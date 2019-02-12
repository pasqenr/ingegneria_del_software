package database;

import java.sql.*;

/**
 * A Database wrapper for the underling database jdbc connection.
 */
public class DatabaseWrapper implements AutoCloseable {
    private static String forcedConnectionUrl;
    private static boolean isForced = false;
    private Connection conn = null;

    /**
     * Create a new connection to the url.
     *
     * @param url The url of the database.
     * @param autoCommit <code>true</code> to enable the autoCommit, <code>false</code> otherwise.
     */
    public DatabaseWrapper(String url, boolean autoCommit) {
        try {
            connect(url);
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new connection to the standard database.
     */
    public DatabaseWrapper() {
        this("jdbc:sqlite:" + System.getProperty("user.dir") + "/magazzino.sqlite", false);
    }

    /**
     * @return Return the underling Connection.
     */
    public Connection getCon() {
        return conn;
    }

    /**
     * Force all the instances of the class to use this url instead the default one.
     *
     * @param url The URL to force.
     */
    public static void forceConnectionUrl(String url) {
        forcedConnectionUrl = url;
        isForced = true;
    }

    /**
     * @return The default test database connection String.
     */
    public static String getDefaultTestConnection() {
        return "jdbc:sqlite:" + System.getProperty("user.dir") + "/magazzino_test.sqlite";
    }

    /**
     * Connect to the url provided.
     *
     * @param url The url of the database.
     */
    private void connect(String url) throws SQLException {
        if (isForced) {
            conn = DriverManager.getConnection(forcedConnectionUrl);
        } else {
            conn = DriverManager.getConnection(url);
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
