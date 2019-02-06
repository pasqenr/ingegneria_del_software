package view;

import controller.InsertArticleTypeController;
import controller.TableController;

import javax.swing.*;

public class InsertArticleTypeView extends javax.swing.JFrame {

    /**
     * Creates new form InsertArticleTypeView
     */
    public InsertArticleTypeView() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        articleTypeTable = new javax.swing.JTable();
        insertButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Insert article type");

        articleTypeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "name", "description", "materials", "sport"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(articleTypeTable);

        insertButton.setText("Insert");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(insertButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(insertButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        final int ARTICLE_NAME_COLUMN = 0;
        final int ARTICLE_DESCRIPTION_COLUMN = 1;
        final int ARTICLE_MATERIALS_COLUMN = 2;
        final int ARTICLE_SPORT_COLUMN = 3;

        String[] articleTypesNames = TableController.fetchColumnsFromTable(articleTypeTable, ARTICLE_NAME_COLUMN);
        String[] articleTypeDescriptions = TableController.fetchColumnsFromTable(articleTypeTable, ARTICLE_DESCRIPTION_COLUMN);
        String[] articleTypeMaterials = TableController.fetchColumnsFromTable(articleTypeTable, ARTICLE_MATERIALS_COLUMN);
        String[] articleTypeSports = TableController.fetchColumnsFromTable(articleTypeTable, ARTICLE_SPORT_COLUMN);

        if (articleTypesNames == null || articleTypeDescriptions == null || articleTypeMaterials == null ||
                articleTypeSports == null) {
            return;
        }

        boolean articleTypesAlreadyStored = InsertArticleTypeController.areArticleTypesAlreadyStored(articleTypesNames);

        if (articleTypesAlreadyStored) {
            JOptionPane.showMessageDialog(this,
                    "Some article types are already in the database",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        InsertArticleTypeController.addAll(articleTypesNames,
                articleTypeDescriptions,
                articleTypeMaterials,
                articleTypeSports);

        JOptionPane.showMessageDialog(this,
                "Article types inserted",
                "Inserted",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_insertButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InsertArticleTypeView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable articleTypeTable;
    private javax.swing.JButton insertButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
