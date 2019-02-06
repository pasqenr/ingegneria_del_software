package controller;

import model.ArticleType;
import model.OrderModel;
import model.OrderTypeArticleModel;
import model.StoreModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage an Order.
 */
public class OrderController {
    /**
     * Add an Order in the database with the related article types and amounts of them.
     *
     * @param orderCode A valid Order code.
     * @param date A valid Order date.
     * @param store A valid Store model.
     * @param articleTypesNames An array of valid ArticleType names.
     * @param amounts An array of amounts.
     */
    public static void addOrder(String orderCode,
                                String date,
                                StoreModel store,
                                String[] articleTypesNames,
                                String[] amounts) {
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
            articleTypes.add(ArticleType.find(articleTypeName));
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
}
