package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a Store, table <code>negozio</code>.
 */
public class StoreModel extends Model implements GenericDAO {
    private String code;
    private String name;
    private String address;
    private String city;

    /**
     * Create a new Store.
     *
     * @param code The Store unique code.
     * @param name The name.
     * @param address The address.
     * @param city The city.
     */
    public StoreModel(String code, String name, String address, String city) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public static StoreModel getInstance() {
        return new StoreModel(null, null, null, null);
    }

    /**
     * @param code The new code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param address The new address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param city The new city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    @Override
    public StoreModel find(String code) {
        String query = "SELECT n.codice_fiscale, n.nome, n.indirizzo, n.citta " +
                "FROM negozio n " +
                "WHERE  n.codice_fiscale LIKE ?";

        return findBy(query, code);
    }

    /**
     * Find the Store identified by some name.
     *
     * @param name A Store name.
     * @return The Store with that name.
     */
    public static StoreModel findByName(String name) {
        String query = "SELECT n.codice_fiscale, n.nome, n.indirizzo, n.citta " +
                "FROM negozio n " +
                "WHERE  n.nome LIKE ?";

        return findBy(query, name);
    }

    /**
     * Group the method to retrieve information using a query.
     *
     * @param query A query to fetch Store information.
     * @param field The field on which do the match.
     * @return A Store that matches or <code>null</code> if not results are given.
     */
    private static StoreModel findBy(String query, String field) {
        StoreModel store = null;
        DatabaseWrapper db = new DatabaseWrapper();
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, field);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            store = buildSingleFromResult(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return store;
    }

    @Override
    public List<StoreModel> findAll() {
        List<StoreModel> stores = new ArrayList<>();
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT n.codice_fiscale, n.nome, n.indirizzo, n.citta FROM negozio n";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stores.add(buildSingleFromResult(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return stores;
    }

    @Override
    public boolean store() {
        return false;
    }

    /**
     * Create only one Store using the data found in rs. The cursor is moved forward.
     *
     * @param rs The ResultSet containing the Store or Stores fetched from the database.
     * @return A new Store.
     */
    private static StoreModel buildSingleFromResult(ResultSet rs) {
        String code = null;
        String name = null;
        String address = null;
        String city = null;

        try {
            code = rs.getString("codice_fiscale");
            name = rs.getString("nome");
            address = rs.getString("indirizzo");
            city = rs.getString("citta");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new StoreModel(code, name, address, city);
    }
}
