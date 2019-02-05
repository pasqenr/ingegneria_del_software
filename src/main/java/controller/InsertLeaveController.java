package controller;

import model.*;

import java.util.List;

public class InsertLeaveController extends Controller {


    public static void addOrder(int orderNumber,
                                List<ArticleModel> articles,
                                String date,
                                StoreModel store,
                                CourierModel courier) {
        addLeaveOrder(orderNumber, date, store, courier);
        int lastOrderNumber = OrderModel.getLastId();
        ArticleOrderModel.storeAll(articles, lastOrderNumber);
    }

    private static void addLeaveOrder(int orderNumber, String date, StoreModel store, CourierModel courier) {
        OrderModel order = new OrderModel(orderNumber, date, store, courier);
        order.store();
    }

    @Override
    public void update() {}
}
