package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleType extends Model {
    private String name;
    private String description;
    private String materials;
    private SportModel sport;

    public ArticleType(String name, String description, String materials, SportModel sport) {
        this.name = name;
        this.description = description;
        this.materials = materials;
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public SportModel getSport() {
        return sport;
    }

    public void setSport(SportModel sport) {
        this.sport = sport;
    }

    public static ArticleType find(String name) {
        ArticleType articleType = null;
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT ta.nome, ta.descrizione, ta.materiali, ta.sport " +
                "FROM tipo_articolo ta " +
                "WHERE ta.nome LIKE ?";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            String description = rs.getString("descrizione");
            String materials = rs.getString("materiali");
            SportModel sport = new SportModel(rs.getString("sport"));

            articleType = new ArticleType(name, description, materials, sport);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return articleType;
    }

    public static List<ArticleType> findAll() {
        List<ArticleType> articleTypes = new ArrayList<>();
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT ta.nome, ta.descrizione, ta.materiali, ta.sport FROM tipo_articolo ta";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("nome");
                String description = rs.getString("descrizione");
                String materials = rs.getString("materiali");
                SportModel sport = new SportModel(rs.getString("sport"));

                articleTypes.add(new ArticleType(name, description, materials, sport));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return articleTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  ArticleType)) {
            return false;
        }

        ArticleType articleType = (ArticleType)o;

        return name.equals(articleType.name) &&
                description.equals(articleType.description) &&
                materials.equals(articleType.materials) &&
                sport.equals(articleType.sport);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + name.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + materials.hashCode();
        result = 31 * result + sport.hashCode();

        return result;
    }

    @Override
    public boolean store() {
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "INSERT INTO tipo_articolo (nome, descrizione, materiali, sport) VALUES " +
                "(?, ?, ?, ?)";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, materials);
            stmt.setString(4, sport.getName());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            db.getCon().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return true;
    }
}
