package controller;

import database.DatabaseWrapper;
import model.ArticleModel;
import model.EntranceArticleModel;
import model.EntranceModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntranceArticleController extends Controller {
    private List<EntranceArticleModel> entranceArticles;

    public EntranceArticleController() {
        entranceArticles = fetchAll();
    }

    public List<EntranceArticleModel> getEntranceArticles() {
        return entranceArticles;
    }

    private List<EntranceArticleModel> fetchAll() {
        List<EntranceArticleModel> entranceArticles = new ArrayList<>();
        ArticleController articleController = new ArticleController();
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT ia.codice_articolo, ia.codice_ingresso FROM ingresso_articolo ia";
        PreparedStatement stmt;

        List<ArticleModel> fetchedArticles = new ArrayList<>();
        int lastEntranceCode = 0;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String articleCode = rs.getString("codice_articolo");
                int entranceCode = rs.getInt("codice_ingresso");

                /* If the entranceCode is changed then we need to add the previous articles to entranceArticles, then
                clear the list that contains the articles and insert the new article (it has a different entranceCode)
                 */
                if (hasCodeChanged(lastEntranceCode, entranceCode)) { // Insert new EntranceArticleModel
                    addPreviousArticlesWithSameCode(entranceArticles, fetchedArticles, lastEntranceCode);
                    fetchedArticles.clear();
                    fetchedArticles.add(articleController.getArticleByCode(articleCode));
                } else { // Store new articles with the same entranceCode
                    fetchedArticles.add(articleController.getArticleByCode(articleCode));
                }

                lastEntranceCode = entranceCode;
            }

            // Add the last
            addPreviousArticlesWithSameCode(entranceArticles, fetchedArticles, lastEntranceCode);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return entranceArticles;
    }

    private boolean hasCodeChanged(int lastEntranceCode, int entranceCode) {
        return lastEntranceCode > 0 && entranceCode != lastEntranceCode;
    }

    private void addPreviousArticlesWithSameCode(List<EntranceArticleModel> entranceArticles,
                                                 List<ArticleModel> articles,
                                                 int entranceCode) {
        EntranceModel entrance = EntranceController.getEntranceByCode(entranceCode);
        EntranceArticleModel entranceArticleModel = new EntranceArticleModel(articles, entrance);

        entranceArticles.add(entranceArticleModel);
    }

    @Override
    public void update() {}
}
