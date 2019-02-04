package database;

import java.sql.*;

public class DatabaseWrapper implements AutoCloseable {
    private static Connection conn = null;

    public DatabaseWrapper(String url) {
        if (conn != null) {
            return;
        }

        connect(url);

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DatabaseWrapper() {
        this("jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/magazzino.sqlite");
    }

    public Connection getCon() {
        return conn;
    }

    // TODO: Should support the dependency injection of the database path
    // TODO: Should implement Singleton pattern and return the old connection if it isn't null
    private void connect(String url) {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet rawQuery(String query) {
        Statement stmt;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conn = null;
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
