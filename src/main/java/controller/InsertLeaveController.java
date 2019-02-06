package controller;

import model.*;

import java.util.List;

public class InsertLeaveController extends Controller {


    public static void addLeave(int leaveNumber,
                                String orderCode,
                                List<ArticleModel> articles,
                                String date,
                                StoreModel store,
                                CourierModel courier) {
        // Add the leave
        LeaveModel leave = new LeaveModel(leaveNumber, date, store, courier);
        leave.store();

        // Add all the articles to the leave
        int lastLeaveNumber = LeaveModel.getLastId();
        ArticleLeaveModel.storeAll(articles, lastLeaveNumber);

        // Add the fulfillment of the order
        FulfillmentModel fulfillment = new FulfillmentModel(orderCode, lastLeaveNumber);
        fulfillment.store();
    }

    @Override
    public void update() {}
}
