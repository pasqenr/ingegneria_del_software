package model;

import database.DatabaseWrapper;
import factories.InstanceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represent an Order, table <code>ordine</code>.
 */
public class OrderModel extends Model implements GenericDAO {
    private final String code;
    private final String date;
    private final StoreModel store;

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
     * @return The date.
     */
    public String getDate() {
        return date;
    }

    /**
     * @return The Store.
     */
    public StoreModel getStore() {
        return store;
    }

    @Override
    public OrderModel find(String code) {
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT o.codice, o.data, o.negozio FROM ordine o WHERE o.codice LIKE ?";
        final PreparedStatement stmt;
        OrderModel order = null;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, code);
            final ResultSet rs = stmt.executeQuery();
            db.getCon().commit();

            if (rs.next()) {
                final String date = rs.getString("data");
                final StoreModel store = InstanceFactory.getInstance(StoreModel.class)
                        .find(rs.getString("negozio"));
                order = new OrderModel(code, date, store);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return order;
    }

    @Override
    public Collection<OrderModel> findAll() {
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT o.codice, o.data, o.negozio FROM ordine o";
        final PreparedStatement stmt;
        final Collection<OrderModel> orders = new ArrayList<>();

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            db.getCon().commit();

            while (rs.next()) {
                String code = rs.getString("codice");
                String date = rs.getString("data");
                StoreModel store = InstanceFactory.getInstance(StoreModel.class).find(rs.getString("negozio"));
                final OrderModel order = new OrderModel(code, date, store);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return orders;
    }

    public OrderModel fetchLast() {
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT o.codice, o.data, o.negozio FROM ordine o ORDER BY o.codice DESC LIMIT 1";
        final PreparedStatement stmt;
        OrderModel order = null;

        try {
            stmt = db.getCon().prepareStatement(query);
            final ResultSet rs = stmt.executeQuery();
            db.getCon().commit();

            if (rs.next()) {
                String code = rs.getString("codice");
                String date = rs.getString("data");
                StoreModel store = InstanceFactory.getInstance(StoreModel.class).find(rs.getString("negozio"));
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
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "INSERT INTO ordine (codice, data, negozio) VALUES (?, ?, ?)";
        final PreparedStatement stmt;

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

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  OrderModel)) {
            return false;
        }

        final OrderModel other = (OrderModel)o;

        return code.equals(other.code) &&
                date.equals(other.date) &&
                store.equals(other.store);
    }
}
