package controller;

import database.DatabaseWrapper;
import factories.FactoryProducer;
import model.ArticleModel;
import model.PositionModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static factories.FactoryProducer.FactoryType.ARTICLE;

/**
 * Manage the movement of articles inside the warehouse.
 */
public class MoveArticleController {
    private final PositionController positionController;

    /**
     * Create a new MoveArticleController.
     */
    public MoveArticleController() {
        positionController = new PositionController();
    }

    /**
     * @return A ComboBoxModel with the free positions.
     */
    public DefaultComboBoxModel<String> getFreePositionComboBoxModel() {
        final List<PositionModel> freePositions = positionController.getFreePositions();

        return new DefaultComboBoxModel<>(positionModelsToStrings(freePositions));
    }

    /**
     * @return A ComboBoxModel with all the positions.
     */
    public DefaultComboBoxModel<String> getArticleCodesComboBoxModel() {
        final ArticleModel article = FactoryProducer.getFactory(ARTICLE).getArticleModel();
        return new DefaultComboBoxModel<>(article.getArticlesCodes());
    }

    /**
     * Move the position of an article.
     *
     * @param articleCode A valid Article code.
     * @param positionId A valid position identifier.
     * @return <code>true</code> if the articles was moved, <code>false</code> otherwise.
     */
    public boolean moveArticlePositionByCodes(final String articleCode, final String positionId) {
        final DatabaseWrapper db = new DatabaseWrapper();
        final Connection con = db.getCon();
        final PreparedStatement updateArticleStmt;
        int affectedRows = 0;
        final String updateArticleQuery = "UPDATE articolo SET posizione = ? WHERE codice LIKE ?";

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
     * Returns an array of positions codes from a list of PositionFactory.
     *
     * @param positionModels A list of PositionFactory.
     * @return An array of PositionFactory codes.
     */
    private String[] positionModelsToStrings(final List<PositionModel> positionModels) {
        int i = 0;

        final String[] positions = new String[positionModels.size()];
        for (PositionModel p : positionModels) {
            positions[i] = p.getCode();
            i++;
        }

        return positions;
    }
}
