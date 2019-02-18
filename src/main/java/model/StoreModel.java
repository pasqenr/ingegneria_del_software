package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represent a Store, table <code>negozio</code>.
 */
public class StoreModel extends Model implements GenericDAO {
    private final String code;
    private final String name;
    private final String address;
    private final String city;

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

    /**
     * @return The code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    @Override
    public StoreModel find(String code) {
        final String query = "SELECT n.codice_fiscale, n.nome, n.indirizzo, n.citta " +
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
        final String query = "SELECT n.codice_fiscale, n.nome, n.indirizzo, n.citta " +
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
        final DatabaseWrapper db = new DatabaseWrapper();
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, field);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                store = buildSingleFromResult(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return store;
    }

    @Override
    public Collection<StoreModel> findAll() {
        final Collection<StoreModel> stores = new ArrayList<>();
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT n.codice_fiscale, n.nome, n.indirizzo, n.citta FROM negozio n";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            final ResultSet rs = stmt.executeQuery();

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

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  StoreModel)) {
            return false;
        }

        final StoreModel other = (StoreModel) o;

        return code.equals(other.code) &&
                name.equals(other.name) &&
                address.equals(other.address) &&
                city.equals(other.city);
    }
}
