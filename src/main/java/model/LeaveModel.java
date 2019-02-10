package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a Leave, table <code>uscita</code>.
 */
public class LeaveModel extends Model implements GenericDAO {
    private int leaveNumber;
    private String date;
    private StoreModel store;
    private CourierModel courier;

    /**
     * Create a new Leave.
     *
     * @param leaveNumber An unique Leave number.
     * @param date A valid date.
     * @param store A Store.
     * @param courier A Courier.
     */
    public LeaveModel(int leaveNumber, String date, StoreModel store, CourierModel courier) {
        this.leaveNumber = leaveNumber;
        this.date = date;
        this.store = store;
        this.courier = courier;
    }

    public static LeaveModel getInstance() {
        return new LeaveModel(0, null, null, null);
    }

    /**
     * @return The unique Leave number.
     */
    public int getLeaveNumber() {
        return leaveNumber;
    }

    /**
     * @param leaveNumber The new unique Leave number.
     */
    public void setLeaveNumber(int leaveNumber) {
        this.leaveNumber = leaveNumber;
    }

    /**
     * @param date The new date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The date.
     */
    public String getDate() {
        return date;
    }

    /**
     * @param store The new Store.
     */
    public void setStore(StoreModel store) {
        this.store = store;
    }

    /**
     * @return The Store.
     */
    public StoreModel getStore() {
        return store;
    }

    /**
     * @param courier The new Courier.
     */
    public void setCourier(CourierModel courier) {
        this.courier = courier;
    }

    /**
     * @return The Courier.
     */
    public CourierModel getCourier() {
        return courier;
    }

    /**
     * Find the Leave identified by the unique leaveNumber.
     *
     * @param leaveNumber The unique number of the Leave.
     * @return The Leave with that leaveNumber.
     */
    public LeaveModel find(int leaveNumber) {
        LeaveModel order = null;
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT u.numero_bolla, u.data, u.negozio, u.spedizioniere " +
                "FROM uscita u " +
                "WHERE u.numero_bolla = ?";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setInt(1, leaveNumber);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            order = buildSingleFromResult(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return order;
    }

    @Override
    public LeaveModel find(String leaveNumber) {
        return LeaveModel.getInstance().find(Integer.valueOf(leaveNumber));
    }

    @Override
    public List<LeaveModel> findAll() {
        List<LeaveModel> orders = new ArrayList<>();
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT u.numero_bolla, u.data, u.negozio, u.spedizioniere FROM uscita u";
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

    /**
     * Create only one Leave using the data found in rs. The cursor is moved forward.
     *
     * @param rs The ResultSet containing the Leave or Leaves fetched from the database.
     * @return A new Leave.
     */
    private LeaveModel buildSingleFromResult(ResultSet rs) {
        int leaveNumber = 0;
        String date = null;
        StoreModel store = null;
        CourierModel courier = null;

        try {
            leaveNumber = rs.getInt("numero_bolla");
            date = rs.getString("data");
            String storeName = rs.getString("negozio");
            String courierName = rs.getString("spedizioniere");

            store = StoreModel.findByName(storeName);
            courier = new CourierModel(courierName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LeaveModel(leaveNumber, date, store, courier);
    }

    /**
     * Returns the last inserted number of the Leaves.
     *
     * @return The number of the newest Leave in the database.
     */
    public int getLastId() {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT o.numero_bolla FROM uscita o ORDER BY o.numero_bolla DESC LIMIT 1";
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
        String query = "INSERT INTO uscita (numero_bolla, data, negozio, spedizioniere) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setInt(1, leaveNumber);
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
