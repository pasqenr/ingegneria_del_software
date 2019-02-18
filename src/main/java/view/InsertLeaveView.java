package view;

import controller.InsertLeaveController;
import controller.TableController;
import factories.InstanceFactory;
import model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class InsertLeaveView extends javax.swing.JFrame {
    private final ResourceBundle i18n;
    private final List<StoreModel> storesList;
    private final List<CourierModel> courierList;
    private DefaultComboBoxModel<String> storesComboBoxModel;
    private DefaultComboBoxModel<String> courierComboBoxModel;
    private final InsertLeaveController insertLeaveController;

    /**
     * Creates new form InsertLeaveView
     */
    public InsertLeaveView() {
        i18n = ResourceBundle.getBundle("InsertLeaveView", Locale.getDefault());
        storesList = new ArrayList<>(InstanceFactory.getInstance(StoreModel.class).findAll());
        courierList = new ArrayList<>(InstanceFactory.getInstance(CourierModel.class).findAll());
        insertLeaveController = new InsertLeaveController();

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

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel orderCodeLabel = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        articlesTable = new javax.swing.JTable();
        javax.swing.JLabel storeNumberLabel = new javax.swing.JLabel();
        javax.swing.JLabel dateLabel = new javax.swing.JLabel();
        javax.swing.JLabel courierLabel = new javax.swing.JLabel();
        storeNameComboBox = new javax.swing.JComboBox<>();
        courierComboBox = new javax.swing.JComboBox<>();
        javax.swing.JButton insertOrderButton = new javax.swing.JButton();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
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
        final int COLUMN_ARTICLE_CODE = 0;
        final String orderCode = orderNumberTextField.getText();

        final OrderModel order = InstanceFactory.getInstance(OrderModel.class).find(orderCode);

        if (order == null) {
            JOptionPane.showMessageDialog(this,
                i18n.getString("error_order_not_found"),
                i18n.getString("error_title"),
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        final String[] articleCodes = TableController.fetchRowsFromTable(articlesTable,
                COLUMN_ARTICLE_CODE);

        if (articleCodes == null) {
            JOptionPane.showMessageDialog(this,
                i18n.getString("error_empty_table"),
                i18n.getString("error_title"),
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        final boolean allCodesStored = insertLeaveController.checkIsAlreadyStoredArticleCode(articleCodes);

        if (!allCodesStored) {
            JOptionPane.showMessageDialog(this,
                i18n.getString("error_article_codes_not_found"),
                i18n.getString("error_title"),
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        final List<ArticleModel> articles = new ArrayList<>();

        for (String articleCode : articleCodes) {
            articles.add(InstanceFactory.getInstance(ArticleModel.class).find(articleCode));
        }

        final int leaveNumber = Integer.valueOf(leaveNumberTextField.getText());
        final String date = dateTextField.getText();
        final StoreModel store = StoreModel.findByName(((String)storeNameComboBox.getSelectedItem()));
        final CourierModel courier = InstanceFactory.getInstance(CourierModel.class)
                .find(((String)courierComboBox.getSelectedItem()));

        insertLeaveController.addLeave(leaveNumber, orderCode, articles, date, store, courier);

        JOptionPane.showMessageDialog(this,
            i18n.getString("info_leave_inserted"),
            i18n.getString("info_title"),
            JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_insertOrderButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InsertLeaveView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable articlesTable;
    private javax.swing.JComboBox<String> courierComboBox;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JTextField leaveNumberTextField;
    private javax.swing.JTextField orderNumberTextField;
    private javax.swing.JComboBox<String> storeNameComboBox;
    // End of variables declaration//GEN-END:variables
}
