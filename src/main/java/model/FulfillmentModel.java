package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Represent a (order) Fulfillment, table <code>evasione</code>.
 */
public class FulfillmentModel extends Model implements GenericDAO {
    private final String orderCode;
    private final int leaveNumber;

    /**
     * Create a new Fulfillment.
     *
     * @param orderCode An unique Order code.
     * @param leaveNumber An unique Leave number.
     */
    public FulfillmentModel(final String orderCode, final int leaveNumber) {
        this.orderCode = orderCode;
        this.leaveNumber = leaveNumber;
    }

    @Override
    public FulfillmentModel find(final String id) {
        return null;
    }

    @Override
    public Collection<FulfillmentModel> findAll() {
        return null;
    }

    @Override
    public boolean store() {
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "INSERT INTO evasione (codice_ordine, numero_bolla) VALUES (?, ?)";
        final PreparedStatement stmt;

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
