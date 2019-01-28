package controller;

import database.DatabaseWrapper;
import model.LeaveOrdersModel;
import model.OrdersModel;
import java.sql.ResultSet;

public class LeaveController {
    private LeaveOrdersModel ordersModel;

    public LeaveController() {
        DatabaseWrapper db = new DatabaseWrapper();

        ResultSet rs = fetchEntranceOrders(db);
        ordersModel = new LeaveOrdersModel(rs);

        db.close();
    }

    static public ResultSet fetchEntranceOrders(DatabaseWrapper db) {
        ResultSet rs;

        String query =
                "SELECT o.numero_bolla, " +
                        "o.data AS data_ordine, " +
                        "a.codice AS codice_articolo, " +
                        "ta.nome, a.prezzo " +
                        "FROM tipo_articolo AS ta " +
                        "JOIN articolo a on ta.nome = a.tipo_articolo " +
                        "JOIN ordine_articolo oa on a.codice = oa.codice_articolo " +
                        "JOIN ordine o on oa.numero_bolla = o.numero_bolla " +
                        "ORDER BY o.numero_bolla";

        rs = db.rawQuery(query);

        return rs;
    }

    public OrdersModel getOrdersModel() {
        return ordersModel;
    }

    public static void main(String[] args) {
        System.out.println(new EntranceController().getOrdersModel());
    }
}
