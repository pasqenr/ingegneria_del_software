package controller;

import database.DatabaseWrapper;
import factories.FactoryProducer;
import model.ArticleModel;
import model.EntranceArticleModel;
import model.EntranceModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static factories.FactoryProducer.FactoryType.ARTICLE;
import static factories.FactoryProducer.FactoryType.ENTRANCE;

/**
 * Manage the entrances of articles in the warehouse.
 */
public class EntranceArticleController {
    private final List<EntranceArticleModel> entranceArticles;

    /**
     * Create a new controller.
     */
    public EntranceArticleController() {
        entranceArticles = fetchAll();
    }

    /**
     * @return The list of <code>EntranceArticle</code>.
     */
    public List<EntranceArticleModel> getEntranceArticles() {
        return entranceArticles;
    }

    /**
     * Fetch from the database all the articles codes and the related entrance codes. Since that the
     * <code>EntranceArticleModel</code> require a list of articles every entrance code we need to do some work.
     * We keep track of the entrance code so that when it changes we stop adding to the first list, build a model, add
     * this to the return list, and start building the second model.
     *
     * @return The list of <code>EntranceArticle</code> models.
     */
    private List<EntranceArticleModel> fetchAll() {
        final List<EntranceArticleModel> entranceArticles = new ArrayList<>();
        final ArticleModel articleModelDao = FactoryProducer.getFactory(ARTICLE).getArticleModel();
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT ia.codice_articolo, ia.codice_ingresso FROM ingresso_articolo ia";
        final PreparedStatement stmt;

        final List<ArticleModel> fetchedArticles = new ArrayList<>();
        int lastEntranceCode = 0;

        try {
            stmt = db.getCon().prepareStatement(query);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String articleCode = rs.getString("codice_articolo");
                int entranceCode = rs.getInt("codice_ingresso");

                /* If the entranceCode is changed then we need to add the previous articles to entranceArticles, then
                clear the list that contains the articles and insert the new article (it has a different entranceCode)
                 */
                if (hasCodeChanged(lastEntranceCode, entranceCode)) { // Insert new EntranceArticleModel
                    addPreviousArticlesWithSameCode(entranceArticles, fetchedArticles, lastEntranceCode);
                    fetchedArticles.clear();
                    fetchedArticles.add(articleModelDao.find(articleCode));
                } else { // Store new articles with the same entranceCode
                    fetchedArticles.add(articleModelDao.find(articleCode));
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

    /**
     * Check if the <code>lastEntranceCode</code> and <code>entranceCode</code> are different
     *
     * @param lastEntranceCode The last entrance code.
     * @param entranceCode The new entrance code.
     * @return <code>true</code> if the codes are different, <code>false</code> otherwise.
     */
    private boolean hasCodeChanged(final int lastEntranceCode, final int entranceCode) {
        return lastEntranceCode > 0 && entranceCode != lastEntranceCode;
    }

    /**
     * Adds to the list of <code>entranceArticles</code> the <code>articles</code> related to the same
     * <code>entranceCode</code>.
     *
     * @param entranceArticles The list of <code>EntranceArticle</code>.
     * @param articles The list of <code>Article</code>.
     * @param entranceCode The entrance code.
     */
    private void addPreviousArticlesWithSameCode(final List<EntranceArticleModel> entranceArticles,
                                                 final List<ArticleModel> articles,
                                                 final int entranceCode) {
        final EntranceModel entrance = FactoryProducer.getFactory(ENTRANCE).getEntranceModel().find(entranceCode);
        final EntranceArticleModel entranceArticleModel = new EntranceArticleModel(articles, entrance);

        entranceArticles.add(entranceArticleModel);
    }
}
