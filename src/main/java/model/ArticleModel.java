package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticleModel extends Model implements Comparable {
    private String code;
    private ArticleType articleType;
    private String price;
    private String productionDate;
    private PositionModel position;

    public ArticleModel(String code, ArticleType articleType, String price, String productionDate, PositionModel position) {
        this.code = code;
        this.articleType = articleType;
        this.price = price;
        this.productionDate = productionDate;
        this.position = position;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
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
