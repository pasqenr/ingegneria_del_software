package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Represent a (order) Fulfillment, table <code>evasione</code>.
 */
public class FulfillmentModel extends Model implements GenericDAO {
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

    public static FulfillmentModel getInstance() {
        return new FulfillmentModel(null, 0);
    }

    @Override
    public FulfillmentModel find(String id) {
        return null;
    }

    @Override
    public List<FulfillmentModel> findAll() {
        return null;
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
