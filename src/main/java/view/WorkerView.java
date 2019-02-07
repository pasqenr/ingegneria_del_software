package view;

import model.UserModel;

import javax.swing.*;
import java.awt.*;

public class WorkerView extends javax.swing.JFrame {

    /**
     * Creates new form WorkerView
     *
     * @param user An User.
     */
    public WorkerView(UserModel user) {
        initComponents();

        emailTextField.setText(user.getEmail());

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

        ordersPanel = new javax.swing.JPanel();
        ordersLabel = new javax.swing.JLabel();
        articlesLabel = new javax.swing.JLabel();
        inOrdersButton = new javax.swing.JButton();
        loggedAsLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        outOrdersButton = new javax.swing.JButton();
        moveArticleButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Worker actions");

        ordersPanel.setLayout(new java.awt.BorderLayout());

        ordersLabel.setText("Orders");

        articlesLabel.setText("Articles");

        inOrdersButton.setText("Insert entrance");
        inOrdersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inOrdersButtonActionPerformed(evt);
            }
        });

        loggedAsLabel.setText("Logged as:");

        emailTextField.setEditable(false);
        emailTextField.setBorder(null);

        outOrdersButton.setText("Insert order");
        outOrdersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outOrdersButtonActionPerformed(evt);
            }
        });

        moveArticleButton.setText("Move article");
        moveArticleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveArticleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ordersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(loggedAsLabel))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(63, 63, 63)
                            .addComponent(ordersLabel)
                            .addGap(99, 99, 99)
                            .addComponent(articlesLabel))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(outOrdersButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(inOrdersButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(moveArticleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ordersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loggedAsLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ordersLabel)
                    .addComponent(articlesLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inOrdersButton)
                    .addComponent(moveArticleButton))
                .addGap(18, 18, 18)
                .addComponent(outOrdersButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moveArticleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveArticleButtonActionPerformed
        SwingUtilities.invokeLater(() -> new MoveArticleView().setVisible(true));
    }//GEN-LAST:event_moveArticleButtonActionPerformed

    private void inOrdersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inOrdersButtonActionPerformed
        SwingUtilities.invokeLater(() -> new InsertEntranceView().setVisible(true));
    }//GEN-LAST:event_inOrdersButtonActionPerformed

    private void outOrdersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outOrdersButtonActionPerformed
        SwingUtilities.invokeLater(() -> new InsertLeaveView().setVisible(true));
    }//GEN-LAST:event_outOrdersButtonActionPerformed

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WorkerView(new
                UserModel("mario.rossi@gmail.com", "'Magazziniere'")).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel articlesLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton inOrdersButton;
    private javax.swing.JLabel loggedAsLabel;
    private javax.swing.JButton moveArticleButton;
    private javax.swing.JLabel ordersLabel;
    private javax.swing.JPanel ordersPanel;
    private javax.swing.JButton outOrdersButton;
    // End of variables declaration//GEN-END:variables
}
