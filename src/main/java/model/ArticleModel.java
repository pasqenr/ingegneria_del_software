package model;

import database.DatabaseWrapper;
import factories.FactoryProducer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static factories.FactoryProducer.FactoryType.*;

/**
 * Represent an Article, table <code>articolo</code>.
 */
public class ArticleModel extends Model implements GenericDAO, Comparable {
    private final String code;
    private final ArticleTypeModel articleType;
    private final String price;
    private final String productionDate;
    private final PositionModel position;

    /**
     * Create a new Article.
     *
     * @param code An Article identifier.
     * @param articleType A valid ArticleTypeModel.
     * @param price A valid price.
     * @param productionDate A valid production date.
     * @param position A valid Position.
     */
    public ArticleModel(String code,
                        ArticleTypeModel articleType,
                        String price,
                        String productionDate,
                        PositionModel position) {
        this.code = code;
        this.articleType = articleType;
        this.price = price;
        this.productionDate = productionDate;
        this.position = position;
    }

    /**
     * @return The code.
     */
    public String getCode() {
        return code;
    }
    /**
     * @return The ArticleTypeModel.
     */
    public ArticleTypeModel getArticleType() {
        return articleType;
    }

    /**
     * @return The price as String.
     */
    public String getPrice() {
        return price;
    }

    /**
     * @return The production date.
     */
    public String getProductionDate() {
        return productionDate;
    }

    /**
     * @return The Article Position.
     */
    public PositionModel getPosition() {
        return position;
    }

    @Override
    public ArticleModel find(String code) {
        ArticleModel article = null;
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT a.codice, a.tipo_articolo, a.prezzo, a.data_produzione, a.posizione " +
                "FROM articolo a " +
                "WHERE a.codice LIKE ?";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, code);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                article = buildSingleFromResult(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return article;
    }

    @Override
    public Collection<ArticleModel> findAll() {
        final Collection<ArticleModel> articlesList = new ArrayList<>();
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT a.codice, a.tipo_articolo, a.prezzo, a.data_produzione, a.posizione " +
                "FROM articolo a";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ArticleModel article = buildSingleFromResult(rs);
                articlesList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return articlesList;
    }

    /**
     * Create only one Article using the data found in rs. The cursor is moved forward.
     *
     * @param rs The ResultSet containing the Article or Articles fetched from the database.
     * @return A new Article.
     */
    private ArticleModel buildSingleFromResult(ResultSet rs) {
        String code = null;
        ArticleTypeModel articleType = null;
        String price = null;
        String productionDate = null;
        PositionModel position = null;

        try {
            code = rs.getString("codice");
            String articleName = rs.getString("tipo_articolo");
            price = rs.getString("prezzo");
            productionDate = rs.getString("data_produzione");
            String positionCode = rs.getString("posizione");

            articleType = FactoryProducer.getFactory(ARTICLE_TYPE).getArticleTypeModel().find(articleName);
            position = FactoryProducer.getFactory(POSITION).getPositionModel().find(positionCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArticleModel(code, articleType, price, productionDate, position);
    }

    /**
     * Returns an array of Strings of the codes of all the Articles in the database.
     *
     * @return An array of Article codes.
     */
    public String[] getArticlesCodes() {
        final Collection<ArticleModel> articles = FactoryProducer.getFactory(ARTICLE).getArticleModel().findAll();
        final String[] codes = new String[articles.size()];

        int i = 0;
        for (ArticleModel a : articles) {
            codes[i] = a.getCode();
            i++;
        }

        return codes;
    }

    @Override
    public boolean store() {
        final DatabaseWrapper db = new DatabaseWrapper();
        final String insertQuery = "INSERT INTO articolo " +
                "(codice, tipo_articolo, prezzo, data_produzione, posizione) " +
                "VALUES (?, ?, ?, ?, ?)";
        final PreparedStatement stmt;
        boolean result = false;

        try {
            stmt = db.getCon().prepareStatement(insertQuery);
            stmt.setString(1, code);
            stmt.setString(2, articleType.getName());
            stmt.setString(3, price);
            stmt.setString(4, productionDate);
            stmt.setString(5, position.getCode());
            result = stmt.execute();
            db.getCon().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                db.getCon().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        db.close();

        return result;
    }

    @Override
    public String toString() {
        return "ArticleModel. code: " + code +
                ", articleType: " + articleType.getName() +
                ", price: " + price +
                ", produtionDate: " + productionDate +
                ", position: " + position.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  ArticleModel)) {
            return false;
        }

        final ArticleModel article = (ArticleModel)o;

        return code.equals(article.code) &&
                articleType.equals(article.articleType) &&
                price.equals(article.price) &&
                productionDate.equals(article.productionDate) &&
                position.equals(article.position);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + code.hashCode();
        result = 31 * result + articleType.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + productionDate.hashCode();
        result = 31 * result + position.hashCode();

        return result;
    }

    @Override
    public int compareTo(Object o) {
        final ArticleModel articleModel = (ArticleModel)o;

        return code.compareTo(articleModel.code);
    }
}
