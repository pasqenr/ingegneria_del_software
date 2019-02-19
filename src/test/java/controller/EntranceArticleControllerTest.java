package controller;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntranceArticleControllerTest extends GenericControllerTest {
    @Test
    void fetchAllTest() {
        final List<ArticleModel> articles = new ArrayList<>();
        articles.add(new ArticleModel("AC8388175",
                new ArticleTypeModel("Costumi Acquagym",
                        "Questo costume da bagno ideato per la pratica dell'aquagym offre un ottimo sostegno al petto, durante i diversi esercizi.",
                        "70.00% Poliammide (PA), 30.00% Elastan",
                        new SportModel("Acquagym")),
                "9.99",
                "2011-03-30",
                new PositionModel("A010101")));
        articles.add(new ArticleModel("AC8402405",
                new ArticleTypeModel("Costumi Acquagym",
                        "Questo costume da bagno ideato per la pratica dell'aquagym offre un ottimo sostegno al petto, durante i diversi esercizi.",
                        "70.00% Poliammide (PA), 30.00% Elastan",
                        new SportModel("Acquagym")),
                "7.99",
                "2018-07-21",
                new PositionModel("A010102")));
        articles.add(new ArticleModel("AC8528089",
                new ArticleTypeModel("Scarpette e calze Acquagym",
                        "Ideato per le attività di acquagym dolce e acquafitness dinamico.",
                        "100.00% Cloruro di polivinile (PVC)",
                        new SportModel("Acquagym")),
                "5.99",
                "2013-02-12",
                new PositionModel("A010201")));
        EntranceModel entrance = new EntranceModel(1, "2017-10-03");
        final EntranceArticleModel entranceArticleModel = new EntranceArticleModel(articles, entrance);
        List<EntranceArticleModel> entranceArticleModels = new ArrayList<>();
        entranceArticleModels.add(entranceArticleModel);

        EntranceArticleController entranceArticleController = new EntranceArticleController();
        List<EntranceArticleModel> fetchedEntranceArticleModels = entranceArticleController.getEntranceArticles();

        assertEquals(entranceArticleModels, fetchedEntranceArticleModels);
    }
}
