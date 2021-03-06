package view;

import controller.InsertArticleTypeController;
import controller.OrderController;
import controller.TableController;
import model.StoreModel;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class PlaceOrderView extends javax.swing.JFrame {
    private final ResourceBundle i18n;
    private final StoreModel store;
    private final InsertArticleTypeController insertArticleTypeController;
    private final OrderController orderController;

    /**
     * Creates new form PlaceOrderView
     *
     * @param store A Store.
     */
    public PlaceOrderView(final StoreModel store) {
        i18n = ResourceBundle.getBundle("PlaceOrderView", Locale.getDefault());
        this.store = store;
        insertArticleTypeController = new InsertArticleTypeController();
        orderController = new OrderController();

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel storeLabel = new javax.swing.JLabel();
        javax.swing.JTextField storeTextField = new javax.swing.JTextField();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        articleTypeTable = new javax.swing.JTable();
        javax.swing.JButton orderButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(i18n.getString("title")
        );

        storeLabel.setText(i18n.getString("store")
        );

        storeTextField.setEditable(false);
        storeTextField.setText(store.getName());
        storeTextField.setBorder(null);

        articleTypeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "tipo_articolo", "quantità"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(articleTypeTable);

        orderButton.setText(i18n.getString("confirm"));
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(storeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(orderButton)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeLabel)
                    .addComponent(storeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(orderButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        final int ARTICLE_TYPE_COLUMN = 0;
        final int AMOUNT_COLUMN = 1;

        final String[] articleTypesNames = TableController.fetchRowsFromTable(articleTypeTable, ARTICLE_TYPE_COLUMN);
        final String[] quantities = TableController.fetchRowsFromTable(articleTypeTable, AMOUNT_COLUMN);

        if (articleTypesNames == null || quantities == null) {
            JOptionPane.showMessageDialog(this,
                    i18n.getString("error_empty_table"),
                    i18n.getString("error_title"),
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        final boolean areArticleTypesStored =
                insertArticleTypeController.areArticleTypesAlreadyStored(articleTypesNames);

        if (!areArticleTypesStored) {
            JOptionPane.showMessageDialog(this,
                    i18n.getString("error_no_article_type"),
                    i18n.getString("error_title"),
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        orderController.addOrder(store, articleTypesNames, quantities);
    }//GEN-LAST:event_orderButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlaceOrderView(
                new StoreModel("NEG001",
                        "Decathlon",
                        "Via Monte Cristallo",
                        "San Giovanni Lupatoto")).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable articleTypeTable;
    // End of variables declaration//GEN-END:variables
}
