package controller;

import model.OrdersModel;
import org.junit.jupiter.api.Test;
import util.TestUtilities;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntranceControllerTest extends GenericControllerTest {
    @Test
    void getOrdersModelTest() {
        final EntranceController entranceController = new EntranceController();
        final OrdersModel ordersModel = entranceController.getOrdersModel();
        final String[] values = new String[] {
                "1", "2017-10-03", "AC8388175", "Costumi Acquagym", "9.99", "A010101",
                "1", "2017-10-03", "AC8402405", "Costumi Acquagym", "7.99", "A010102",
                "1", "2017-10-03", "AC8528089", "Scarpette e calze Acquagym", "5.99", "A010201"
        };

        List<String> ordersList = TestUtilities.flattenToString(ordersModel.getRawData());

        for (int i = 0; i < ordersList.size(); i++) {
            assertEquals(values[i], ordersList.get(i));
        }
    }
}
