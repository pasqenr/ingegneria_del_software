package model;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a matrix of data as a table, each table with its column name.
 */
public class OrdersModel {
    private String[] columnNames;
    private Object[][] data;
    private boolean isCodeInt;

    /**
     * Create a new OrdersModel. The table is populated with the data fetched using rs using columnNames as
     * parameters. Also, columnNames is also used to name the columns in the table.
     *
     * @param columnNames Names of the columns.
     * @param rs The data used to populate the table.
     * @param isCodeInt <code>true</code> if the first field is an Integer, <code>false</code> if it's a String.
     */
    public OrdersModel(String[] columnNames, ResultSet rs, boolean isCodeInt) {
        this.columnNames = columnNames;
        this.isCodeInt = isCodeInt;

        try {
            populateDataTable(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new OrdersModel. The table is populated with the data fetched using rs using columnNames as
     * parameters. Also, columnNames is also used to name the columns in the table.
     *
     * @param columnNames Names of the columns.
     * @param rs The data used to populate the table.
     */
    public OrdersModel(String[] columnNames, ResultSet rs) {
        this(columnNames, rs, true);
    }

    /**
     * @return The internal matrix of the data.
     */
    public Object[][] getRawData() {
        return data;
    }

    /**
     * @return The TableModel of the data.
     */
    public TableModel getTableModel() {
        return new DefaultTableModel(data, columnNames);
    }

    /**
     * Populate the table.
     *
     * @param rs The data used to populate the table.
     * @throws SQLException If there are errors using rs.
     */
    private void populateDataTable(ResultSet rs) throws SQLException {
        int totalRows = 0;
        List<List<Object>> results = new ArrayList<>();

        while (rs.next()) {
            List<Object> columns = new ArrayList<>();

            for (int c = 0; c < columnNames.length; c++) {
                if (c == 0 && isCodeInt) {
                    columns.add(rs.getInt(columnNames[c]));
                } else {
                    columns.add(rs.getString(columnNames[c]));
                }
            }

            results.add(columns);
            totalRows++;
        }

        data = new Object[totalRows][columnNames.length];
        for (int r = 0; r < totalRows; r++) {
            for (int c = 0; c < columnNames.length; c++) {
                data[r][c] = results.get(r).get(c);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < data.length; r++) {
            sb.append("[");

            for (int c = 0; c < columnNames.length; c++) {
                sb.append(data[r][c]);

                if (c + 1 < data[r].length) {
                    sb.append(", ");
                }
            }

            sb.append("]\n");
        }

        return sb.toString();
    }
}
