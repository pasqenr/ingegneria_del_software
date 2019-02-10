package controller;

import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage an Order.
 */
public class OrderController {
    /**
     * Add an Order in the database with the related article types and amounts of them.
     *
     * @param store A valid Store model.
     * @param articleTypesNames An array of valid ArticleType names.
     * @param amounts An array of amounts.
     */
    public void addOrder(StoreModel store, String[] articleTypesNames, String[] amounts) {
        String orderCode = buildNewOrderCode();
        String date = getCurrentDate();

        // To list
        List<ArticleType> articleTypesList = articleTypesToList(articleTypesNames);
        List<Integer> amountsList = amountsToList(amounts);

        // Create a new order
        OrderModel order = new OrderModel(orderCode, date, store);
        order.store();

        // Insert the articles and amounts in the order created
        OrderTypeArticleModel orderTypeArticle = new OrderTypeArticleModel(order, articleTypesList, amountsList);
        orderTypeArticle.store();
    }

    /**
     * Returns a list of ArticleType from an array of valid ArticleType names.
     *
     * @param articleTypesNames A valid array of ArticleType names.
     * @return A list of ArticleType.
     */
    private static List<ArticleType> articleTypesToList(String[] articleTypesNames) {
        List<ArticleType> articleTypes = new ArrayList<>();
        for (String articleTypeName : articleTypesNames) {
            articleTypes.add(ArticleType.getInstance().find(articleTypeName));
        }

        return articleTypes;
    }

    /**
     * Returns a list of amounts from an array of amounts.
     *
     * @param amounts An array of amounts.
     * @return A list of amounts.
     */
    private static List<Integer> amountsToList(String[] amounts) {
        List<Integer> amountsList = new ArrayList<>();
        for (String amount : amounts) {
            amountsList.add(Integer.valueOf(amount));
        }

        return amountsList;
    }

    /**
     * @return The current date in the format yyyy-MM-dd.
     */
    private String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * @return The numerical part of the last order. Orders are made, for example, as <code>ORD001</code>. This
     * function returns only the last part 001 as integer, so 1.
     */
    private int fetchLastOrderCodeNumber() {
        String lastOrderCode = OrderModel.getInstance().fetchLast().getCode();

        return Integer.valueOf(lastOrderCode.substring(4));
    }

    /**
     * @return The new sequential order code to insert in the database. That is, the last order code + 1.
     * For example: ORD001 -> buildNewOrderCode() -> ORD002.
     */
    private String buildNewOrderCode() {
        int lastOrderCode = fetchLastOrderCodeNumber();

        return String.format("ORD%03d", lastOrderCode + 1);
    }
}
