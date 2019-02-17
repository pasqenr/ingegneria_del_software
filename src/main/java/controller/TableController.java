package controller;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Helps the management of a JTable.
 */
public class TableController {
    /**
     * Fetch the rows in the table under the column at index columnPosition.
     *
     * @param table A JTable.
     * @param columnPosition A valid table index, starting from 0.
     * @return The array of rows of the table at the columnPosition.
     */
    public static String[] fetchRowsFromTable(final JTable table, final int columnPosition) {
        final List<String> rowsList = new ArrayList<>();

        for (int row = 0; row < table.getRowCount(); row++) {
            final Object current = table.getValueAt(row, columnPosition);

            if (current != null) {
                rowsList.add((String) current);
            }
        }

        final int fetchedRowsNumber = rowsList.size();

        // If the list is empty then also the table was empty, let's return that to the caller
        if (fetchedRowsNumber == 0) {
            return null;
        }

        final String[] rowsArray = new String[fetchedRowsNumber];

        for (int i = 0; i < fetchedRowsNumber; i++) {
            rowsArray[i] = rowsList.get(i);
        }

        return  rowsArray;
    }
}
