package controller;

import database.DatabaseWrapper;
import model.EntranceOrdersModel;
import model.OrdersModel;
import java.sql.ResultSet;

public class EntranceController {
    private EntranceOrdersModel ordersModel;

    public EntranceController() {
        DatabaseWrapper db = new DatabaseWrapper();

        ResultSet rs = fetchEntranceOrders(db);
        ordersModel = new EntranceOrdersModel(rs);

        db.close();
    }

    static public ResultSet fetchEntranceOrders(DatabaseWrapper db) {
        ResultSet rs;

        String query =
                "SELECT i.codice AS codice_ingresso, " +
                "i.data AS data_ingresso, " +
                "a.codice AS codice_articolo, " +
                "ta.nome, a.prezzo, a.posizione " +
                "FROM tipo_articolo AS ta " +
                "JOIN articolo a ON ta.nome = a.tipo_articolo " +
                "JOIN ingresso_articolo ia on a.codice = ia.codice_articolo " +
                "JOIN ingresso i on ia.codice_ingresso = i.codice " +
                "ORDER BY i.codice";

        rs = db.rawQuery(query);

        return rs;
    }

    public OrdersModel getOrdersModel() {
        return ordersModel;
    }
}
