package controller;

import model.ArticleTypeModel;
import factories.InstanceFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertArticleTypeModelControllerTest extends GenericControllerTest {
    @Test
    void addAllTest() {
        final String[] articleTypeNames = new String[] {
                "Guanti palmati aquagym",
                "Materasso acquafitness"
        };
        final String[] articleTypeDescriptions = new String[] {
                "Guanti in neoprene che potenziano la superficie d'appoggio delle mani consentendoti di far lavorare efficacemente la parte alta del corpo.",
                "Questa tavola gonfiabile diventa una piattaforma per eseguire esercizi di rafforzamento muscolare sfruttando l’instabilità dell’acqua."
        };
        final String[] articleTypeMaterials = new String[] {
                "85.00% Poliammide (PA), 15.00% Elastan",
                "30.00% Poliestere (PES), 70.00% Cloruro di polivinile (PVC) - senza ftalati"
        };
        final String[] articleTypeSports = new String[] {
                "Acquagym",
                "Acquagym"
        };

        final InsertArticleTypeController insertArticleTypeController = new InsertArticleTypeController();

        insertArticleTypeController.addAll(articleTypeNames,
                articleTypeDescriptions,
                articleTypeMaterials,
                articleTypeSports);

        assertNotNull(InstanceFactory.getInstance(ArticleTypeModel.class).find("Guanti palmati aquagym"));
        assertNotNull(InstanceFactory.getInstance(ArticleTypeModel.class).find("Materasso acquafitness"));
    }

    @Test
    void areArticleTypesAlreadyStoredTest() {
        final String[] articleTypeNamesValid = new String[] {
                "Costume coprente acquafitness",
                "Top anny nero-azzurro"
        };
        final String[] articleTypeNamesInvalid = new String[] {
                "Costumi Acquagym",
                "Scarpette e calze Acquagym"
        };

        final InsertArticleTypeController insertArticleTypeController = new InsertArticleTypeController();

        assertFalse(insertArticleTypeController.areArticleTypesAlreadyStored(articleTypeNamesValid));
        assertTrue(insertArticleTypeController.areArticleTypesAlreadyStored(articleTypeNamesInvalid));
    }

    @Test
    void areSportsValidTest() {
        final String[] sportsValid = new String[] {
                "Acquagym",
                "Alpinismo"
        };
        final String[] sportsInvalid = new String[] {
                "mygauqcA",
                "omsiniplA"
        };

        final InsertArticleTypeController insertArticleTypeController = new InsertArticleTypeController();

        assertTrue(insertArticleTypeController.areSportsValid(sportsValid));
        assertFalse(insertArticleTypeController.areSportsValid(sportsInvalid));
    }
}
