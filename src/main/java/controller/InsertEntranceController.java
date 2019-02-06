package controller;

import model.ArticleModel;
import model.EntranceArticleModel;
import model.EntranceModel;

import java.util.List;

public class InsertEntranceController extends Controller {
    private EntranceArticleModel entranceArticleModel;

    public InsertEntranceController() {}

    public void insertArticlesAsEntrance(List<ArticleModel> articles) {
        // Create a new entrance
        EntranceModel entrance = createNewEntrance();

        // Insert the new articles
        articles.forEach(ArticleModel::store);

        // Insert article entrance
        entranceArticleModel = new EntranceArticleModel(articles, entrance);
        entranceArticleModel.store();
    }

    private EntranceModel createNewEntrance() {
        insertEntrance();

        return fetchLastEntrance();
    }

    private void insertEntrance() {
        EntranceModel entrance = new EntranceModel();
        entrance.store();
    }

    private EntranceModel fetchLastEntrance() {
        int greatestCode = EntranceModel.getGreatestCode();

        return EntranceModel.find(greatestCode);
    }

    @Override
    public void update() {
        System.err.println("Update function non implemented in " + this.getClass().getSimpleName());
    }
}
