package controller;

import database.DatabaseWrapper;
import model.PositionModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionController {
    private List<PositionModel> freePositions;
    private List<PositionModel> usedPositions;

    public List<PositionModel> getFreePositions() {
        if (freePositions == null) {
            freePositions = new ArrayList<>(retrieveFreePositions());
        }

        return freePositions;
    }

    public List<PositionModel> getUsedPositions() {
        if (usedPositions == null) {
            usedPositions = new ArrayList<>(retrieveUsedPositions());
        }

        return usedPositions;
    }

    /**
     * Force lists update, that is, fetch fresh data from the database.
     */
    public void update() {
        freePositions = new ArrayList<>(retrieveFreePositions());
        usedPositions = new ArrayList<>(retrieveUsedPositions());
    }

    private List<PositionModel> retrieveFreePositions() {
        String query = "SELECT p.id_posizione AS posizione " +
                "FROM posizione p " +
                "EXCEPT " +
                "SELECT a.posizione " +
                "FROM articolo a";

        return retrievePosition(query);
    }

    private List<PositionModel> retrieveUsedPositions() {
        String query = "SELECT a.posizione AS posizione " +
                "FROM articolo a";

        return retrievePosition(query);
    }

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
}
