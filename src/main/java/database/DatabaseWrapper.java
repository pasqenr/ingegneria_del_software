package database;

import java.sql.*;

public class DatabaseWrapper {
    private Connection conn = null;

    public DatabaseWrapper() {
        connect();

        try {
            this.conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return this.conn;
    }

    public void connect() {
        try {
            String url = "jdbc:sqlite:/home/pasqenr/gitlab/ingegneria_del_software/src/main/resources/magazzino.sqlite";

            this.conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet rawQuery(String query) {
        Statement stmt;
        ResultSet rs = null;

        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public void close() {
        try {
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.conn = null;
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
