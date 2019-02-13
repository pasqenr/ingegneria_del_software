package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleLeaveModelTest extends GenericModelTest {
    @Test
    void findTest() {
        assertNull(ArticleLeaveModel.getInstance().find("000000"));
    }

    @Test
    void findAllTest() {
        final int leaveNumber = 1;
        final List<ArticleModel> articles = new ArrayList<>();
        articles.add(ArticleModel.getInstance().find("AC8402392"));
        articles.add(ArticleModel.getInstance().find("AC8342928"));
        final ArticleLeaveModel articleLeaveModel = new ArticleLeaveModel(articles, leaveNumber);
        final List<ArticleLeaveModel> articleLeaveModelListValid = new ArrayList<>();
        articleLeaveModelListValid.add(articleLeaveModel);

        assertEquals(articleLeaveModelListValid, ArticleLeaveModel.getInstance().findAll());
    }
}
