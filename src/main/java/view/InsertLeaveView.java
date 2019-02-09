package view;

import controller.InsertLeaveController;
import controller.TableController;
import model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class InsertLeaveView extends javax.swing.JFrame {
    private ResourceBundle i18n;
    private List<StoreModel> storesList;
    private List<CourierModel> courierList;
    private DefaultComboBoxModel<String> storesComboBoxModel;
    private DefaultComboBoxModel<String> courierComboBoxModel;

    private enum ColumnPosition {
        ARTICLE_CODE (0);

        private final int columnPosition;
        ColumnPosition(int columnPosition) { this.columnPosition = columnPosition; }
        public int getValue() { return columnPosition; }
    }

    /**
     * Creates new form InsertLeaveView
     */
    public InsertLeaveView() {
        i18n = ResourceBundle.getBundle("InsertLeaveView", Locale.getDefault());
        storesList = StoreModel.findAll();
        courierList = CourierModel.findAll();

        initModels();
        initComponents();
    }

    private void initModels() {
        String[] stores = new String[storesList.size()];

        for (int i = 0; i < storesList.size(); i++) {
            stores[i] = storesList.get(i).getName();
        }

        String[] couriers = new String[courierList.size()];

        for (int i = 0; i < courierList.size(); i++) {
            couriers[i] = courierList.get(i).getName();
        }

        storesComboBoxModel = new DefaultComboBoxModel<>(stores);
        courierComboBoxModel = new DefaultComboBoxModel<>(couriers);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        orderCodeLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        articlesTable = new javax.swing.JTable();
        storeNumberLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        courierLabel = new javax.swing.JLabel();
        storeNameComboBox = new javax.swing.JComboBox<>();
        courierComboBox = new javax.swing.JComboBox<>();
        insertOrderButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        leaveNumberTextField = new javax.swing.JTextField();
        dateTextField = new javax.swing.JTextField();
        orderNumberTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(i18n.getString("title"));

        orderCodeLabel.setText(i18n.getString("label_order_code"));

        articlesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "codice_articolo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(articlesTable);

        storeNumberLabel.setText(i18n.getString("label_store_name"));

        dateLabel.setText(i18n.getString("label_date"));

        courierLabel.setText(i18n.getString("label_courier"));

        storeNameComboBox.setModel(storesComboBoxModel);

        courierComboBox.setModel(courierComboBoxModel);

        insertOrderButton.setText(i18n.getString("button_insert"));
        insertOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertOrderButtonActionPerformed(evt);
            }
        });

        jLabel1.setText(i18n.getString("label_leave_number"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel)
                            .addComponent(courierLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateTextField)
                            .addComponent(courierComboBox, 0, 139, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(orderCodeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(orderNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(leaveNumberTextField)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(insertOrderButton)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(storeNumberLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(storeNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orderCodeLabel)
                    .addComponent(orderNumberTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(leaveNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeNumberLabel)
                    .addComponent(storeNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courierLabel)
                    .addComponent(courierComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(insertOrderButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void insertOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertOrderButtonActionPerformed
        String orderCode = orderNumberTextField.getText();

        OrderModel order = OrderModel.find(orderCode);

        if (order == null) {
            JOptionPane.showMessageDialog(this,
                i18n.getString("error_order_not_found"),
                i18n.getString("error_title"),
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] articleCodes = TableController.fetchRowsFromTable(articlesTable,
            ColumnPosition.ARTICLE_CODE.getValue());

        if (articleCodes == null) {
            JOptionPane.showMessageDialog(this,
                i18n.getString("error_empty_table"),
                i18n.getString("error_title"),
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean allCodesStored = checkIsAlreadyStoredArticleCode(articleCodes);

        if (!allCodesStored) {
            JOptionPane.showMessageDialog(this,
                i18n.getString("error_article_codes_not_found"),
                i18n.getString("error_title"),
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<ArticleModel> articles = new ArrayList<>();

        for (String articleCode : articleCodes) {
            articles.add(ArticleModel.find(articleCode));
        }

        int leaveNumber = Integer.valueOf(leaveNumberTextField.getText());
        String date = dateTextField.getText();
        StoreModel store = StoreModel.findByName(((String)storeNameComboBox.getSelectedItem()));
        CourierModel courier = CourierModel.find(((String)courierComboBox.getSelectedItem()));

        articles.forEach(System.out::println);

        InsertLeaveController.addLeave(leaveNumber, orderCode, articles, date, store, courier);

        JOptionPane.showMessageDialog(this,
            i18n.getString("info_leave_inserted"),
            i18n.getString("info_title"),
            JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_insertOrderButtonActionPerformed

    private boolean checkIsAlreadyStoredArticleCode(String[] tableCodes) {
        String[] storedCodes = ArticleModel.getArticlesCodes();
        int matchCounter = 0;

        for (String tableCode : tableCodes) {
            for (String storedCode : storedCodes) {
                if (tableCode.equals(storedCode)) { // We have found a code already stored in the database
                    matchCounter++;
                }
            }
        }

        return matchCounter == tableCodes.length;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InsertLeaveView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable articlesTable;
    private javax.swing.JComboBox<String> courierComboBox;
    private javax.swing.JLabel courierLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton insertOrderButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField leaveNumberTextField;
    private javax.swing.JLabel orderCodeLabel;
    private javax.swing.JTextField orderNumberTextField;
    private javax.swing.JComboBox<String> storeNameComboBox;
    private javax.swing.JLabel storeNumberLabel;
    // End of variables declaration//GEN-END:variables
}
