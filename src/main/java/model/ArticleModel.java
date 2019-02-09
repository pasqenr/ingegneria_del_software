package model;

import controller.PositionController;
import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent an Article, table <code>articolo</code>.
 */
public class ArticleModel extends Model implements Comparable {
    private String code;
    private ArticleType articleType;
    private String price;
    private String productionDate;
    private PositionModel position;

    /**
     * Create a new Article.
     *
     * @param code An Article identifier.
     * @param articleType A valid ArticleType.
     * @param price A valid price.
     * @param productionDate A valid production date.
     * @param position A valid Position.
     */
    public ArticleModel(String code, ArticleType articleType, String price, String productionDate, PositionModel position) {
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
     * @param code The new code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The ArticleType.
     */
    public ArticleType getArticleType() {
        return articleType;
    }

    /**
     * @param articleType The new ArticleType.
     */
    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    /**
     * @return The price as String.
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price The new price as String.
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return The production date.
     */
    public String getProductionDate() {
        return productionDate;
    }

    /**
     * @param productionDate The new production date.
     */
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    /**
     * @return The Article Position.
     */
    public PositionModel getPosition() {
        return position;
    }

    /**
     * @param position The new Article Position.
     */
    public void setPosition(PositionModel position) {
        this.position = position;
    }

    /**
     * Returns the Article identified by code.
     *
     * @param code A valid article code.
     * @return The Article identified by the code.
     */
    public static ArticleModel find(String code) {
        ArticleModel article = null;
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT a.codice, a.tipo_articolo, a.prezzo, a.data_produzione, a.posizione " +
                "FROM articolo a " +
                "WHERE a.codice LIKE ?";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                article = buildSingleFromResult(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return article;
    }

    /**
     * Returns all the Articles.
     *
     * @return A list of all the Articles in the database.
     */
    public static List<ArticleModel> findAll() {
        List<ArticleModel> articlesList = new ArrayList<>();
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT a.codice, a.tipo_articolo, a.prezzo, a.data_produzione, a.posizione FROM articolo a";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

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
    private static ArticleModel buildSingleFromResult(ResultSet rs) {
        String code = null;
        ArticleType articleType = null;
        String price = null;
        String productionDate = null;
        PositionModel position = null;

        try {
            code = rs.getString("codice");
            String articleName = rs.getString("tipo_articolo");
            price = rs.getString("prezzo");
            productionDate = rs.getString("data_produzione");
            String positionCode = rs.getString("posizione");

            articleType = ArticleType.find(articleName);
            position = new PositionController().findPositionByCode(positionCode);
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
    public static String[] getArticlesCodes() {
        List<ArticleModel> articles = ArticleModel.findAll();
        String[] codes = new String[articles.size()];

        int i = 0;
        for (ArticleModel a : articles) {
            codes[i] = a.getCode();
            i++;
        }

        return codes;
    }

    @Override
    public boolean store() {
        DatabaseWrapper db = new DatabaseWrapper();
        String insertQuery = "INSERT INTO articolo (codice, tipo_articolo, prezzo, data_produzione, posizione) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt;
        boolean result = false;

        try {
            stmt = db.getCon().prepareStatement(insertQuery);
            stmt.setString(1, code);
            stmt.setString(2, articleType.getName());
            stmt.setString(3, price);
            stmt.setString(4, productionDate);
            stmt.setString(5, position.getRawPosition());
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
                ", position: " + position.getRawPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  ArticleModel)) {
            return false;
        }

        ArticleModel article = (ArticleModel)o;

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
        ArticleModel articleModel = (ArticleModel)o;

        return code.compareTo(articleModel.code);
    }
}
