package controller;

import model.*;

import java.util.List;

/**
 * Manage the insertion of a leave from the warehouse.
 */
public class InsertLeaveController {

    /**
     * Insert a new leave in the database.
     *
     * @param leaveNumber The identifier of the leave.
     * @param orderCode A valid Order code.
     * @param articles A list of Article.
     * @param date The date of the leave.
     * @param store The store where send the articles
     * @param courier The courier that will carry the articles.
     */
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
}
