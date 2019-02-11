package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public static PositionModel getInstance() {
        return new PositionModel(null);
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
                position = buildSingleFromResult(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        db.close();

        return position;
    }

    @Override
    public List<PositionModel> findAll() {
        List<PositionModel> positions = new ArrayList<>();
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT a.codice, a.tipo_articolo, a.prezzo, a.data_produzione, a.posizione FROM articolo a";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PositionModel article = buildSingleFromResult(rs);
                positions.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return positions;
    }

    @Override
    public boolean store() {
        return false;
    }

    /**
     * Create only one Position using the data found in rs. The cursor is moved forward.
     *
     * @param rs The ResultSet containing the Position or Positions fetched from the database.
     * @return A new Positon.
     */
    private PositionModel buildSingleFromResult(ResultSet rs) {
        String code = null;

        try {
            code = rs.getString("id_posizione");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new PositionModel(code);
    }
}
