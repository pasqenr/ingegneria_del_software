package controller;

import model.PositionModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PositionControllerTest extends GenericControllerTest {
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

        PositionController positionController = new PositionController();
        List<PositionModel> fetchedFreePositions = positionController.getFreePositions();

        assertEquals(freePositions, fetchedFreePositions);
    }

    @Test
    void findFreePositionByCodeTest() {
        PositionController positionController = new PositionController();

        PositionModel validPosition = positionController.findFreePositionByCode("A010106");
        PositionModel invalidPosition = positionController.findFreePositionByCode("A010101");

        assertNotNull(validPosition);
        assertEquals(validPosition.getCode(), "A010106");
        assertNull(invalidPosition);
    }
}
