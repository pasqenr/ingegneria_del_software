package model;

import database.DatabaseWrapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntranceModel extends Model implements Comparable {
    private int code;
    private String date;

    public EntranceModel() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime localDateTime = LocalDateTime.now();

        code = 0;
        date = dateTimeFormatter.format(localDateTime);
    }

    public EntranceModel(int code, String date) {
        this.code = code;
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public static int getGreatestCode() {
        int greatestCode = -1;
        DatabaseWrapper db = new DatabaseWrapper();
        String query = "SELECT i.codice FROM ingresso i ORDER BY i.codice DESC LIMIT 1";
        PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            greatestCode = rs.getInt("codice");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return greatestCode;
    }

    @Override
    public boolean store() {
        boolean result = false;
        DatabaseWrapper db = new DatabaseWrapper();
        String queryEntrance = "INSERT INTO ingresso (codice, data) VALUES (?, ?)";
        PreparedStatement insertEntranceStmt;

        try {
            insertEntranceStmt = db.getCon().prepareStatement(queryEntrance);

            if (code <= 0) {
                insertEntranceStmt.setNull(1, code);
            } else {
                insertEntranceStmt.setInt(1, code);
            }

            insertEntranceStmt.setString(2, Date.valueOf(date).toString());
            result = insertEntranceStmt.execute();
            db.getCon().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  EntranceModel)) {
            return false;
        }

        EntranceModel entranceModel = (EntranceModel)o;

        return code == entranceModel.code &&
                date.equals(entranceModel.date);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + code;
        result = 31 * result + date.hashCode();

        return result;
    }

    @Override
    public int compareTo(Object o) {
        EntranceModel entranceModel = (EntranceModel)o;

        return code - entranceModel.code;
    }

    @Override
    public String toString() {
        return "EntranceModel {code : " + code + "}";
    }
}
