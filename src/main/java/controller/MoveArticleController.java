package controller;

import model.PositionModel;

import javax.swing.*;
import java.util.List;

public class MoveArticleController {
    PositionController positionController;
    ArticleController articleController;

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
