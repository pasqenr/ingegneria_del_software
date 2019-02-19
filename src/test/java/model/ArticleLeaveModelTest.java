package model;

import factories.FactoryProducer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static factories.FactoryProducer.FactoryType.ARTICLE;
import static factories.FactoryProducer.FactoryType.ARTICLE_LEAVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArticleLeaveModelTest extends GenericModelTest {
    private final List<ArticleLeaveModel> articleLeaveModelListValid;

    ArticleLeaveModelTest() {
        int leaveNumber = 1;
        List<ArticleModel> articles = new ArrayList<>();
        final ArticleModel articleModelInstance = FactoryProducer.getFactory(ARTICLE).getArticleModel();
        articles.add(articleModelInstance.find("AC8402392"));
        articles.add(articleModelInstance.find("AC8342928"));
        ArticleLeaveModel articleLeaveModel = new ArticleLeaveModel(articles, leaveNumber);
        articleLeaveModelListValid = new ArrayList<>();
        articleLeaveModelListValid.add(articleLeaveModel);
    }

    @Test
    void findTest() {
        assertNull(FactoryProducer.getFactory(ARTICLE_LEAVE).getArticleLeaveModel().find("000000"));
    }

    @Test
    void findAllTest() {
        assertEquals(articleLeaveModelListValid,
                FactoryProducer.getFactory(ARTICLE_LEAVE).getArticleLeaveModel().findAll());
    }

    @Test
    void toStringTest() {
        assertEquals(articleLeaveModelListValid.toString(),
                FactoryProducer.getFactory(ARTICLE_LEAVE).getArticleLeaveModel().findAll().toString());
    }
}
