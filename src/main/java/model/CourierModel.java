package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a Courier, table <code>spedizioniere</code>.
 */
public class CourierModel extends Model implements GenericDAO {
    private String name;

    /**
     * Create a new Courier.
     *
     * @param name A valid Courier name.
     */
    public CourierModel(String name) {
        this.name = name;
    }

    public static CourierModel getInstance() {
        return new CourierModel(null);
    }

    /**
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public CourierModel find(String name) {
        CourierModel courier = null;
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT s.nome " +
                "FROM spedizioniere s " +
                "WHERE s.nome LIKE ?";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            courier = buildSingleFromResult(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return courier;
    }

    @Override
    public List<CourierModel> findAll() {
        List<CourierModel> couriers = new ArrayList<>();
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT s.nome FROM spedizioniere s";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

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
    private CourierModel buildSingleFromResult(ResultSet rs) {
        String name = null;
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
