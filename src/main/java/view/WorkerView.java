package view;

import model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WorkerView extends JFrame {
    public WorkerView(UserModel user) {
        JButton moveArticleButton = new JButton("Move article");
        JLabel ordersLabel = new JLabel("Orders");
        JPanel ordersPanel = new JPanel();
        JPanel articlesPanel = new JPanel();
        JPanel userPanel = new JPanel();
        JButton showOrderButton = new JButton("Show orders");
        JButton outOrdersButton = new JButton("Out order");
        JButton inOrdersButton = new JButton("In order");
        JLabel articlesLabel = new JLabel("Articles");
        JLabel loggedAsLabel = new JLabel("Logged as:");
        JTextField emailTextField = new JTextField(user.getEmail());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Worker options");

        GroupLayout ordersPanelLayout = new GroupLayout(ordersPanel);
        ordersPanel.setLayout(ordersPanelLayout);
        ordersPanelLayout.setHorizontalGroup(
                ordersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(ordersPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(ordersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(showOrderButton, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                        .addComponent(outOrdersButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inOrdersButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        ordersPanelLayout.setVerticalGroup(
                ordersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(ordersPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(showOrderButton)
                                .addGap(18, 18, 18)
                                .addComponent(outOrdersButton)
                                .addGap(18, 18, 18)
                                .addComponent(inOrdersButton)
                                .addContainerGap(36, Short.MAX_VALUE))
        );

        moveArticleButton.setText("Move article");
        moveArticleButton.addActionListener(this::moveArticleButtonActionPerformed);

        GroupLayout articlesPanelLayout = new GroupLayout(articlesPanel);
        articlesPanel.setLayout(articlesPanelLayout);
        articlesPanelLayout.setHorizontalGroup(
                articlesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(articlesPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(moveArticleButton, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addContainerGap())
        );
        articlesPanelLayout.setVerticalGroup(
                articlesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(articlesPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(moveArticleButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ordersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ordersLabel.setText("Orders");

        articlesLabel.setText("Articles");

        loggedAsLabel.setText("Logged as:");

        emailTextField.setEditable(false);
        emailTextField.setBorder(null);

        GroupLayout userPanelLayout = new GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
                userPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(loggedAsLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(emailTextField))
        );
        userPanelLayout.setVerticalGroup(
                userPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, userPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(userPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loggedAsLabel)
                                        .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(userPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(ordersLabel)
                                                .addGap(137, 137, 137)
                                                .addComponent(articlesLabel)
                                                .addGap(64, 64, 64))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(ordersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(articlesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(userPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(ordersLabel)
                                        .addComponent(articlesLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(ordersPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(articlesPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
        setVisible(true);

        // Center the frame
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    private void moveArticleButtonActionPerformed(ActionEvent event) {
        SwingUtilities.invokeLater(() -> new MoveArticleView().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WorkerView(new
            UserModel("mario.rossi@gmail.com", "'Magazziniere'")));
    }
}
