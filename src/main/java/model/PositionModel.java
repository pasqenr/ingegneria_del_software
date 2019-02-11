package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Represent a Position, table <code>posizione</code>.
 */
public class PositionModel extends Model implements GenericDAO {
    private String code;

    /**
     * Create a new Position.
     *
     * @param code A valid Position code.
     */
    public PositionModel(String code) {
        this.code = code;
    }

    /**
     * @return The String representation of the Position, that is, the Position identifier.
     */
    public String getRawPosition() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  PositionModel)) {
            return false;
        }

        PositionModel position = (PositionModel)o;

        return code.equals(position.code);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + code.hashCode();

        return result;
    }

    @Override
    public PositionModel find(String code) {
        PositionModel position = null;
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT p.id_posizione FROM posizione p WHERE p.id_posizione = ?";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                position = new PositionModel(rs.getString("id_posizione"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        db.close();

        return position;
    }

    @Override
    public List<PositionModel> findAll() {
        return null;
    }

    @Override
    public boolean store() {
        return false;
    }
}
