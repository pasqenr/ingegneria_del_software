package model;

import javax.swing.table.TableModel;

public interface OrdersTable {
    Object[][] getRawData();

    TableModel getTableModel();
}
