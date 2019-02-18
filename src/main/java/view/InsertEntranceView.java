package view;

import controller.*;
import model.ArticleModel;
import model.ArticleTypeModel;
import factories.InstanceFactory;
import model.PositionModel;

import javax.swing.*;
import java.util.*;

public class InsertEntranceView extends javax.swing.JFrame {
    private final ResourceBundle i18n;
    private final PositionController positionController;
    private final InsertEntranceController insertEntranceController;

    /**
     * Creates new form InsertEntranceView
     */
    public InsertEntranceView() {
        i18n = ResourceBundle.getBundle("InsertEntranceView", Locale.getDefault());
        positionController = new PositionController();
        insertEntranceController = new InsertEntranceController();

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

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        articlesTable = new javax.swing.JTable();
        javax.swing.JButton createEntranceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(i18n.getString("title"));

        articlesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "codice_articolo", "tipo_articolo", "prezzo", "data_produzione", "posizione"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(articlesTable);

        createEntranceButton.setText(i18n.getString("insert"));
        createEntranceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createEntranceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(createEntranceButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createEntranceButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void createEntranceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createEntranceButtonActionPerformed
        final int ARTICLE_COLUMN = 0;
        final int ARTICLE_TYPE_COLUMN = 1;
        final int PRICE_COLUMN = 2;
        final int PRODUCTION_DATE_COLUMN = 3;
        final int POSITION_COLUMN = 4;

        String[] articleCodes = TableController.fetchRowsFromTable(articlesTable, ARTICLE_COLUMN);
        String[] articleTypes = TableController.fetchRowsFromTable(articlesTable, ARTICLE_TYPE_COLUMN);
        String[] articlePrices = TableController.fetchRowsFromTable(articlesTable, PRICE_COLUMN);
        String[] articleProductionDates = TableController.fetchRowsFromTable(articlesTable, PRODUCTION_DATE_COLUMN);
        String[] articlePositions = TableController.fetchRowsFromTable(articlesTable, POSITION_COLUMN);

        if (articleCodes == null || articleTypes == null || articlePrices == null ||  articleProductionDates == null ||
                articlePositions == null) {
            JOptionPane.showMessageDialog(this,
                    i18n.getString("error_empty_fields"),
                    i18n.getString("error_title"),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check that all the columns have the same lengths, that is, have the same amount of rows (elements)
        if (!insertEntranceController.checkEqualLengths(new int[] {
                articleCodes.length,
                articleTypes.length,
                articlePrices.length,
                articleProductionDates.length,
                articlePositions.length
        })) {
            JOptionPane.showMessageDialog(this,
                    i18n.getString("error_elements_length_differ"),
                    i18n.getString("error_title"),
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        List<ArticleModel> articles = new ArrayList<>();

        for (int i = 0; i < articleCodes.length; i++) {
            ArticleTypeModel articleType = InstanceFactory.getInstance(ArticleTypeModel.class).find(articleTypes[i]);

            if (articleType == null) {
                JOptionPane.showMessageDialog(this,
                        i18n.getString("error_article_type_not_found"),
                        i18n.getString("error_title"),
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            PositionModel position = positionController.findFreePositionByCode(articlePositions[i]);

            if (position == null) {
                JOptionPane.showMessageDialog(this,
                        i18n.getString("error_position_code_not_found"),
                        i18n.getString("error_title"),
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            articles.add(new ArticleModel(articleCodes[i],
                    articleType,
                    articlePrices[i],
                    articleProductionDates[i],
                    position));
        }

        boolean articleCodesAreValid = insertEntranceController.checkIsAlreadyStoredArticleCodes(articleCodes);

        if (!articleCodesAreValid) {
            JOptionPane.showMessageDialog(this,
                    i18n.getString("error_article_code_already_stored"),
                    i18n.getString("error_title"),
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        insertEntranceController.insertArticlesAsEntrance(articles);

        JOptionPane.showMessageDialog(this,
                i18n.getString("info_articles_inserted"),
                i18n.getString("info_title"),
                JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_createEntranceButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InsertEntranceView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable articlesTable;
    // End of variables declaration//GEN-END:variables
}
