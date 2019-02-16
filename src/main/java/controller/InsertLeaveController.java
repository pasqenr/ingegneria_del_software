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
    public void addLeave(int leaveNumber,
                         String orderCode,
                         List<ArticleModel> articles,
                         String date,
                         StoreModel store,
                         CourierModel courier) {
        // Add the leave
        LeaveModel leave = new LeaveModel(leaveNumber, date, store, courier);
        leave.store();

        // Add all the articles to the leave
        int lastLeaveNumber = LeaveModel.getInstance().getLastId();
        ArticleLeaveModel articleLeave = new ArticleLeaveModel(articles, lastLeaveNumber);
        articleLeave.store();

        // Add the fulfillment of the order
        FulfillmentModel fulfillment = new FulfillmentModel(orderCode, lastLeaveNumber);
        fulfillment.store();
    }

    /**
     * Check if all the table codes are stored in the database.
     *
     * @param tableCodes An array of table codes.
     * @return <code>true</code> if all che table codes are in the database, <code>false</code> otherwise.
     */
    public boolean checkIsAlreadyStoredArticleCode(String[] tableCodes) {
        final String[] storedCodes = ArticleModel.getInstance().getArticlesCodes();
        int matchCounter = 0;

        for (String tableCode : tableCodes) {
            for (String storedCode : storedCodes) {
                if (tableCode.equals(storedCode)) { // We have found a code already stored in the database
                    matchCounter++;
                }
            }
        }

        return matchCounter == tableCodes.length;
    }
}
