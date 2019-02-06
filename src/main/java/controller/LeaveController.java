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
                "SELECT u.numero_bolla, " +
                        "u.data AS data_ordine, " +
                        "n.nome AS negozio, " +
                        "u.spedizioniere, " +
                        "a.codice AS codice_articolo, " +
                        "ta.nome, a.prezzo " +
                        "FROM tipo_articolo AS ta " +
                        "JOIN articolo a on ta.nome = a.tipo_articolo " +
                        "JOIN uscita_articolo ua on a.codice = ua.codice_articolo " +
                        "JOIN uscita u on ua.numero_bolla = u.numero_bolla " +
                        "JOIN negozio n on u.negozio = n.nome " +
                        "ORDER BY u.numero_bolla";

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
