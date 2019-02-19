package controller;

import factories.FactoryProducer;
import model.ArticleModel;
import model.ArticleTypeModel;
import model.EntranceModel;
import model.PositionModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static factories.FactoryProducer.FactoryType.*;
import static org.junit.jupiter.api.Assertions.*;

class InsertEntranceControllerTest extends GenericControllerTest {

    @Test
    void insertArticlesAsEntranceTest() {
        final PositionController positionController = new PositionController();
        final InsertEntranceController insertEntranceController = new InsertEntranceController();

        final String[] articleCodes = new String[] {"AC8528090", "AC8528091"};
        final String[] articleTypes = new String[] {"Materiale Acquagym", "Materiale Acquagym"};
        final String[] articlePositions = new String[] {"A010106", "A010107"};
        final String[] articlePrices = new String[] {"12.99", "15.99"};
        final String[] articleProductionDates = new String[] {"2014-01-30", "2014-02-16"};
        List<ArticleModel> articles = new ArrayList<>();

        for (int i = 0; i < articleCodes.length; i++) {
            final ArticleTypeModel articleType = FactoryProducer.getFactory(ARTICLE_TYPE)
                    .getArticleTypeModel().find(articleTypes[i]);
            final PositionModel position = positionController.findFreePositionByCode(articlePositions[i]);

            assertNotNull(articleType);
            assertNotNull(position);

            articles.add(new ArticleModel(articleCodes[i],
                    articleType,
                    articlePrices[i],
                    articleProductionDates[i],
                    position));
        }

        insertEntranceController.insertArticlesAsEntrance(articles);

        final ArticleModel articleModelDao = FactoryProducer.getFactory(ARTICLE).getArticleModel();
        final ArticleModel article1 = articleModelDao.find(articleCodes[0]);
        final ArticleModel article2 = articleModelDao.find(articleCodes[1]);

        assertNotNull(article1);
        assertNotNull(article2);
        assertEquals(article1.getPosition().getCode(), articlePositions[0]);
        assertEquals(article2.getPosition().getCode(), articlePositions[1]);

        final EntranceModel dummyEntrance = FactoryProducer.getFactory(ENTRANCE).getEntranceModel();
        final EntranceModel entrance = dummyEntrance.find(dummyEntrance.getGreatestCode());

        assertEquals(dummyEntrance.getGreatestCode(), 2);
        assertNotNull(entrance);
        assertEquals(entrance.getCode(), 2);
    }

    @Test
    void checkEqualLengthsTest() {
        final InsertEntranceController insertEntranceController = new InsertEntranceController();
        final int[] lengthsValid = new int[] { 5, 5, 5, 5 };
        final int[] lengthsInvalid = new int[] { 1, 2, 3, 4 };

        assertTrue(insertEntranceController.checkEqualLengths(lengthsValid));
        assertFalse(insertEntranceController.checkEqualLengths(lengthsInvalid));
    }

    @Test
    void checkIsAlreadyStoredArticleCodeTest() {
        final InsertEntranceController insertEntranceController = new InsertEntranceController();
        final String[] articleCodesValid = new String[] {
                "AC8388175",
                "AC8402405"
        };
        final String[] articleCodesInvalid = new String[] {
                "AC000000",
                "AC999999"
        };

        assertFalse(insertEntranceController.checkIsAlreadyStoredArticleCodes(articleCodesValid));
        assertTrue(insertEntranceController.checkIsAlreadyStoredArticleCodes(articleCodesInvalid));
    }
}
