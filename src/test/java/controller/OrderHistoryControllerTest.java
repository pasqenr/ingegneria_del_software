package controller;

import model.OrdersModel;
import model.StoreModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderHistoryControllerTest extends GenericControllerTest {
    @Test
    void getOrdersModelTest() {
        final StoreModel storeValid = new StoreModel("NEG001",
                "Decathlon",
                "Via Monte Cristallo",
                "San Giovanni Lupatoto");
        final OrderHistoryController orderHistoryController = new OrderHistoryController(storeValid);
        final OrdersModel ordersModel = orderHistoryController.getOrdersModel();
        final Object[][] ordersModelRawData = ordersModel.getRawData();
        final String[] rawDataValid = new String[] {
                "ORD001", "2017-07-30", "Costumi Acquagym", "ORD001", "2017-07-30", "Scarpette e calze Acquagym"
        };

        final List<String> ordersModelList = flattenToString(ordersModelRawData);

        for (int i = 0; i < ordersModelList.size(); i++) {
            assertEquals(rawDataValid[i], ordersModelList.get(i));
        }
    }

    private List<String> flattenToString(Object[][] data) {
        List<String> list = new ArrayList<>();

        Arrays.stream(data)
                .forEach(objects -> Arrays.stream(objects)
                        .forEach(object -> list.add((String)object)));

        return list;
    }
}
