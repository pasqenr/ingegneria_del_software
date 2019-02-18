package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represente an EntranceArticle, table <code>ingresso_articolo</code>.
 */
public class EntranceArticleModel extends Model implements GenericDAO {
    private final List<ArticleModel> articles;
    private final EntranceModel entrance;

    /**
     * Create a new EntranceArticle.
     *
     * @param articles A list of Articles.
     * @param entrance An Entrance.
     */
    public EntranceArticleModel(Collection<ArticleModel> articles, EntranceModel entrance) {
        this.articles = new ArrayList<>(articles);
        this.entrance = entrance;
    }

    @Override
    public EntranceArticleModel find(String id) {
        return null;
    }

    @Override
    public Collection<EntranceArticleModel> findAll() {
        return null;
    }

    @Override
    public boolean store() {
        boolean result = true;

        for (final ArticleModel article : articles) {
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
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "INSERT INTO ingresso_articolo (codice_articolo, codice_ingresso) VALUES (?, ?)";
        final PreparedStatement stmt;

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
    public boolean equals(Object o) {
        if (! (o instanceof  EntranceArticleModel)) {
            return false;
        }

        final EntranceArticleModel other = (EntranceArticleModel)o;

        boolean areArticlesEquals = true;
        for (int i = 0; i < articles.size(); i++) {
            areArticlesEquals = areArticlesEquals && articles.get(i).equals(other.articles.get(i));
        }

        return areArticlesEquals &&
                entrance.equals(other.entrance);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("EntranceArticleModel {entrance : ");
        stringBuilder.append(entrance);
        stringBuilder.append(", articles : ");

        articles.forEach(article -> stringBuilder.append(article).append(", "));

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
