package controller;

import database.DatabaseWrapper;
import model.OrdersModel;
import model.StoreModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manage the orders history for table visualization.
 */
public class OrderHistoryController {
    private final OrdersModel ordersModel;
    private final StoreModel store;

    /**
     * Create a new OrderHistoryController.
     *
     * @param store A valid Store.
     */
    public OrderHistoryController(final StoreModel store) {
        this.store = store;
        final DatabaseWrapper db = new DatabaseWrapper();

        final ResultSet rs = fetchOrders(db);
        ordersModel = new OrdersModel(new String[] { "codice_ordine", "data_ordine", "tipo_articolo" },
                rs,
                false);

        db.close();
    }

    /**
     * Fetch from the database the orders that will populate the <code>ordersModel</code> table.
     *
     * @param db A <code>DatabaseWrapper</code> instance.
     * @return The <code>ResultSet</code> of the orders used to populate <code>ordersModel</code> table.
     */
    private ResultSet fetchOrders(final DatabaseWrapper db) {
        ResultSet rs = null;
        final PreparedStatement stmt;
        final String query = "SELECT o.codice AS codice_ordine, o.data AS data_ordine, " +
                "ota.nome_tipo_articolo AS tipo_articolo " +
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

    /**
     * @return A <code>OrdersModel</code> that can be used to generate a <code>TableModel</code>.
     */
    public OrdersModel getOrdersModel() {
        return ordersModel;
    }
}
