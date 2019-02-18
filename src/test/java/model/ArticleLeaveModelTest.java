package model;

import factories.InstanceFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleLeaveModelTest extends GenericModelTest {
    private final List<ArticleLeaveModel> articleLeaveModelListValid;

    ArticleLeaveModelTest() {
        int leaveNumber = 1;
        List<ArticleModel> articles = new ArrayList<>();
        final ArticleModel articleModelInstance = InstanceFactory.getInstance(ArticleModel.class);
        articles.add(articleModelInstance.find("AC8402392"));
        articles.add(articleModelInstance.find("AC8342928"));
        ArticleLeaveModel articleLeaveModel = new ArticleLeaveModel(articles, leaveNumber);
        articleLeaveModelListValid = new ArrayList<>();
        articleLeaveModelListValid.add(articleLeaveModel);
    }

    @Test
    void findTest() {
        assertNull(InstanceFactory.getInstance(ArticleLeaveModel.class).find("000000"));
    }

    @Test
    void findAllTest() {
        assertEquals(articleLeaveModelListValid, InstanceFactory.getInstance(ArticleLeaveModel.class).findAll());
    }

    @Test
    void toStringTest() {
        assertEquals(articleLeaveModelListValid.toString(), InstanceFactory.getInstance(ArticleLeaveModel.class).findAll().toString());
    }
}
