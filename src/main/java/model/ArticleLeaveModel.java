package model;

import database.DatabaseWrapper;
import factories.InstanceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represent the entity ArticleLeave, table <code>uscita_articolo</code>.
 */
public class ArticleLeaveModel extends Model implements GenericDAO {
    private final List<ArticleModel> articles;
    private final int leaveNumber;

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

    @Override
    public ArticleLeaveModel find(String id) {
        return null;
    }

    @Override
    public Collection<ArticleLeaveModel> findAll() {
        final Collection<ArticleLeaveModel> list = new ArrayList<>();
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query =
                "SELECT ua.numero_bolla, GROUP_CONCAT(ua.codice_articolo) AS codice_articoli " +
                "FROM uscita_articolo ua " +
                "GROUP BY ua.numero_bolla";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                final int leaveNumber = rs.getInt("numero_bolla");
                final String articleCodes = rs.getString("codice_articoli");
                final List<ArticleModel> articles = articleCodesToList(articleCodes);
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
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "INSERT INTO uscita_articolo (codice_articolo, numero_bolla) VALUES (?, ?)";
        PreparedStatement stmt;

        for (final ArticleModel article : articles) {
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
        final StringBuilder stringBuilder = new StringBuilder();
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

        final ArticleLeaveModel other = (ArticleLeaveModel)o;

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
        final List<ArticleModel> articles = new ArrayList<>();
        final String[] array = articleCodes.split(",");

        for (final String s : array) {
            articles.add(InstanceFactory.getInstance(ArticleModel.class).find(s));
        }

        return articles;
    }
}
