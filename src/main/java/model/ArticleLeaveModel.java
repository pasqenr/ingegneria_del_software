package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent the entity ArticleLeave, table <code>uscita_articolo</code>.
 */
public class ArticleLeaveModel extends Model implements GenericDAO {
    private List<ArticleModel> articles;
    private int leaveNumber;

    /**
     * Create a new ArticleLeave.
     *
     * @param articles A list of Article.
     * @param leaveNumber A valid order number.
     */
    public ArticleLeaveModel(List<ArticleModel> articles, int leaveNumber) {
        this.articles = articles;
        this.leaveNumber = leaveNumber;
    }

    public static ArticleLeaveModel getInstance() {
        return new ArticleLeaveModel(null, 0);
    }

    @Override
    public ArticleLeaveModel find(String id) {
        return null;
    }

    @Override
    public List<ArticleLeaveModel> findAll() {
        List<ArticleLeaveModel> list = new ArrayList<>();
        DatabaseWrapper db = new DatabaseWrapper();
        String query =
                "SELECT ua.numero_bolla, GROUP_CONCAT(ua.codice_articolo) AS codice_articoli " +
                "FROM uscita_articolo ua " +
                "GROUP BY ua.numero_bolla";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int leaveNumber = rs.getInt("numero_bolla");
                String articleCodes = rs.getString("codice_articoli");
                List<ArticleModel> articles = articleCodesToList(articleCodes);
                list.add(new ArticleLeaveModel(articles, leaveNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return list;
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
                stmt.setInt(2, leaveNumber);
                stmt.execute();
                db.getCon().commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        db.close();

        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        stringBuilder.append("leaveNumber: ");
        stringBuilder.append(leaveNumber);
        stringBuilder.append(", articles: ");
        articles.forEach(stringBuilder::append);

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  ArticleLeaveModel)) {
            return false;
        }

        ArticleLeaveModel other = (ArticleLeaveModel)o;

        return leaveNumber == other.leaveNumber &&
                articles.equals(other.articles);
    }

    /**
     * Create a list of ArticleModel from a String of article codes separated by comma ','.
     *
     * @param articleCodes Article codes separated by comma ','.
     * @return A list of ArticleModels.
     */
    private List<ArticleModel> articleCodesToList(String articleCodes) {
        List<ArticleModel> articles = new ArrayList<>();
        final String[] array = articleCodes.split(",");

        for (String s : array) {
            articles.add(ArticleModel.getInstance().find(s));
        }

        return articles;
    }
}
