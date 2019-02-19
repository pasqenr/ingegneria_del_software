package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represent a Courier, table <code>spedizioniere</code>.
 */
public class CourierModel extends Model implements GenericDAO {
    private final String name;

    /**
     * Create a new Courier.
     *
     * @param name A valid Courier name.
     */
    public CourierModel(final String name) {
        this.name = name;
    }

    /**
     * @return The name.
     */
    public String getName() {
        return name;
    }

    @Override
    public CourierModel find(String name) {
        CourierModel courier = null;
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT s.nome " +
                "FROM spedizioniere s " +
                "WHERE s.nome LIKE ?";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, name);
            final ResultSet rs = stmt.executeQuery();

            rs.next();

            courier = buildSingleFromResult(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return courier;
    }

    @Override
    public Collection<CourierModel> findAll() {
        final Collection<CourierModel> couriers = new ArrayList<>();
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT s.nome FROM spedizioniere s";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                couriers.add(buildSingleFromResult(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return couriers;
    }

    /**
     * Create only one Courier using the data found in rs. The cursor is moved forward.
     *
     * @param rs The ResultSet containing the Courier or Couriers fetched from the database.
     * @return A new Courier.
     */
    private CourierModel buildSingleFromResult(final ResultSet rs) {
        final String name;
        CourierModel courier = null;

        try {
            name = rs.getString("nome");

            courier = new CourierModel(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courier;
    }

    @Override
    public boolean store() {
        return false;
    }
}
