package controller;

import database.DatabaseWrapper;
import model.OrderModel;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manage a Order Fulfillment.
 */
public class OrderFulfillmentController {
    private DefaultTableModel tableModel;

    /**
     * Create a new OrderFulfillmentController.
     */
    public OrderFulfillmentController() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("leave_number");
        tableModel.addColumn("date");
        tableModel.addColumn("store");
        tableModel.addColumn("courier");
    }

    /**
     * Populate the rows of the internal TableModel with the orders fulfillment found in the database.
     *
     * @param order A valid Order from which search the fulfillment.
     */
    public void populateTableModel(OrderModel order) {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT u.numero_bolla, u.data, n.nome AS negozio, s.nome AS spedizioniere " +
                "FROM uscita u " +
                "JOIN negozio n ON u.negozio = n.codice_fiscale " +
                "JOIN spedizioniere s ON u.spedizioniere = s.nome " +
                "JOIN ordine o on n.codice_fiscale = o.negozio " +
                "WHERE o.codice LIKE ?";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, order.getCode());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String leaveNumber = rs.getString("numero_bolla");
                String date = rs.getString("data");
                String storeName = rs.getString("negozio");
                String courierName = rs.getString("spedizioniere");
                tableModel.addRow(new Object[] {leaveNumber, date, storeName, courierName});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();
    }

    /**
     * @return The internal TableModel.
     */
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
