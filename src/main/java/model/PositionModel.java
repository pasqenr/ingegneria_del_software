package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represent a Position, table <code>posizione</code>.
 */
public class PositionModel extends Model implements GenericDAO {
    private final String code;

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
    public String getCode() {
        return code;
    }

    @Override
    public PositionModel find(String code) {
        PositionModel position = null;
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT p.id_posizione FROM posizione p WHERE p.id_posizione = ?";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, code);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                position = buildSingleFromResult(rs, "id_posizione");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        db.close();

        return position;
    }

    @Override
    public Collection<PositionModel> findAll() {
        final Collection<PositionModel> positions = new ArrayList<>();
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT a.codice, a.tipo_articolo, a.prezzo, a.data_produzione, a.posizione " +
                "FROM articolo a";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PositionModel article = buildSingleFromResult(rs, "posizione");
                positions.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return positions;
    }

    /**
     * Create only one Position using the data found in rs. The cursor is moved forward.
     *
     * @param rs The ResultSet containing the Position or Positions fetched from the database.
     * @return A new Positon.
     */
    private PositionModel buildSingleFromResult(ResultSet rs, String... fields) {
        String code = null;

        try {
            code = rs.getString(fields[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new PositionModel(code);
    }

    @Override
    public boolean store() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  PositionModel)) {
            return false;
        }

        final PositionModel position = (PositionModel)o;

        return code.equals(position.code);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + code.hashCode();

        return result;
    }
}
