package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourierModel extends Model {
    private String name;

    CourierModel(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CourierModel find(String name) {
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

    public static List<CourierModel> findAll() {
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

    private static CourierModel buildSingleFromResult(ResultSet rs) {
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
