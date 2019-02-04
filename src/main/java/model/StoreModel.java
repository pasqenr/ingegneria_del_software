package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreModel extends Model {
    private String code;
    private String name;
    private String address;
    private String city;

    public StoreModel(String code, String name, String address, String city) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public static StoreModel find(String code) {
        String query = "SELECT n.codice_fiscale, n.nome, n.indirizzo, n.citta " +
                "FROM negozio n " +
                "WHERE  n.codice_fiscale LIKE ?";

        return findBy(query, code);
    }

    public static StoreModel findByName(String name) {
        String query = "SELECT n.codice_fiscale, n.nome, n.indirizzo, n.citta " +
                "FROM negozio n " +
                "WHERE  n.nome LIKE ?";

        return findBy(query, name);
    }

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

    public static List<StoreModel> findAll() {
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
    public boolean store() {
        return false;
    }
}
