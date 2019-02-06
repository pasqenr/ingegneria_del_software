package controller;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsertEntranceControllerTest {

    @Test
    void insertArticlesAsEntranceTest() {
        PositionController positionController = new PositionController();
        InsertEntranceController insertEntranceController = new InsertEntranceController();

        String[] articleCodes = new String[] {"AC8528090", "AC8528091"};
        String[] articleTypes = new String[] {"Materiale Acquagym", "Materiale Acquagym"};
        String[] articlePositions = new String[] {"A010106", "A010107"};
        String[] articlePrices = new String[] {"12.99", "15.99"};
        String[] articleProductionDates = new String[] {"2014-01-30", "2014-02-16"};
        List<ArticleModel> articles = new ArrayList<>();

        for (int i = 0; i < articleCodes.length; i++) {
            ArticleType articleType = ArticleType.find(articleTypes[i]);
            PositionModel position = positionController.findFreePositionByCode(articlePositions[i]);

            assertNotNull(articleType);
            assertNotNull(position);

            articles.add(new ArticleModel(articleCodes[i],
                    articleType,
                    articlePrices[i],
                    articleProductionDates[i],
                    position));
        }

        insertEntranceController.insertArticlesAsEntrance(articles);

        ArticleModel article1 = ArticleModel.find(articleCodes[0]);
        ArticleModel article2 = ArticleModel.find(articleCodes[1]);

        assertNotNull(article1);
        assertNotNull(article2);
        assertEquals(article1.getPosition().getRawPosition(), articlePositions[0]);
        assertEquals(article2.getPosition().getRawPosition(), articlePositions[1]);

        EntranceModel entrance = EntranceController.getEntranceByCode(EntranceModel.getGreatestCode());

        assertEquals(EntranceModel.getGreatestCode(), 2);
        assertNotNull(entrance);
        assertEquals(entrance.getCode(), 2);

    }
}
