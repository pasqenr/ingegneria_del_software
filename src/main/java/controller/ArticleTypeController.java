package controller;

import database.DatabaseWrapper;
import model.ArticleType;
import model.SportModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleTypeController extends Controller {
    private List<ArticleType> articleTypeList;

    public ArticleTypeController() {
        articleTypeList = fetchArticleTypes();
    }

    private List<ArticleType> fetchArticleTypes() {
        DatabaseWrapper db = new DatabaseWrapper();
        Connection con = db.getCon();
        String query = "SELECT ta.nome, ta.descrizione, ta.materiali, ta.sport FROM tipo_articolo ta";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ArticleType> articleTypes = new ArrayList<>();

        try {
            stmt = con.prepareStatement(query);
            stmt.execute();
            rs = stmt.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assert rs != null;
            while (rs.next()) {
                String name = rs.getString("nome");
                String description = rs.getString("descrizione");
                String materials = rs.getString("materiali");
                String sport = rs.getString("sport");

                articleTypes.add(new ArticleType(name, description, materials, new SportModel(sport)));
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            db.close();
        }

        db.close();

        return articleTypes;
    }

    public ArticleType findArticleTypeByName(String articleName) {
        return articleTypeList.stream().filter(articleType -> articleType.getName().equals(articleName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update() {
        articleTypeList = fetchArticleTypes();
    }
}
