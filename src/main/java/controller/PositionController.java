package controller;

import database.DatabaseWrapper;
import model.PositionModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage the positions inside the warehouse.
 */
public class PositionController {
    private List<PositionModel> freePositions;

    /**
     * @return The free positions.
     */
    public List<PositionModel> getFreePositions() {
        if (freePositions == null) {
            freePositions = new ArrayList<>(retrieveFreePositions());
        }

        return freePositions;
    }

    /**
     * Update the internal cache of positions.
     */
    public void update() {
        freePositions = new ArrayList<>(retrieveFreePositions());
    }

    /**
     * @return A list of free Position.
     */
    private List<PositionModel> retrieveFreePositions() {
        String query = "SELECT p.id_posizione AS posizione " +
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
    public PositionModel findFreePositionByCode(String positionCode) {
        if (freePositions == null) {
            freePositions = retrieveFreePositions();
        }

        List<PositionModel> positions = freePositions;
        PositionModel retPosition;

        retPosition = positions.stream().filter(position -> position.getRawPosition().equals(positionCode))
                .findFirst()
                .orElse(null);

        if (retPosition != null) {
            positions.remove(retPosition);
        }

        return retPosition;
    }

    /**
     * @return A list of all the Position.
     */
    private List<PositionModel> fetchAllPositions() {
        String query = "SELECT p.id_posizione AS posizione FROM posizione p";

        return retrievePosition(query);
    }

    /**
     * Retrieve the positions based on the query passed as parameter.
     *
     * @param positionQuery A position query to select the wanted positions.
     * @return The list of Position returned by the positionQuery.
     */
    private List<PositionModel> retrievePosition(String positionQuery) {
        DatabaseWrapper db = new DatabaseWrapper();
        List<PositionModel> fetchedFreePositions = new ArrayList<>();

        ResultSet rs = db.rawQuery(positionQuery);

        try {
            while (rs.next()) {
                fetchedFreePositions.add(new PositionModel(rs.getString("posizione")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return fetchedFreePositions;
    }

    /**
     * Return the Position identified by the positionCode.
     *
     * @param positionCode A valid position code.
     * @return The Position identified by the positionCode.
     */
    public PositionModel findPositionByCode(String positionCode) {
        List<PositionModel> positions = fetchAllPositions();

        return positions.stream().filter(position -> position.getRawPosition().equals(positionCode))
                .findFirst()
                .orElse(null);
    }
}
