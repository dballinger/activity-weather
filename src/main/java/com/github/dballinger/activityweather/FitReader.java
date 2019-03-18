package com.github.dballinger.activityweather;

import com.garmin.fit.Decode;
import com.garmin.fit.MesgBroadcaster;
import com.garmin.fit.RecordMesg;
import com.garmin.fit.RecordMesgListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class FitReader {
    private InputStream fitStream;

    public FitReader(InputStream fitStream) {
        this.fitStream = fitStream;
    }

    public Optional<Coord> routeMidpoint() {
        Decode decode = new Decode();
        //decode.skipHeader();        // Use on streams with no header and footer (stream contains FIT defn and data messages only)
        //decode.incompleteStream();  // This suppresses exceptions with unexpected eof (also incorrect crc)
        MesgBroadcaster mesgBroadcaster = new MesgBroadcaster(decode);
        PositionListener positionListener = new PositionListener();
        mesgBroadcaster.addListener(positionListener);
        decode.read(fitStream, mesgBroadcaster);
        return positionListener.coords.stream().reduce(new MeanCoord());
    }

    private class PositionListener implements RecordMesgListener {
        final List<Coord> coords = new ArrayList<Coord>();

        @Override
        public void onMesg(RecordMesg recordMesg) {
            coords.add(
             new Coord(
              new Semicircle(recordMesg.getPositionLat()),
              new Semicircle(recordMesg.getPositionLong())
             )
            );
        }
    }

    private class MeanCoord implements BinaryOperator<Coord> {
        private int position = 1;

        @Override
        public Coord apply(Coord meanCoord, Coord next) {
            position++;
            return new Coord(
             new Semicircle(meanCoord.getLat().getValue() + (next.getLat().getValue() - meanCoord.getLat().getValue()) / position),
             new Semicircle(meanCoord.getLon().getValue() + (next.getLon().getValue() - meanCoord.getLon().getValue()) / position)
            );
        }
    }
}
