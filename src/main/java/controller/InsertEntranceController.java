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
    public void insertArticlesAsEntrance(List<ArticleModel> articles) {
        // Create a new entrance
        EntranceModel entrance = createNewEntrance();

        // Insert the new articles
        articles.forEach(ArticleModel::store);

        // Insert article entrance
        EntranceArticleModel entranceArticleModel = new EntranceArticleModel(articles, entrance);
        entranceArticleModel.store();
    }

    /**
     * Check if all the integers in lengths are the same.
     *
     * @param lengths An array of lengths.
     * @return <code>true</code> if all the lengths are equal, <code>false</code> otherwise.
     */
    public boolean checkEqualLengths(int[] lengths) {
        return Arrays.stream(lengths).allMatch(length -> length == lengths[0]);
    }

    /**
     * Check if some code in tableCodes is already stored in the database.
     *
     * @param tableCodes An array of codes.
     * @return <true>code</true> if all the codes are not used in the database, <code>false</code> otherwise.
     */
    public boolean checkIsAlreadyStoredArticleCode(String[] tableCodes) {
        String[] storedCodes = ArticleModel.getInstance().getArticlesCodes();

        for (String tableCode : tableCodes) {
            for (String storedCode : storedCodes) {
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
        EntranceModel entrance = new EntranceModel();
        entrance.store();
    }

    /**
     * @return The last Entrance in the database.
     */
    private EntranceModel fetchLastEntrance() {
        int greatestCode = EntranceModel.getInstance().getGreatestCode();

        return EntranceModel.getInstance().find(greatestCode);
    }
}
