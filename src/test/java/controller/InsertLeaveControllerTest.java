package controller;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsertLeaveControllerTest {
    @Test
    void addOrderTest() {
        int leaveNumber = 2;
        String orderCode = "ORD001";
        List<ArticleModel> articles = new ArrayList<>();
        articles.add(new ArticleModel("AC8503710",
                        new ArticleType("Scarpette e calze Acquagym",
                                "Ideato per le attività di acquagym dolce e acquafitness dinamico.",
                                "100.00% Cloruro di polivinile (PVC)",
                                new SportModel("Acquagym")),
                        "12.99",
                        "2012-01-22",
                        new PositionModel("A010204")));
        articles.add(new ArticleModel("AC8335706",
                new ArticleType("Materiale Acquagym",
                        "Guanti in neoprene che potenziano la superficie d'appoggio delle mani consentendoti di far lavorare efficacemente la parte alta del corpo.",
                        "100.00% Gomma - Cloroprene (CR) - Neoprene",
                        new SportModel("Acquagym")),
                "8.99",
                "2012-02-12",
                new PositionModel("A010301")));
        String date = "2019-02-05";
        StoreModel store = new StoreModel("NEG002", "Sport Center", "Via Verrara, 61", "Ostiglia");
        CourierModel courier = new CourierModel("UPS");

        new InsertLeaveController().addLeave(leaveNumber, orderCode, articles, date, store, courier);

        assertEquals(2, LeaveModel.getInstance().getLastId());
    }
}
