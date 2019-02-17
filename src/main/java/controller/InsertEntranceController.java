package controller;

import model.ArticleModel;
import model.EntranceArticleModel;
import model.EntranceModel;

import java.util.Arrays;
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
    public void insertArticlesAsEntrance(final List<ArticleModel> articles) {
        // Create a new entrance
        final EntranceModel entrance = createNewEntrance();

        // Insert the new articles
        articles.forEach(ArticleModel::store);

        // Insert article entrance
        final EntranceArticleModel entranceArticleModel = new EntranceArticleModel(articles, entrance);
        entranceArticleModel.store();
    }

    /**
     * Check if all the integers in lengths are the same.
     *
     * @param lengths An array of lengths.
     * @return <code>true</code> if all the lengths are equal, <code>false</code> otherwise.
     */
    public boolean checkEqualLengths(final int[] lengths) {
        return Arrays.stream(lengths).allMatch(length -> length == lengths[0]);
    }

    /**
     * Check if at least one code in articleCodes is already stored in the database.
     *
     * @param articleCodes An array of codes.
     * @return <true>code</true> if at least one code in articleCodes is already stored in the database,
     * <code>false</code> otherwise.
     */
    public boolean checkIsAlreadyStoredArticleCodes(final String[] articleCodes) {
        final String[] storedCodes = ArticleModel.getInstance().getArticlesCodes();

        for (final String tableCode : articleCodes) {
            for (final String storedCode : storedCodes) {
                if (tableCode.equals(storedCode)) { // We have found a code already stored in the database
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Insert a new entrance and return the last entrance inserted.
     *
     * @return The last instance inserted.
     */
    private EntranceModel createNewEntrance() {
        insertEntrance();

        return fetchLastEntrance();
    }

    /**
     * Create a new Entrance in the database.
     */
    private void insertEntrance() {
        final EntranceModel entrance = new EntranceModel();
        entrance.store();
    }

    /**
     * @return The last Entrance in the database.
     */
    private EntranceModel fetchLastEntrance() {
        final int greatestCode = EntranceModel.getInstance().getGreatestCode();

        return EntranceModel.getInstance().find(greatestCode);
    }
}
