package controller;

import database.DatabaseWrapper;
import model.OrdersModel;
import model.StoreModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderHistoryController {
    private OrdersModel ordersModel;
    private StoreModel store;

    public OrderHistoryController(StoreModel store) {
        this.store = store;
        DatabaseWrapper db = new DatabaseWrapper();

        ResultSet rs = fetchOrders(db);
        ordersModel = new OrdersModel(new String[] { "codice_ordine", "data_ordine", "nome_articolo" },
                rs,
                false);

        db.close();
    }

    private ResultSet fetchOrders(DatabaseWrapper db) {
        ResultSet rs = null;
        PreparedStatement stmt;
        String query = "SELECT o.codice AS codice_ordine, o.data AS data_ordine, " +
                "ota.nome_tipo_articolo AS nome_articolo " +
                "FROM ordine o " +
                "JOIN ordine_tipo_articolo ota ON o.codice = ota.codice_ordine " +
                "JOIN negozio n ON o.negozio = n.codice_fiscale " +
                "WHERE n.codice_fiscale LIKE ? " +
                "ORDER BY o.data";

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, store.getCode());
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
