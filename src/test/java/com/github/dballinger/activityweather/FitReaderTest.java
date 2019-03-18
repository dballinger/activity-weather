package com.github.dballinger.activityweather;

import com.garmin.fit.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class FitReaderTest {
    @Test
    public void shouldCalulateTheMidPointOfTheRouteFromAFitFile() throws Exception {
        var records = List.of(
         new Coord(new DecimalDegree(51.230798).toSemicircle(), new DecimalDegree(-0.185539).toSemicircle()),
         new Coord(new DecimalDegree(51.233270).toSemicircle(), new DecimalDegree(-0.226827).toSemicircle())
        );

        var fitFile = writeFit(records);

        FitReader fit = new FitReader(new FileInputStream(fitFile));
        Coord midpont = fit.routeMidpoint().get();

        System.out.println(midpont.getLat().toDegrees());
        System.out.println(midpont.getLon().toDegrees());
    }


    private java.io.File writeFit(List<Coord> coords) {
        java.io.File activityFile = null;
        try {
            activityFile = java.io.File.createTempFile("activity", ".fit");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileEncoder encoder = new FileEncoder(activityFile, Fit.ProtocolVersion.V2_0);
        FileIdMesg fileIdMesg = new FileIdMesg();
        fileIdMesg.setManufacturer(Manufacturer.GARMIN);
        fileIdMesg.setType(File.ACTIVITY);
        fileIdMesg.setProduct(1);
        fileIdMesg.setSerialNumber(2L);
        encoder.write(fileIdMesg);

        byte[] appId = new byte[]{
         0x1, 0x1, 0x2, 0x3,
         0x5, 0x8, 0xD, 0x15,
         0x22, 0x37, 0x59, (byte) 0x90,
         (byte) 0xE9, 0x79, 0x62, (byte) 0xDB
        };

        DeveloperDataIdMesg developerIdMesg = new DeveloperDataIdMesg();
        for (int i = 0; i < appId.length; i++) {
            developerIdMesg.setApplicationId(i, appId[i]);
        }
        developerIdMesg.setDeveloperDataIndex((short) 0);
        encoder.write(developerIdMesg);

        for (Coord coord : coords) {
            RecordMesg recordMesg = new RecordMesg();
            recordMesg.setTimestamp(new DateTime(new Date()));
            recordMesg.setPositionLat((int)coord.getLat().getValue());
            recordMesg.setPositionLong((int)coord.getLon().getValue());
            encoder.write(recordMesg);
        }

        encoder.close();

        return activityFile;
    }

}
