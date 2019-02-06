package controller;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TableController {
    public static String[] fetchColumnsFromTable(JTable table, int columnPosition) {
        List<String> columnData = new ArrayList<>();

        for (int row = 0; row < table.getRowCount(); row++) {
            Object current = table.getValueAt(row, columnPosition);

            if (current != null) {
                columnData.add((String) current);
            }
        }

        final int fetchedRowsNumber = columnData.size();

        // If the list is empty then also the table was empty, let's return that to the caller
        if (fetchedRowsNumber == 0) {
            return null;
        }

        String[] stringColumnData = new String[fetchedRowsNumber];

        for (int i = 0; i < fetchedRowsNumber; i++) {
            stringColumnData[i] = columnData.get(i);
        }

        return  stringColumnData;
    }
}
