package controller;

import model.PositionModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class PositionControllerTest {
    @Test
    void testGetFreePositions() {
        List<PositionModel> freePositions = new ArrayList<>();
        freePositions.add(new PositionModel("A010106"));
        freePositions.add(new PositionModel("A010107"));
        freePositions.add(new PositionModel("A010108"));
        freePositions.add(new PositionModel("A010109"));
        freePositions.add(new PositionModel("A010110"));
        freePositions.add(new PositionModel("A010205"));
        freePositions.add(new PositionModel("A010206"));
        freePositions.add(new PositionModel("A010207"));
        freePositions.add(new PositionModel("A010208"));
        freePositions.add(new PositionModel("A010209"));
        freePositions.add(new PositionModel("A010210"));
        freePositions.add(new PositionModel("A010302"));
        freePositions.add(new PositionModel("A010303"));
        freePositions.add(new PositionModel("A010304"));
        freePositions.add(new PositionModel("A010305"));
        freePositions.add(new PositionModel("A010306"));
        freePositions.add(new PositionModel("A010307"));
        freePositions.add(new PositionModel("A010308"));
        freePositions.add(new PositionModel("A010309"));
        freePositions.add(new PositionModel("A010310"));

        List<PositionModel> usedPositions = new ArrayList<>();
        usedPositions.add(new PositionModel("A010102"));
        usedPositions.add(new PositionModel("A010104"));
        usedPositions.add(new PositionModel("A010106"));
        usedPositions.add(new PositionModel("A010108"));
        usedPositions.add(new PositionModel("A010110"));

        PositionController pc = new PositionController();

        PositionModel[] freePositionsArray = (PositionModel[])freePositions.toArray();
        String[] freeRawPositions = new String[freePositionsArray.length];

        for (int i = 0; i < freePositionsArray.length; i++) {
            freeRawPositions[i] = freePositionsArray[i].getCode();
        }

        List<PositionModel> fetchedFreePositions = new ArrayList<>(pc.getFreePositions());
        PositionModel[] freeFetchedPositionsArray = (PositionModel[])fetchedFreePositions.toArray();
        String[] freeFetchedRawPositions = new String[freeFetchedPositionsArray.length];

        for (int i = 0; i < freeFetchedPositionsArray.length; i++) {
            freeFetchedRawPositions[i] = freeFetchedPositionsArray[i].getCode();
        }

        assertEquals(freeRawPositions, freeFetchedRawPositions);
    }
}
