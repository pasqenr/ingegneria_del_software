package controller;

import database.DatabaseWrapper;
import model.ArticleModel;
import model.PositionModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MoveArticleController {
    private PositionController positionController;
    private ArticleController articleController;

    public MoveArticleController() {
        positionController = new PositionController();
        articleController = new ArticleController();
    }

    public String[] getFreePositions() {
        return positionModelsToStrings(positionController.getFreePositions());
    }

    public DefaultComboBoxModel<String> getFreePositionComboBoxModel() {
        return new DefaultComboBoxModel<>(getFreePositions());
    }

    public DefaultComboBoxModel<String> getArticleCodesComboBoxModel() {
        return new DefaultComboBoxModel<>(articleController.getArticlesCodes());
    }

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

    public void update() {
        articleController.update();
        positionController.update();
    }

    public ArticleModel getArticleByCode(String articleCode) {
        return articleController.getArticleByCode(articleCode);
    }

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
