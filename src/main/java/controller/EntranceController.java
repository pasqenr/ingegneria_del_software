package controller;

import database.DatabaseWrapper;
import model.EntranceOrdersModel;
import model.OrdersModel;

import java.sql.ResultSet;

/**
 * Manage the entrances' table visualization.
 */
public class EntranceController {
    private EntranceOrdersModel ordersModel;

    /**
     * Create a new <code>EntranceController</code>.
     */
    public EntranceController() {
        DatabaseWrapper db = new DatabaseWrapper();

        ResultSet rs = fetchEntranceOrders(db);
        ordersModel = new EntranceOrdersModel(rs);

        db.close();
    }

    /**
     * Fetch from the database the orders that will populate the <code>ordersModel</code> table.
     *
     * @param db A <code>DatabaseWrapper</code> instance.
     * @return The <code>ResultSet</code> of the orders used to populate <code>ordersModel</code> table.
     */
    private static ResultSet fetchEntranceOrders(DatabaseWrapper db) {
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

    /**
     * @return A <code>OrdersModel</code> that can be used to generate a <code>TableModel</code>.
     */
    public OrdersModel getOrdersModel() {
        return ordersModel;
    }
}
