package controller;

import model.ArticleType;
import model.SportModel;

import java.util.List;

/**
 * Manage the insertion of a list of ArticleType
 */
public class InsertArticleTypeController {

    /**
     * Add all the ArticleType that can build using the parameters information.
     *
     * @param articleTypesNames An array of valid ArticleType names.
     * @param articleTypeDescriptions An array of ArticleType description.
     * @param articleTypeMaterials An array of ArticleType materials.
     * @param articleTypeSports An array of valid Sport names.
     */
    public static void addAll(String[] articleTypesNames,
                              String[] articleTypeDescriptions,
                              String[] articleTypeMaterials,
                              String[] articleTypeSports) {
        for (int i = 0; i < articleTypesNames.length; i++) {
            String name = articleTypesNames[i];
            String description = articleTypeDescriptions[i];
            String materials = articleTypeMaterials[i];
            SportModel sport = SportModel.find(articleTypeSports[i]);

            ArticleType articleType = new ArticleType(name, description, materials, sport);
            articleType.store();
        }
    }

    /**
     * Check if the ArticleType list built using the articleTypes array of names is valid. That is, they are not
     * already stored in the database.
     *
     * @param articleTypes An array of ArticleType names.
     * @return <code>true</code> if all the names are valid, <code>false</code> otherwise.
     */
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

    /**
     * Check if all the sports are stored in the database.
     *
     * @param sportNames Unique names of the sports.
     * @return <code>true</code> if all the sports are found, <code>false</code> otherwise.
     */
    public static boolean areSportsValid(String[] sportNames) {
        int matches = 0;
        List<SportModel> sports = SportModel.findAll();

        for (SportModel sport : sports) {
            for (String sportName : sportNames) {
                if (sport.getName().equals(sportName)) {
                    matches++;
                }
            }
        }

        return matches == sportNames.length;
    }
}
