package controller;

import factories.FactoryProducer;
import model.ArticleTypeModel;
import model.SportModel;

import java.util.Collection;

import static factories.FactoryProducer.FactoryType.ARTICLE_TYPE;
import static factories.FactoryProducer.FactoryType.SPORT;

/**
 * Manage the insertion of a list of ArticleTypeModel
 */
public class InsertArticleTypeController {

    /**
     * Add all the ArticleTypeModel that can build using the parameters information.
     *
     * @param articleTypesNames An array of valid ArticleTypeModel names.
     * @param articleTypeDescriptions An array of ArticleTypeModel description.
     * @param articleTypeMaterials An array of ArticleTypeModel materials.
     * @param articleTypeSports An array of valid Sport names.
     */
    public void addAll(final String[] articleTypesNames,
                       final String[] articleTypeDescriptions,
                       final String[] articleTypeMaterials,
                       final String[] articleTypeSports) {
        for (int i = 0; i < articleTypesNames.length; i++) {
            final String name = articleTypesNames[i];
            final String description = articleTypeDescriptions[i];
            final String materials = articleTypeMaterials[i];
            final SportModel sport = FactoryProducer.getFactory(SPORT).getSportModel().find(articleTypeSports[i]);

            final ArticleTypeModel articleType = new ArticleTypeModel(name, description, materials, sport);
            articleType.store();
        }
    }

    /**
     * Check if the ArticleTypeModel list built using the articleTypes array of names is valid. That is, they are not
     * already stored in the database.
     *
     * @param articleTypes An array of ArticleTypeModel names.
     * @return <code>true</code> if all the names are valid, <code>false</code> otherwise.
     */
    public boolean areArticleTypesAlreadyStored(final String[] articleTypes) {
        int matches = 0;
        final Collection<ArticleTypeModel> articleTypeList = FactoryProducer.getFactory(ARTICLE_TYPE)
                .getArticleTypeModel().findAll();

        for (final ArticleTypeModel articleType : articleTypeList) {
            for (final String articleCode : articleTypes) {
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
    public boolean areSportsValid(final String[] sportNames) {
        int matches = 0;
        final Collection<SportModel> sports = FactoryProducer.getFactory(SPORT).getSportModel().findAll();

        for (final SportModel sport : sports) {
            for (final String sportName : sportNames) {
                if (sport.getName().equals(sportName)) {
                    matches++;
                }
            }
        }

        return matches == sportNames.length;
    }
}
