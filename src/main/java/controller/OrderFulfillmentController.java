package controller;

import database.DatabaseWrapper;

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
        tableModel.addColumn("codice_ordine");
        tableModel.addColumn("numbero_bolla");
        tableModel.addColumn("data");
        tableModel.addColumn("negozio");
        tableModel.addColumn("spedizioniere");

        populateTableModel();
    }

    /**
     * Populate the rows of the internal TableModel with the orders fulfillment found in the database.
     */
    private void populateTableModel() {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT o.codice AS codice_ordine," +
                " u.numero_bolla, " +
                "u.data, " +
                "n.nome AS negozio, " +
                "s.nome AS spedizioniere " +
                "FROM uscita u " +
                "JOIN negozio n ON u.negozio = n.codice_fiscale " +
                "JOIN spedizioniere s ON u.spedizioniere = s.nome " +
                "JOIN ordine o on n.codice_fiscale = o.negozio ";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String orderCode = rs.getString("codice_ordine");
                String leaveNumber = rs.getString("numero_bolla");
                String date = rs.getString("data");
                String storeName = rs.getString("negozio");
                String courierName = rs.getString("spedizioniere");
                tableModel.addRow(new Object[] {orderCode, leaveNumber, date, storeName, courierName});
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
