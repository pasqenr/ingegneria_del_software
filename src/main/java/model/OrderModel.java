package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderModel extends Model {
    private int orderNumber;
    private String date;
    private StoreModel store;
    private CourierModel courier;

    public OrderModel(int orderNumber, String date, StoreModel store, CourierModel courier) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.store = store;
        this.courier = courier;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setStore(StoreModel store) {
        this.store = store;
    }

    public StoreModel getStore() {
        return store;
    }

    public void setCourier(CourierModel courier) {
        this.courier = courier;
    }

    public CourierModel getCourier() {
        return courier;
    }

    public static OrderModel find(int orderNumber) {
        OrderModel order = null;
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT o.numero_bolla, o.data, o.negozio, o.spedizioniere " +
                "FROM ordine o " +
                "WHERE o.numero_bolla = ?";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setInt(1, orderNumber);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            order = buildSingleFromResult(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return order;
    }

    public static List<OrderModel> findAll() {
        List<OrderModel> orders = new ArrayList<>();
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT o.numero_bolla, o.data, o.negozio, o.spedizioniere FROM ordine o";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orders.add(buildSingleFromResult(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return orders;
    }

    private static OrderModel buildSingleFromResult(ResultSet rs) {
        int orderNumber = 0;
        String date = null;
        StoreModel store = null;
        CourierModel courier = null;

        try {
            orderNumber = rs.getInt("numero_bolla");
            date = rs.getString("data");
            String storeName = rs.getString("negozio");
            String courierName = rs.getString("spedizioniere");

            store = StoreModel.findByName(storeName);
            courier = new CourierModel(courierName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new OrderModel(orderNumber, date, store, courier);
    }

    public static int getLastId() {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT o.numero_bolla FROM ordine o ORDER BY o.numero_bolla DESC LIMIT 1";
        PreparedStatement stmt;
        int lastOrderNumber = 0;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            lastOrderNumber = rs.getInt("numero_bolla");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return lastOrderNumber;
    }

    @Override
    public boolean store() {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "INSERT INTO ordine (numero_bolla, data, negozio, spedizioniere) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setInt(1, orderNumber);
            stmt.setString(2, date);
            stmt.setString(3, store.getCode());
            stmt.setString(4, courier.getName());
            stmt.execute();
            db.getCon().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return true;
    }
}
