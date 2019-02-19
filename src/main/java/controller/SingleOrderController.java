package controller;

import factories.FactoryProducer;
import model.OrderModel;

import javax.swing.table.DefaultTableModel;

import static factories.FactoryProducer.FactoryType.ORDER;

public class SingleOrderController {
    private final DefaultTableModel tableModel;

    public SingleOrderController() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("codice");
        tableModel.addColumn("data");
        tableModel.addColumn("negozio");
    }

    public boolean populateTableModel(final String orderCode) {
        final OrderModel order = FactoryProducer.getFactory(ORDER).getOrderModel().find(orderCode);

        // Order not found
        if (order == null) {
            return false;
        }

        tableModel.addRow(new Object[] {order.getCode(), order.getDate(), order.getStore().getName()});

        return true;
    }

    /**
     * @return The internal TableModel.
     */
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
