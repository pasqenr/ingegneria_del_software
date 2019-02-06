package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ArticleLeaveModel extends Model {
    public static void storeAll(List<ArticleModel> articles, int orderNumber) {
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
    }

    @Override
    public boolean store() {
        return false;
    }
}
