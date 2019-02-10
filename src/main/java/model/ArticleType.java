package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent ArticleType, table <code>tipo_articolo</code>.
 */
public class ArticleType extends Model implements GenericDAO {
    private String name;
    private String description;
    private String materials;
    private SportModel sport;

    /**
     * Create a new ArticleType.
     *
     * @param name A valid ArticleType name,.
     * @param description A description.
     * @param materials The materials.
     * @param sport A valid Sport.
     */
    public ArticleType(String name, String description, String materials, SportModel sport) {
        this.name = name;
        this.description = description;
        this.materials = materials;
        this.sport = sport;
    }

    public static ArticleType getInstance() {
        return new ArticleType(null, null, null, null);
    }

    /**
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The materials.
     */
    public String getMaterials() {
        return materials;
    }

    /**
     * @param materials The new materials.
     */
    public void setMaterials(String materials) {
        this.materials = materials;
    }

    /**
     * @return The sport.
     */
    public SportModel getSport() {
        return sport;
    }

    /**
     * @param sport The new sport.
     */
    public void setSport(SportModel sport) {
        this.sport = sport;
    }

    @Override
    public ArticleType find(String name) {
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

            if (rs.next()) {
                String description = rs.getString("descrizione");
                String materials = rs.getString("materiali");
                SportModel sport = new SportModel(rs.getString("sport"));

                articleType = new ArticleType(name, description, materials, sport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return articleType;
    }

    @Override
    public List<ArticleType> findAll() {
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
