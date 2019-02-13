package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleLeaveModelTest extends GenericModelTest {
    private final List<ArticleLeaveModel> articleLeaveModelListValid;

    ArticleLeaveModelTest() {
        int leaveNumber = 1;
        List<ArticleModel> articles = new ArrayList<>();
        articles.add(ArticleModel.getInstance().find("AC8402392"));
        articles.add(ArticleModel.getInstance().find("AC8342928"));
        ArticleLeaveModel articleLeaveModel = new ArticleLeaveModel(articles, leaveNumber);
        articleLeaveModelListValid = new ArrayList<>();
        articleLeaveModelListValid.add(articleLeaveModel);
    }

    @Test
    void findTest() {
        assertNull(ArticleLeaveModel.getInstance().find("000000"));
    }

    @Test
    void findAllTest() {
        assertEquals(articleLeaveModelListValid, ArticleLeaveModel.getInstance().findAll());
    }

    @Test
    void toStringTest() {
        assertEquals(articleLeaveModelListValid.toString(), ArticleLeaveModel.getInstance().findAll().toString());
    }
}
