package model;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersModel {
    private String[] columnNames;
    private Object[][] data;
    private boolean isCodeInt;

    public OrdersModel(String[] columnNames, ResultSet rs, boolean isCodeInt) {
        this.columnNames = columnNames;
        this.isCodeInt = isCodeInt;

        try {
            populateDataTable(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OrdersModel(String[] columnNames, ResultSet rs) {
        this(columnNames, rs, true);
    }

    public Object[][] getRawTable() {
        return data;
    }

    public TableModel getTableModel() {
        return new DefaultTableModel(data, columnNames);
    }

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
