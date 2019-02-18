package model;

import database.DatabaseWrapper;
import factories.InstanceFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * Represent an Entrance, table <code>ingresso</code>.
 */
public class EntranceModel extends Model implements GenericDAO, Comparable {
    private final int code;
    private final String date;

    /**
     * Create a new Entrance. Should not be used because the code is <code>AUTOINCREMENT</code>.
     *
     * @param code The code.
     * @param date The date.
     */
    public EntranceModel(int code, String date) {
        this.code = code;
        this.date = date;
    }

    /**
     * Create a new Entrance. The table has a code identifier that is set as <code>AUTOINCREMENT</code> so
     * an Entrance can be created without arguments.
     */
    public EntranceModel() {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDateTime localDateTime = LocalDateTime.now();

        code = 0;
        date = dateTimeFormatter.format(localDateTime);
    }

    /**
     * @return The code.
     */
    public int getCode() {
        return code;
    }

    @Override
    public EntranceModel find(String code) {
        return InstanceFactory.getInstance(EntranceModel.class).find(Integer.valueOf(code));
    }

    public EntranceModel find(int code) {
        EntranceModel entrance = null;
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT i.codice, i.data FROM ingresso i WHERE i.codice = ?";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            stmt.setInt(1, code);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int foundCode = rs.getInt("codice");
                String foundDate = rs.getString("data");
                entrance = new EntranceModel(foundCode, foundDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return entrance;
    }

    /**
     * Returns the greatest code, that is, the code of the last inserted Entrance.
     *
     * @return The code of the newest Entrance.
     */
    public int getGreatestCode() {
        int greatestCode = -1;
        final DatabaseWrapper db = new DatabaseWrapper();
        final String query = "SELECT i.codice FROM ingresso i ORDER BY i.codice DESC LIMIT 1";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            final ResultSet rs = stmt.executeQuery();

            rs.next();
            greatestCode = rs.getInt("codice");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        return greatestCode;
    }

    @Override
    public Collection<EntranceModel> findAll() {
        return null;
    }

    @Override
    public boolean store() {
        boolean result = false;
        final DatabaseWrapper db = new DatabaseWrapper();
        final String queryEntrance = "INSERT INTO ingresso (codice, data) VALUES (?, ?)";
        final PreparedStatement insertEntranceStmt;

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

        final EntranceModel entranceModel = (EntranceModel)o;

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
        final EntranceModel entranceModel = (EntranceModel)o;

        return code - entranceModel.code;
    }

    @Override
    public String toString() {
        return "EntranceModel {code : " + code + "}";
    }
}
