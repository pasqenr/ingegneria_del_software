package controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EntranceArticleControllerTest {
    @Test
    void fetchAllTest() {
        EntranceArticleController entranceArticleController = new EntranceArticleController();
        entranceArticleController.getEntranceArticles().forEach(System.out::println);
    }
}
