package view;

import model.UserModel;
import model.UserRole;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class SecretaryView extends javax.swing.JFrame {
    private final ResourceBundle i18n;
    private final UserModel user;

    /**
     * Creates new form SecretaryView
     *
     * @param user The user.
     */
    public SecretaryView(final UserModel user) {
        i18n = ResourceBundle.getBundle("SecretaryView", Locale.getDefault());
        this.user = user;

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

        javax.swing.JLabel loggedAsLabel = new javax.swing.JLabel();
        javax.swing.JTextField emailTextField = new javax.swing.JTextField();
        javax.swing.JButton insertArticleTypeButton = new javax.swing.JButton();
        javax.swing.JButton ordersFulfillmentButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(i18n.getString("title"));

        loggedAsLabel.setText(i18n.getString("logged_as"));

        emailTextField.setEditable(false);
        emailTextField.setText(user.getEmail());
        emailTextField.setBorder(null);

        insertArticleTypeButton.setText(i18n.getString("insert_article_type"));
        insertArticleTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertArticleTypeButtonActionPerformed(evt);
            }
        });

        ordersFulfillmentButton.setText(i18n.getString("orders_fulfillment"));
        ordersFulfillmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersFulfillmentButtonActionPerformed(evt);
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
                        .addComponent(loggedAsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(insertArticleTypeButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ordersFulfillmentButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loggedAsLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(insertArticleTypeButton)
                .addGap(18, 18, 18)
                .addComponent(ordersFulfillmentButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void insertArticleTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertArticleTypeButtonActionPerformed
        SwingUtilities.invokeLater(() -> new InsertArticleTypeView().setVisible(true));
    }//GEN-LAST:event_insertArticleTypeButtonActionPerformed

    private void ordersFulfillmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersFulfillmentButtonActionPerformed
        SwingUtilities.invokeLater(() -> new OrdersFulfillmentView().setVisible(true));
    }//GEN-LAST:event_ordersFulfillmentButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SecretaryView(
                new UserModel("mario.rossi@gmail.com", UserRole.SECRETARY, null)).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
