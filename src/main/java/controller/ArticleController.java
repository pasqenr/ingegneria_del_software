package controller;

import database.DatabaseWrapper;
import model.ArticleModel;
import model.ArticleType;
import model.PositionModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    private List<ArticleModel> articles;

    public ArticleController() {
        articles = fetchAllArticles();
    }

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public String[] getArticlesCodes() {
        String[] codes = new String[articles.size()];

        int i = 0;
        for (ArticleModel a : articles) {
            codes[i] = a.getCode();
            i++;
        }

        return codes;
    }

    public ArticleModel findArticleByCode(String articleCode) {
        update();
        ArticleModel article = null;

        for (ArticleModel a : articles) {
            if (articleCode.equals(a.getCode())) {
                article = a;
            }
        }

        return article;
    }

    /**
     * Fetch fresh data from the database and update the internal state
     */
    public void update() {
        articles = fetchAllArticles();
    }

    public ArticleModel getArticleByCode(String code) {
        return articles.stream().filter(article1 -> article1.getCode().equals(code)).findFirst().orElse(null);
    }

    private List<ArticleModel> fetchAllArticles() {
        List<ArticleModel> articlesList = new ArrayList<>();

        try (DatabaseWrapper db = new DatabaseWrapper()) {
            ResultSet rs;
            String query = "SELECT a.codice, a.tipo_articolo, a.prezzo, a.data_produzione, a.posizione FROM articolo a";
            rs = db.rawQuery(query);

            while (rs.next()) {
                String code = rs.getString("codice");
                String articleName = rs.getString("tipo_articolo");
                ArticleType articleType = new ArticleType(articleName, "", "", null);
                String price = rs.getString("prezzo");
                String productionDate = rs.getString("data_produzione");
                String position_id = rs.getString("posizione");
                PositionModel position = new PositionModel(position_id);

                ArticleModel article = new ArticleModel(code, articleType, price, productionDate, position);
                articlesList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articlesList;
    }
}
