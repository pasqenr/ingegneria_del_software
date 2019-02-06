package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SportModel {
    private String name;

    public SportModel(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SportModel find(String name) {
        SportModel sport = null;
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT s.nome FROM sport s WHERE s.nome LIKE ?";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            String sportName = rs.getString("nome");
            sport = new SportModel(sportName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return sport;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  SportModel)) {
            return false;
        }

        SportModel sportModel = (SportModel)o;

        return name.equals(sportModel.name);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + name.hashCode();

        return result;
    }
}
