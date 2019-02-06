package controller;

import model.ArticleType;
import model.OrderModel;
import model.OrderTypeArticleModel;
import model.StoreModel;

import java.util.ArrayList;
import java.util.List;

public class OrderController extends Controller {
    public static void addOrder(String orderCode,
                                String date, StoreModel store,
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

    private static List<ArticleType> articleTypesToList(String[] articleTypesNames) {
        List<ArticleType> articleTypes = new ArrayList<>();
        for (String articleTypeName : articleTypesNames) {
            articleTypes.add(ArticleType.find(articleTypeName));
        }

        return articleTypes;
    }

    private static List<Integer> amountsToList(String[] amounts) {
        List<Integer> amountsList = new ArrayList<>();
        for (String amount : amounts) {
            amountsList.add(Integer.valueOf(amount));
        }

        return amountsList;
    }

    @Override
    public void update() {}
}
