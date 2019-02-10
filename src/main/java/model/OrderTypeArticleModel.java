package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Represent a OrderTypeArticle, table <code>ordine_tipo_articolo</code>.
 */
public class OrderTypeArticleModel extends Model implements GenericDAO {
    private OrderModel order;
    private List<ArticleType> articleTypes;
    private List<Integer> amounts;

    /**
     * Create a new OrderTypeArticle.
     *
     * @param order A Order.
     * @param articleTypes A list of ArticleTypes.
     * @param amounts A list of amounts for each ArticleType.
     */
    public OrderTypeArticleModel(OrderModel order, List<ArticleType> articleTypes, List<Integer> amounts) {
        this.order = order;
        this.articleTypes = articleTypes;
        this.amounts = amounts;
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
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "INSERT INTO ordine_tipo_articolo (codice_ordine, nome_tipo_articolo, quantita) VALUES " +
                "(?, ?, ?)";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);

            for (int i = 0; i < articleTypes.size(); i++) {
                stmt.setString(1, order.getCode());
                stmt.setString(2, articleTypes.get(i).getName());
                stmt.setInt(3, amounts.get(i));
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
