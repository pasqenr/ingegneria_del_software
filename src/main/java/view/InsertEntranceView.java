package view;

import controller.ArticleController;
import controller.ArticleTypeController;
import controller.InsertEntranceController;
import controller.PositionController;
import model.ArticleModel;
import model.ArticleType;
import model.PositionModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertEntranceView extends javax.swing.JFrame {
    private static final int ROW_NUM = 20;
    private ArticleTypeController articleTypeController;
    private PositionController positionController;
    private InsertEntranceController insertEntranceController;

    private enum ColumnPosition {
        ARTICLE (0),
        ARTICLE_TYPE (1),
        PRICE (2),
        PRODUCTION_DATE (3),
        POSITION (4);

        private final int columnPosition;
        ColumnPosition(int columnPosition) { this.columnPosition = columnPosition; }
        public int getValue() { return columnPosition; }
    }

    /**
     * Creates new form InsertEntranceView
     */
    public InsertEntranceView() {
        articleTypeController = new ArticleTypeController();
        positionController = new PositionController();
        insertEntranceController = new InsertEntranceController();

        initComponents();

        // Center the frame
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        articlesTable = new javax.swing.JTable();
        articlesLabel = new javax.swing.JLabel();
        createEntranceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrance");

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

        articlesLabel.setText("Articles:");

        createEntranceButton.setText("Create entrance");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(articlesLabel)
                    .addComponent(createEntranceButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(articlesLabel)
                .addGap(18, 18, 18)
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
    }// </editor-fold>//GEN-END:initComponents

    private void createEntranceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createEntranceButtonActionPerformed
        String[] articleCodes = fetchColumnsFromTable(ColumnPosition.ARTICLE);
        String[] articleTypes = fetchColumnsFromTable(ColumnPosition.ARTICLE_TYPE);
        String[] articlePrices = fetchColumnsFromTable(ColumnPosition.PRICE);
        String[] articleProductionDates = fetchColumnsFromTable(ColumnPosition.PRODUCTION_DATE);
        String[] articlePositions = fetchColumnsFromTable(ColumnPosition.POSITION);

        if (articleCodes == null || articleTypes == null || articlePrices == null ||  articleProductionDates == null ||
                articlePositions == null) {
            JOptionPane.showMessageDialog(this,
                    "Empty table",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check that all the columns have the same lengths, that is, have the same amount of rows (elements)
        if (!checkEqualLenghts(new int[] {
                articleCodes.length,
                articleTypes.length,
                articlePrices.length,
                articleProductionDates.length,
                articlePositions.length
        })) {
            JOptionPane.showMessageDialog(this,
                    "Number of elements differ",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        List<ArticleModel> articles = new ArrayList<>();

        for (int i = 0; i < articleCodes.length; i++) {
            ArticleType articleType = articleTypeController.findArticleTypeByName(articleTypes[i]);

            if (articleType == null) {
                JOptionPane.showMessageDialog(this,
                        "Article type not found",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            PositionModel position = positionController.findPositionByCode(articlePositions[i]);

            if (position == null) {
                JOptionPane.showMessageDialog(this,
                        "Position code not found",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            articles.add(new ArticleModel(articleCodes[i],
                    articleType,
                    articlePrices[i],
                    articleProductionDates[i],
                    position));
        }

        boolean articleCodesAreValid = checkIsAlreadyStoredArticleCode(articleCodes);

        if (articleCodesAreValid) {
            insertEntranceController.insertArticlesAsEntrance(articles);

            JOptionPane.showMessageDialog(this,
                    "Articles inserted",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "A code is already in the warehouse",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_createEntranceButtonActionPerformed

    private boolean checkEqualLenghts(int[] lengths) {
        return Arrays.stream(lengths).allMatch(length -> length == lengths[0]);
    }

    private String[] fetchColumnsFromTable(ColumnPosition columnPosition) {
        List<String> columnData = new ArrayList<>();

        for (int row = 0; row < ROW_NUM; row++) {
            Object current = articlesTable.getValueAt(row, columnPosition.getValue());

            if (current != null) {
                columnData.add((String) current);
            }
        }

        final int fetchedRowsNumber = columnData.size();

        /*
          If the list is empty then also the table was empty, let's return that to the caller
         */
        if (fetchedRowsNumber == 0) {
            return null;
        }

        String[] stringColumnData = new String[fetchedRowsNumber];

        for (int i = 0; i < fetchedRowsNumber; i++) {
            stringColumnData[i] = columnData.get(i);
        }

        return  stringColumnData;
    }

    private boolean checkIsAlreadyStoredArticleCode(String[] tableCodes) {
        ArticleController articleController = new ArticleController();
        String[] storedCodes = articleController.getArticlesCodes();

        for (String tableCode : tableCodes) {
            for (String storedCode : storedCodes) {
                if (tableCode.equals(storedCode)) { // We have found a code already stored in the database
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InsertEntranceView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel articlesLabel;
    private javax.swing.JTable articlesTable;
    private javax.swing.JButton createEntranceButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}