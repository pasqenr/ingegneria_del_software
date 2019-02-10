package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a Sport, table <code>sport</code>.
 */
public class SportModel extends Model implements GenericDAO {
    private String name;

    /**
     * Create a new Sport.
     *
     * @param name A valid Sport name.
     */
    public SportModel(String name) {
        this.name = name;
    }

    public static SportModel getInstance() {
        return new SportModel(null);
    }

    /**
     * @param name The new Sport name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The Sport name.
     */
    public String getName() {
        return name;
    }

    @Override
    public SportModel find(String name) {
        SportModel sport = null;
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT s.nome FROM sport s WHERE s.nome LIKE ?";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String sportName = rs.getString("nome");
                sport = new SportModel(sportName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return sport;
    }

    @Override
    public List<SportModel> findAll() {
        List<SportModel> sports = new ArrayList<>();
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT s.nome FROM sport s";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String sportName = rs.getString("nome");
                sports.add(new SportModel(sportName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return sports;
    }

    @Override
    public boolean store() {
        return false;
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
