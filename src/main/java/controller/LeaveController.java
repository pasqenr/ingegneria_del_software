package controller;

import database.DatabaseWrapper;
import model.LeaveOrdersModel;
import model.OrdersModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaveController {
    private LeaveOrdersModel ordersModel;

    public LeaveController() {
        DatabaseWrapper db = new DatabaseWrapper();

        ResultSet rs = fetchEntranceOrders(db);
        ordersModel = new LeaveOrdersModel(rs);

        db.close();
    }

    static public ResultSet fetchEntranceOrders(DatabaseWrapper db) {
        ResultSet rs = null;

        String query =
                "SELECT o.numero_bolla, " +
                        "o.data AS data_ordine, " +
                        "n.nome AS negozio, " +
                        "o.spedizioniere, " +
                        "a.codice AS codice_articolo, " +
                        "ta.nome, a.prezzo " +
                        "FROM tipo_articolo AS ta " +
                        "JOIN articolo a on ta.nome = a.tipo_articolo " +
                        "JOIN ordine_articolo oa on a.codice = oa.codice_articolo " +
                        "JOIN ordine o on oa.numero_bolla = o.numero_bolla " +
                        "JOIN negozio n on o.negozio = n.nome " +
                        "ORDER BY o.numero_bolla";

        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public OrdersModel getOrdersModel() {
        return ordersModel;
    }
}
