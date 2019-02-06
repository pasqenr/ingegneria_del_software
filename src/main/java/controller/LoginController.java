package controller;

import database.DatabaseWrapper;
import model.StoreModel;
import model.UserModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private UserModel user;

    public UserModel login(String email, String password) {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT u.email, u.password, u.ruolo, u.negozio FROM utente u " +
                "WHERE email LIKE ?" +
                "AND password LIKE ?" +
                "LIMIT 1";
        try {
            PreparedStatement stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            db.commit();

            if (rs.next()) {
                String userEmail = rs.getString("email");
                String role = rs.getString("ruolo");
                String storeName = rs.getString("negozio");

                if (storeName == null) {
                    user = new UserModel(userEmail, role);
                } else {
                    user = new UserModel(userEmail, role, StoreModel.find(storeName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return user;
    }

    public boolean isLogged(UserModel user) {
        return user != null;
    }
}
