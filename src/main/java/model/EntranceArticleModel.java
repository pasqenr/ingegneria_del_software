package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntranceArticleModel extends Model {
    private List<ArticleModel> articles;
    private EntranceModel entrance;

    public EntranceArticleModel(List<ArticleModel> articles, EntranceModel entrance) {
        this.articles = new ArrayList<>(articles);
        this.entrance = entrance;
    }

    @Override
    public boolean store() {
        boolean result = true;

        for (ArticleModel article : articles) {
            result = result && storeSingleArticle(article);
        }

        return result;
    }

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
