package controller;

import database.DatabaseWrapper;
import model.OrdersModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manage the leaves table visualization.
 */
public class LeaveController {
    private OrdersModel ordersModel;

    /**
     * Create a new LeaveController.
     */
    public LeaveController() {
        DatabaseWrapper db = new DatabaseWrapper();

        ResultSet rs = fetchEntranceOrders(db);
        ordersModel = new OrdersModel(new String[] {
                "numero_bolla",
                "data_ordine",
                "codice_articolo",
                "nome",
                "prezzo"
        }, rs);

        db.close();
    }

    /**
     * Fetch from the database the orders that will populate the <code>ordersModel</code> table.
     *
     * @param db A <code>DatabaseWrapper</code> instance.
     * @return The <code>ResultSet</code> of the orders used to populate <code>ordersModel</code> table.
     */
    private ResultSet fetchEntranceOrders(DatabaseWrapper db) {
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

    /**
     * @return A <code>OrdersModel</code> that can be used to generate a <code>TableModel</code>.
     */
    public OrdersModel getOrdersModel() {
        return ordersModel;
    }
}
