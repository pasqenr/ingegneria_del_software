package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represent an Order, table <code>ordine</code>.
 */
public class OrderModel extends Model {
    private String code;
    private String date;
    private StoreModel store;

    /**
     * Create a new Order.
     *
     * @param code An unique code.
     * @param date A valid date.
     * @param store A Store.
     */
    public OrderModel(String code, String date, StoreModel store) {
        this.code = code;
        this.date = date;
        this.store = store;
    }

    /**
     * @return The code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The new code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The date.
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The new date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The Store.
     */
    public StoreModel getStore() {
        return store;
    }

    /**
     * @param store The new Store.
     */
    public void setStore(StoreModel store) {
        this.store = store;
    }

    /**
     * Find the Order identified by the unique code.
     *
     * @param code The unique code of the Order.
     * @return The Order identified by code.
     */
    public static OrderModel find(String code) {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT o.codice, o.data, o.negozio FROM ordine o WHERE o.codice LIKE ?";
        PreparedStatement stmt;
        OrderModel order = null;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            db.getCon().commit();

            if (rs.next()) {
                String date = rs.getString("data");
                StoreModel store = StoreModel.find(rs.getString("negozio"));
                order = new OrderModel(code, date, store);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return order;
    }

    @Override
    public boolean store() {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "INSERT INTO ordine (codice, data, negozio) VALUES (?, ?, ?)";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, code);
            stmt.setString(2, date);
            stmt.setString(3, store.getCode());
            stmt.execute();
            db.getCon().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return true;
    }
}
