package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represent a Sport, table <code>sport</code>.
 */
public class SportModel extends Model implements GenericDAO {
    private final String name;

    /**
     * Create a new Sport.
     *
     * @param name A valid Sport name.
     */
    public SportModel(String name) {
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
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT s.nome FROM sport s WHERE s.nome LIKE ?";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, name);
            final ResultSet rs = stmt.executeQuery();

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
    public Collection<SportModel> findAll() {
        final Collection<SportModel> sports = new ArrayList<>();
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT s.nome FROM sport s";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            final ResultSet rs = stmt.executeQuery();

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

        final SportModel sportModel = (SportModel)o;

        return name.equals(sportModel.name);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + name.hashCode();

        return result;
    }
}
