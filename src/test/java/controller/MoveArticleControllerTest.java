package controller;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class MoveArticleControllerTest extends GenericControllerTest {
    @Test
    void getFreePositionComboBoxModelTest() {
        final String[] freePositionsValid = new String[] {
                "A010106", "A010107", "A010108", "A010109", "A010110", "A010205", "A010206", "A010207", "A010208",
            "A010209", "A010210", "A010302", "A010303", "A010304", "A010305", "A010306", "A010307", "A010308",
            "A010309", "A010310"
        };
        final MoveArticleController moveArticleController = new MoveArticleController();
        final DefaultComboBoxModel<String> freePositionsComboBoxModel =
                moveArticleController.getFreePositionComboBoxModel();

        for (int i = 0; i < freePositionsComboBoxModel.getSize(); i++) {
            assertEquals(freePositionsValid[i], freePositionsComboBoxModel.getElementAt(i));
        }
    }

    @Test
    void moveArticlePositionByCodesTest() {
        final MoveArticleController moveArticleController = new MoveArticleController();
        final String articleCodeValid = "AC8388175";
        final String positionIdValid = "A010106";
        final String positionId2Valid = "A010107";
        final String articleCodeInvalid = "AC000000";

        // The position should be valid by contract
        assertTrue(moveArticleController.moveArticlePositionByCodes(articleCodeValid, positionIdValid));
        assertFalse(moveArticleController.moveArticlePositionByCodes(articleCodeInvalid, positionId2Valid));
    }
}
