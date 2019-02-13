package controller;

import model.OrdersModel;
import org.junit.jupiter.api.Test;
import util.TestUtilities;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaveControllerTest extends GenericControllerTest {
    @Test
    void getOrdersModelTest() {
        final LeaveController leaveController = new LeaveController();
        final OrdersModel ordersModel = leaveController.getOrdersModel();
        final String[] values = new String[] {
                "1",
                "2018-06-15",
                "AC8360917,AC8388175,AC8402392,AC8402405,AC8503430,AC8342928,AC8503710,AC8513244,AC8528089",
                "Costumi Acquagym",
                "29.99"
        };
        final List<String> ordersList = TestUtilities.flattenToString(ordersModel.getRawData());

        for (int i = 0; i < ordersList.size(); i++) {
            assertEquals(values[i], ordersList.get(i));
        }
    }
}
