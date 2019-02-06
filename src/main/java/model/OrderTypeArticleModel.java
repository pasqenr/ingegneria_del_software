package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderTypeArticleModel extends Model {
    private OrderModel order;
    private List<ArticleType> articleTypes;
    private List<Integer> amounts;

    public OrderTypeArticleModel(OrderModel order, List<ArticleType> articleTypes, List<Integer> amounts) {
        this.order = order;
        this.articleTypes = articleTypes;
        this.amounts = amounts;
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
