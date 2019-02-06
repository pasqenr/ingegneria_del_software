package controller;

import model.ArticleModel;
import model.EntranceArticleModel;
import model.EntranceModel;

import java.util.List;

/**
 * Manage entrance insertion in the warehouse.
 */
public class InsertEntranceController {

    /**
     * Insert a list of Article with the associated entrance.
     *
     * @param articles A list of Article.
     */
    public static void insertArticlesAsEntrance(List<ArticleModel> articles) {
        // Create a new entrance
        EntranceModel entrance = createNewEntrance();

        // Insert the new articles
        articles.forEach(ArticleModel::store);

        // Insert article entrance
        EntranceArticleModel entranceArticleModel = new EntranceArticleModel(articles, entrance);
        entranceArticleModel.store();
    }

    /**
     * Insert a new entrance and return the last entrance inserted.
     *
     * @return The last instance inserted.
     */
    private static EntranceModel createNewEntrance() {
        insertEntrance();

        return fetchLastEntrance();
    }

    /**
     * Create a new Entrance in the database.
     */
    private static void insertEntrance() {
        EntranceModel entrance = new EntranceModel();
        entrance.store();
    }

    /**
     * @return The last Entrance in the database.
     */
    private static EntranceModel fetchLastEntrance() {
        int greatestCode = EntranceModel.getGreatestCode();

        return EntranceModel.find(greatestCode);
    }
}
