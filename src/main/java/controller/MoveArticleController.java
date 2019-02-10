package controller;

import database.DatabaseWrapper;
import model.ArticleModel;
import model.PositionModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Manage the movement of articles inside the warehouse.
 */
public class MoveArticleController {
    private PositionController positionController;

    /**
     * Create a new MoveArticleController.
     */
    public MoveArticleController() {
        positionController = new PositionController();
    }

    /**
     * @return Return an array with the codes of the free positions in the warehouse.
     */
    public String[] getFreePositions() {
        return positionModelsToStrings(positionController.getFreePositions());
    }

    /**
     * @return A ComboBoxModel with the free positions.
     */
    public DefaultComboBoxModel<String> getFreePositionComboBoxModel() {
        return new DefaultComboBoxModel<>(getFreePositions());
    }

    /**
     * @return A ComboBoxModel with all the positions.
     */
    public DefaultComboBoxModel<String> getArticleCodesComboBoxModel() {
        return new DefaultComboBoxModel<>(ArticleModel.getInstance().getArticlesCodes());
    }

    /**
     * Move the position of an article.
     *
     * @param articleCode A valid Article code.
     * @param positionId A valid position identifier.
     * @return <code>true</code> if the articles was moved, <code>false</code> otherwise.
     */
    public boolean moveArticlePositionByCodes(String articleCode, String positionId) {
        DatabaseWrapper db = new DatabaseWrapper();
        Connection con = db.getCon();
        PreparedStatement updateArticleStmt;
        int affectedRows = 0;

        String updateArticleQuery = "UPDATE articolo SET posizione = ? WHERE codice LIKE ?";

        try {
            updateArticleStmt = con.prepareStatement(updateArticleQuery);
            updateArticleStmt.setString(1, positionId);
            updateArticleStmt.setString(2, articleCode);
            affectedRows = updateArticleStmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            db.close();
        }

        return affectedRows != 0;
    }

    /**
     * Update the cached positions.
     */
    public void update() {
        positionController.update();
    }

    /**
     * Returns an array of positions codes from a list of PositionModel.
     *
     * @param positionModels A list of PositionModel.
     * @return An array of PositionModel codes.
     */
    private String[] positionModelsToStrings(List<PositionModel> positionModels) {
        int i = 0;

        String[] positions = new String[positionModels.size()];
        for (PositionModel p : positionModels) {
            positions[i] = p.getRawPosition();
            i++;
        }

        return positions;
    }
}
