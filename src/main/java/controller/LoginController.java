package controller;

import database.DatabaseWrapper;
import factories.FactoryProducer;
import model.UserModel;
import model.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static factories.FactoryProducer.FactoryType.STORE;

/**
 * Manage the user login.
 */
public class LoginController {
    private UserModel user;

    public LoginController() {
        this.user = null;
    }

    /**
     * Check for a match in the database for a pair of email and password. The user can have a store associated or
     * nothing.
     *
     * @param email The user email
     * @param password The user password.
     * @return A valid <code>User</code> if there's a match, <code>null</code> otherwise.
     */
    public UserModel login(final String email, final String password) {
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT u.email, u.password, u.ruolo, u.negozio FROM utente u " +
                "WHERE email LIKE ?" +
                "AND password LIKE ?" +
                "LIMIT 1";
        try {
            final PreparedStatement stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            final ResultSet rs = stmt.executeQuery();
            db.commit();

            if (rs.next()) {
                final String userEmail = rs.getString("email");
                final String role = rs.getString("ruolo");
                final String storeName = rs.getString("negozio");

                if (storeName == null) {
                    user = new UserModel(userEmail, UserRole.ofName(role));
                } else {
                    user = new UserModel(userEmail, UserRole.ofName(role),
                            FactoryProducer.getFactory(STORE).getStoreModel().find(storeName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return user;
    }

    /**
     * @param user A user.
     * @return <code>true</code> if the user is logged and valid, <code>false</code> otherwise.
     */
    public boolean isLogged(final UserModel user) {
        return user != null;
    }

    /**
     * @return <code>true</code> if the user is logged and valid, <code>false</code> otherwise.
     */
    public boolean isLogged() {
        return user != null;
    }
}
