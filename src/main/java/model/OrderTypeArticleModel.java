package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Represent a OrderTypeArticle, table <code>ordine_tipo_articolo</code>.
 */
public class OrderTypeArticleModel extends Model implements GenericDAO {
    private final OrderModel order;
    private final List<ArticleType> articleTypes;
    private final List<Integer> quantities;
    private final List<Float> totalPrices;

    /**
     * Create a new OrderTypeArticle.
     *
     * @param order A Order.
     * @param articleTypes A list of ArticleTypes.
     * @param quantities The total price of ArticleType ordered.
     */
    public OrderTypeArticleModel(OrderModel order,
                                 List<ArticleType> articleTypes,
                                 List<Integer> quantities,
                                 List<Float> totalPrices) {
        this.order = order;
        this.articleTypes = articleTypes;
        this.quantities = quantities;
        this.totalPrices = totalPrices;
    }

    @Override
    public OrderTypeArticleModel find(String id) {
        return null;
    }

    @Override
    public List<OrderTypeArticleModel> findAll() {
        return null;
    }

    @Override
    public boolean store() {
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "INSERT INTO " +
                "ordine_tipo_articolo (codice_ordine, nome_tipo_articolo, quantita, prezzo_totale) " +
                "VALUES (?, ?, ?, ?)";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);

            for (int i = 0; i < articleTypes.size(); i++) {
                stmt.setString(1, order.getCode());
                stmt.setString(2, articleTypes.get(i).getName());
                stmt.setInt(3, quantities.get(i));
                stmt.setFloat(4, totalPrices.get(i));
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            db.getCon().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return true;
    }
}
