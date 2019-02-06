package controller;

import model.ArticleType;
import model.SportModel;

import java.util.List;

public class InsertArticleTypeController {

    public static void addAll(String[] articleTypesNames,
                              String[] articleTypeDescriptions,
                              String[] articleTypeMaterials,
                              String[] articleTypeSports) {
        for (int i = 0; i < articleTypesNames.length; i++) {
            ArticleType articleType = new ArticleType(
                    articleTypesNames[i],
                    articleTypeDescriptions[i],
                    articleTypeMaterials[i],
                    SportModel.find(articleTypeSports[i]));

            articleType.store();
        }
    }

    public static boolean areArticleTypesAlreadyStored(String[] articleTypes) {
        int matches = 0;
        List<ArticleType> articleTypeList = ArticleType.findAll();

        for (ArticleType articleType : articleTypeList) {
            for (String articleCode : articleTypes) {
                if (articleType.getName().equals(articleCode)) {
                    matches++;
                }
            }
        }

        return matches != 0;
    }
}
