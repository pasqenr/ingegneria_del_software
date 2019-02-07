package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Represent a (order) Fulfillment, table <code>evasione</code>.
 */
public class FulfillmentModel extends Model {
    private String orderCode;
    private int leaveNumber;

    /**
     * Create a new Fulfillment.
     *
     * @param orderCode An unique Order code.
     * @param leaveNumber An unique Leave number.
     */
    public FulfillmentModel(String orderCode, int leaveNumber) {
        this.orderCode = orderCode;
        this.leaveNumber = leaveNumber;
    }

    @Override
    public boolean store() {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "INSERT INTO evasione (codice_ordine, numero_bolla) VALUES (?, ?)";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, orderCode);
            stmt.setInt(2, leaveNumber);
            stmt.execute();
            db.getCon().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return true;
    }
}
