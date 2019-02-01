package view;

import controller.LoginController;
import model.UserModel;
import javax.swing.*;
import java.awt.*;

public class LoginView {
    public LoginView() {
        JFrame f = new JFrame();

        JButton loginButton = new JButton("Submit");
        JTextField emailTextField = new JTextField();
        JPasswordField passwordTextField = new JPasswordField();
        JLabel labelEmail = new JLabel("Email");
        JLabel labelPassword = new JLabel("Password");

        f.setTitle("Login");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout layout = new GroupLayout(f.getContentPane());
        f.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(loginButton)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(labelEmail)
                                                        .addComponent(labelPassword))
                                                .addGap(56, 56, 56)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(emailTextField, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                        .addComponent(passwordTextField))
                                                .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelEmail)
                                        .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelPassword)
                                        .addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addComponent(loginButton)
                                .addContainerGap(40, Short.MAX_VALUE))
        );

        f.pack();
        f.setVisible(true);

        // Center the frame
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - f.getWidth()) / 2;
        int y = (screenSize.height - f.getHeight()) / 2;
        f.setLocation(x, y);

        // Set listeners
        loginButton.addActionListener(actionEvent -> {
            LoginController loginController = new LoginController();
            UserModel user = loginController.login(emailTextField.getText(),
                    charArrayToString(passwordTextField.getPassword()));

            if (loginController.isLogged(user)) {
                System.out.println("Logged");
                System.out.println(user);
                SwingUtilities.invokeLater(() -> new WorkerView(user).setVisible(true));
                f.dispose();
            } else {
                System.out.println("Not logged");
            }
        });
    }

    private String charArrayToString(char[] array) {
        StringBuilder s = new StringBuilder();

        for (char c : array) {
            s.append(c);
        }

        return s.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginView::new);
    }
}
