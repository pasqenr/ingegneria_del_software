package controller;

import database.DatabaseWrapper;
import model.PositionModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage the positions inside the warehouse.
 */
public class PositionController {
    /**
     * @return The free positions.
     */
    public List<PositionModel> getFreePositions() {
        final String query = "SELECT p.id_posizione AS posizione " +
                "FROM posizione p " +
                "EXCEPT " +
                "SELECT a.posizione " +
                "FROM articolo a";

        return retrievePosition(query);
    }

    /**
     * Returns the Position with the positionCode.
     *
     * @param positionCode A valid position code.
     * @return The Position associated to the positionCode.
     */
    public PositionModel findFreePositionByCode(final String positionCode) {
        final List<PositionModel> positions = getFreePositions();
        final PositionModel retPosition;

        retPosition = positions.stream().filter(position -> position.getCode().equals(positionCode))
                .findFirst()
                .orElse(null);

        if (retPosition != null) {
            positions.remove(retPosition);
        }

        return retPosition;
    }

    /**
     * Retrieve the positions based on the query passed as parameter.
     *
     * @param positionQuery A position query to select the wanted positions.
     * @return The list of Position returned by the positionQuery.
     */
    private List<PositionModel> retrievePosition(final String positionQuery) {
        final DatabaseWrapper db = new DatabaseWrapper();
        final List<PositionModel> fetchedFreePositions = new ArrayList<>();
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(positionQuery);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                fetchedFreePositions.add(new PositionModel(rs.getString("posizione")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return fetchedFreePositions;
    }
}
