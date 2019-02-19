package controller;

import org.junit.jupiter.api.Test;

import javax.swing.table.TableModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderFulfillmentControllerTest extends GenericControllerTest {
    @Test
    void getTableModelTest() {
        final OrderFulfillmentController orderFulfillmentController = new OrderFulfillmentController();
        final TableModel tableModel = orderFulfillmentController.getTableModel();
        final String[] columnNamesValid = new String[] {
                "codice_ordine", "numbero_bolla", "data", "negozio", "spedizioniere"
        };
        final String[] rowsValid = new String[] {
                "ORD001", "1", "2018-06-15", "Decathlon", "BRT"
        };

        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            assertEquals(columnNamesValid[i], tableModel.getColumnName(i));

            for (int j = 0; j < tableModel.getRowCount(); j++) {
                assertEquals(rowsValid[i], tableModel.getValueAt(j, i));
            }
        }
    }
}
