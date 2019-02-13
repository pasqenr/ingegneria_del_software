package controller;

import database.DatabaseWrapper;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage an Order.
 */
public class OrderController {
    /**
     * Add an Order in the database with the related article types and quantities of them.
     *
     * @param store A valid Store model.
     * @param articleTypesNames An array of valid ArticleType names.
     * @param quantities An array of quantities.
     */
    public void addOrder(StoreModel store, String[] articleTypesNames, String[] quantities) {
        String orderCode = buildNewOrderCode();
        String date = getCurrentDate();

        // To list
        List<ArticleType> articleTypesList = articleTypesToList(articleTypesNames);
        List<Integer> quantitiesList = amountsToList(quantities);

        // Create a new order
        OrderModel order = new OrderModel(orderCode, date, store);
        order.store();

        // Calculate the total prices
        List<Float> totalPrices = calculateTotalPrices(articleTypesList, quantitiesList);

        // Insert the articles and quantities in the order created
        OrderTypeArticleModel orderTypeArticle = new OrderTypeArticleModel(
                order,
                articleTypesList,
                quantitiesList,
                totalPrices);
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
     * Returns a list of quantities from an array of quantities.
     *
     * @param quantities An array of quantities.
     * @return A list of quantities.
     */
    private static List<Integer> amountsToList(String[] quantities) {
        List<Integer> amountsList = new ArrayList<>();

        for (String amount : quantities) {
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

    /**
     * Calculate the total prices for every ArticleType in the list articleTypes.
     *
     * @param articleTypes A list of ArticleTypes.
     * @param quantities A list of quantities, one for every ArticleType.
     * @return A list of total prices, one for every ArticleType.
     */
    private List<Float> calculateTotalPrices(List<ArticleType> articleTypes, final List<Integer> quantities) {
        final List<Float> totalPrices = new ArrayList<>();

        for (int i = 0; i < articleTypes.size(); i++) {
            final List<String> articleCodes = fetchArticlesCodesFromType(articleTypes.get(i));
            final Integer quantity = quantities.get(i);
            totalPrices.add(calculateSingleTotalPrice(articleCodes, quantity));
        }

        return totalPrices;
    }

    /**
     * Calculate the total price of a single ArticleType. To do that the function needs a list of article codes
     * related to that ArticleType.
     * The total price is sum(all_articles) * quantity, where all_articles are only the ones of a single ArticleType.
     *
     * @param articleCodes A list of valid article codes.
     * @param quantity A valid quantity.
     * @return The total price.
     */
    private Float calculateSingleTotalPrice(final List<String> articleCodes, final Integer quantity) {
        float totalPrice = 0F;

        final String queryWhere =
                "SELECT SUM(articolo.prezzo) AS prezzo_totale " +
                "FROM articolo " +
                "WHERE articolo.codice";

        try (final DatabaseWrapper db = new DatabaseWrapper()) {
            final ResultSet rs = db.queryIn(queryWhere, articleCodes);

            if (rs.next()) {
                totalPrice += rs.getFloat("prezzo_totale");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPrice * quantity;
    }

    /**
     * Return a list articles codes from the Articles related to some articleType.
     *
     * @param articleType A valid articleType.
     * @return A list of articles codes related to articleType.
     */
    private List<String> fetchArticlesCodesFromType(final ArticleType articleType) {
        final List<String> articles = new ArrayList<>();
        final String query = "SELECT a.codice " +
                "FROM articolo a " +
                "JOIN tipo_articolo ta ON a.tipo_articolo = ta.nome " +
                "WHERE ta.nome LIKE ?";
        final PreparedStatement stmt;

        try (final DatabaseWrapper db = new DatabaseWrapper()) {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, articleType.getName());
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                articles.add(rs.getString("codice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;
    }
}
