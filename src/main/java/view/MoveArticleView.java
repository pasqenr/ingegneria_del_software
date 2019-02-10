package view;

import controller.MoveArticleController;
import model.ArticleModel;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class MoveArticleView extends javax.swing.JFrame {
    private ResourceBundle i18n;
    private MoveArticleController moveArticleController;
    private ArticleModel article;

    /**
     * Creates new form MoveArticleView
     */
    public MoveArticleView() {
        i18n = ResourceBundle.getBundle("MoveArticleView", Locale.getDefault());
        moveArticleController = new MoveArticleController();

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

        jPanel1 = new javax.swing.JPanel();
        articleCodeLabel = new javax.swing.JLabel();
        articleCodeTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        articleOldPositionLabel = new javax.swing.JLabel();
        articleOldPositionTextField = new javax.swing.JTextField();
        avaiableNewPositionsLabel = new javax.swing.JLabel();
        freePositionsComboBox = new javax.swing.JComboBox<>();
        moveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(i18n.getString("title"));

        jPanel1.setToolTipText("");

        articleCodeLabel.setText(i18n.getString("article_code_label"));

        searchButton.setText(i18n.getString("search"));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        articleOldPositionLabel.setText(i18n.getString("article_current_position_label"));

        articleOldPositionTextField.setEditable(false);
        articleOldPositionTextField.setBorder(null);

        avaiableNewPositionsLabel.setText(i18n.getString("avaiable_positions_label"));

        freePositionsComboBox.setModel(moveArticleController.getFreePositionComboBoxModel());

        moveButton.setText(i18n.getString("move_button"));
        moveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(moveButton)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(articleCodeLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(articleCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(searchButton))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(articleOldPositionLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(articleOldPositionTextField))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(avaiableNewPositionsLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(freePositionsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(articleCodeLabel)
                    .addComponent(articleCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(articleOldPositionLabel)
                    .addComponent(articleOldPositionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(avaiableNewPositionsLabel)
                    .addComponent(freePositionsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(moveButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String articleCode = articleCodeTextField.getText();
        article = ArticleModel.getInstance().find(articleCode);

        if (article == null) {
            JOptionPane.showMessageDialog(this,
                    i18n.getString("error_invalid_article_code"),
                    i18n.getString("error_title"),
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        articleOldPositionTextField.setText(article.getPosition().getRawPosition());
    }//GEN-LAST:event_searchButtonActionPerformed

    private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveButtonActionPerformed
        String articleCode = articleCodeTextField.getText();
        String positionId = (String)freePositionsComboBox.getSelectedItem();

        if (article == null) {
            JOptionPane.showMessageDialog(this,
                    i18n.getString("error_invalid_article_code"),
                    i18n.getString("error_title"),
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        boolean positionHasChanged = moveArticleController.moveArticlePositionByCodes(articleCode, positionId);

        if (positionHasChanged) {
            moveArticleController.update();
            articleCodeTextField.setText("");
            articleOldPositionTextField.setText("");
            freePositionsComboBox.setModel(moveArticleController.getFreePositionComboBoxModel());
        }
    }//GEN-LAST:event_moveButtonActionPerformed

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MoveArticleView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel articleCodeLabel;
    private javax.swing.JTextField articleCodeTextField;
    private javax.swing.JLabel articleOldPositionLabel;
    private javax.swing.JTextField articleOldPositionTextField;
    private javax.swing.JLabel avaiableNewPositionsLabel;
    private javax.swing.JComboBox<String> freePositionsComboBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton moveButton;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
