package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represente an EntranceArticle, table <code>ingresso_articolo</code>.
 */
public class EntranceArticleModel extends Model implements GenericDAO {
    private List<ArticleModel> articles;
    private EntranceModel entrance;

    /**
     * Create a new EntranceArticle.
     *
     * @param articles A list of Articles.
     * @param entrance An Entrance.
     */
    public EntranceArticleModel(List<ArticleModel> articles, EntranceModel entrance) {
        this.articles = new ArrayList<>(articles);
        this.entrance = entrance;
    }

    @Override
    public EntranceArticleModel find(String id) {
        return null;
    }

    @Override
    public List<EntranceArticleModel> findAll() {
        return null;
    }

    @Override
    public boolean store() {
        boolean result = true;

        for (ArticleModel article : articles) {
            result = result && storeSingleArticle(article);
        }

        return result;
    }

    /**
     * Store a single Article.
     *
     * @param article The Article to store.
     * @return <code>true</code> if the Article was stored, <code>false</code> otherwise.
     */
    private boolean storeSingleArticle(ArticleModel article) {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "INSERT INTO ingresso_articolo (codice_articolo, codice_ingresso) VALUES (?, ?)";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, article.getCode());
            stmt.setInt(2, entrance.getCode());
            stmt.execute();
            db.getCon().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("EntranceArticleModel {entrance : ");
        stringBuilder.append(entrance);
        stringBuilder.append(", articles : ");

        articles.forEach(article -> stringBuilder.append(article).append(", "));

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
