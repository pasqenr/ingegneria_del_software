package database;

import java.sql.*;

/**
 * A Database wrapper for the underling database jdbc connection.
 */
public class DatabaseWrapper implements AutoCloseable {
    private Connection conn = null;

    /**
     * Create a new connection to the url.
     *
     * @param url The url of the database.
     * @param autoCommit <code>true</code> to enable the autoCommit, <code>false</code> otherwise.
     */
    public DatabaseWrapper(String url, boolean autoCommit) {
        connect(url);

        try {
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new connection to the standard database.
     */
    public DatabaseWrapper() {
        this("jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/magazzino.sqlite",
                false);
    }

    /**
     * @return Return the underling Connection.
     */
    public Connection getCon() {
        return conn;
    }

    /**
     * Connect to the url provided.
     *
     * @param url The url of the database.
     */
    private void connect(String url) {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send the query to the database.
     *
     * @param query The query to send.
     * @return The ResultSet.
     */
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

    /**
     * Close the connection and free resources.
     */
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

    /**
     * Commit the current transaction.
     */
    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
