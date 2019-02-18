package model;

import database.DatabaseWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represent ArticleTypeModel, table <code>tipo_articolo</code>.
 */
public class ArticleTypeModel extends Model implements GenericDAO {
    private final String name;
    private final String description;
    private final String materials;
    private final SportModel sport;

    /**
     * Create a new ArticleTypeModel.
     *
     * @param name A valid ArticleTypeModel name,.
     * @param description A description.
     * @param materials The materials.
     * @param sport A valid Sport.
     */
    public ArticleTypeModel(String name, String description, String materials, SportModel sport) {
        this.name = name;
        this.description = description;
        this.materials = materials;
        this.sport = sport;
    }

    /**
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The materials.
     */
    public String getMaterials() {
        return materials;
    }

    /**
     * @return The sport.
     */
    public SportModel getSport() {
        return sport;
    }

    @Override
    public ArticleTypeModel find(String name) {
        ArticleTypeModel articleType = null;
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT ta.nome, ta.descrizione, ta.materiali, ta.sport " +
                "FROM tipo_articolo ta " +
                "WHERE ta.nome LIKE ?";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setString(1, name);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String description = rs.getString("descrizione");
                String materials = rs.getString("materiali");
                SportModel sport = new SportModel(rs.getString("sport"));

                articleType = new ArticleTypeModel(name, description, materials, sport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return articleType;
    }

    @Override
    public Collection<ArticleTypeModel> findAll() {
        final Collection<ArticleTypeModel> articleTypes = new ArrayList<>();
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT ta.nome, ta.descrizione, ta.materiali, ta.sport FROM tipo_articolo ta";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            final ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("nome");
                String description = rs.getString("descrizione");
                String materials = rs.getString("materiali");
                SportModel sport = new SportModel(rs.getString("sport"));

                articleTypes.add(new ArticleTypeModel(name, description, materials, sport));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return articleTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof ArticleTypeModel)) {
            return false;
        }

        final ArticleTypeModel articleType = (ArticleTypeModel)o;

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
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "INSERT INTO tipo_articolo (nome, descrizione, materiali, sport) VALUES " +
                "(?, ?, ?, ?)";
        final PreparedStatement stmt;

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
