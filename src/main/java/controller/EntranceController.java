package controller;

import database.DatabaseWrapper;
import model.EntranceModel;
import model.EntranceOrdersModel;
import model.OrdersModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EntranceController {
    private EntranceOrdersModel ordersModel;

    public EntranceController() {
        DatabaseWrapper db = new DatabaseWrapper();

        ResultSet rs = fetchEntranceOrders(db);
        ordersModel = new EntranceOrdersModel(rs);

        db.close();
    }

    public static EntranceModel getEntranceByCode(int code) {
        EntranceModel entrance = null;
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT i.codice, i.data FROM ingresso i WHERE i.codice = ?";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setInt(1, code);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            int foundCode = rs.getInt("codice");
            String foundDate = rs.getString("data");
            entrance = new EntranceModel(foundCode, foundDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        db.close();

        return entrance;
    }

    static public List<EntranceModel> getEntrances() {
        DatabaseWrapper db = new DatabaseWrapper();
        List<EntranceModel> entrances = null;
        PreparedStatement stmt;
        String query = "SELECT i.codice, i.data FROM ingresso";

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int foundCode = rs.getInt("codice");
                String foundDate = rs.getString("data");

                entrances.add(new EntranceModel(foundCode, foundDate));
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        db.close();

        return entrances;
    }

    static public ResultSet fetchEntranceOrders(DatabaseWrapper db) {
        ResultSet rs;

        String query =
                "SELECT i.codice AS codice_ingresso, " +
                "i.data AS data_ingresso, " +
                "a.codice AS codice_articolo, " +
                "ta.nome, a.prezzo, a.posizione " +
                "FROM tipo_articolo AS ta " +
                "JOIN articolo a ON ta.nome = a.tipo_articolo " +
                "JOIN ingresso_articolo ia on a.codice = ia.codice_articolo " +
                "JOIN ingresso i on ia.codice_ingresso = i.codice " +
                "ORDER BY i.codice";

        rs = db.rawQuery(query);

        return rs;
    }

    public OrdersModel getOrdersModel() {
        return ordersModel;
    }
}
