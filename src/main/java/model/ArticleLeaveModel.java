package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Represent the entity ArticleLeave, table <code>uscita_articolo</code>.
 */
public class ArticleLeaveModel extends Model {
    private List<ArticleModel> articles;
    private int orderNumber;

    /**
     * Create a new ArticleLeave.
     *
     * @param articles A list of Article.
     * @param orderNumber A valid order number.
     */
    public ArticleLeaveModel(List<ArticleModel> articles, int orderNumber) {
        this.articles = articles;
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean store() {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "INSERT INTO uscita_articolo (codice_articolo, numero_bolla) VALUES (?, ?)";
        PreparedStatement stmt;

        for (ArticleModel article : articles) {
            try {
                stmt = db.getCon().prepareStatement(query);
                stmt.setString(1, article.getCode());
                stmt.setInt(2, orderNumber);
                stmt.execute();
                db.getCon().commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        db.close();

        return true;
    }
}
