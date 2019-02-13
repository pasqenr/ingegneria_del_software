package controller;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class TableControllerTest extends GenericControllerTest {
    @Test
    void fetchRowsFromTableTest() {
        final Object[][] rowData = new Object[][] {
                new Object[]{ "1", "2" },
                new Object[]{ "3", "4" }
        };
        final Object[] columnNames = new Object[] { "a", "b" };
        final JTable tableValid = new JTable(rowData, columnNames);

        final String[] tableRows = TableController.fetchRowsFromTable(tableValid, 0);

        assert tableRows != null;
        for (int i = 0; i < tableRows.length; i++) {
            assertEquals(rowData[i][0], tableRows[i]);
        }

        final String[] tableRows2 = TableController.fetchRowsFromTable(tableValid, 1);

        assert tableRows2 != null;
        for (int i = 0; i < tableRows2.length; i++) {
            assertEquals(rowData[i][1], tableRows2[i]);
        }
    }
}
