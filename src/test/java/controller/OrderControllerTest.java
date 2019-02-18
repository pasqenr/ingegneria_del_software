package controller;

import factories.InstanceFactory;
import model.OrderModel;
import model.StoreModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest extends GenericControllerTest {
    @Test
    void addOrderTest() {
        final OrderController orderController = new OrderController();
        final StoreModel store = new StoreModel("NEG001",
                "Decathlon",
                "Via Monte Cristallo",
                "San Giovanni Lupatoto");
        final String[] articleTypesNames = new String[] {
                "Costumi Acquagym",
                "Materiale Acquagym"
        };
        final String[] quantities = new String[] { "2", "3" };

        orderController.addOrder(store, articleTypesNames, quantities);
        OrderModel lastOrder = InstanceFactory.getInstance(OrderModel.class).fetchLast();
        List<OrderModel> orders = new ArrayList<>(InstanceFactory.getInstance(OrderModel.class).findAll());
        assertNotNull(lastOrder);
        assertEquals(2, orders.size());
        assertNotEquals(orders.get(0), lastOrder);
        assertEquals(orders.get(1), lastOrder);
    }
}
