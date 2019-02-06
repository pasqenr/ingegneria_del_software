package view;

import controller.LoginController;
import model.UserModel;
import javax.swing.*;

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
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        // Set listeners
        loginButton.addActionListener(actionEvent -> {
            LoginController loginController = new LoginController();
            UserModel user = loginController.login(emailTextField.getText(),
                    charArrayToString(passwordTextField.getPassword()));

            if (loginController.isLogged(user)) {
                switch (user.getRole()) {
                    case "Magazziniere":
                        SwingUtilities.invokeLater(() -> new WorkerView(user).setVisible(true));
                        break;
                    case "Responsabile":
                        SwingUtilities.invokeLater(() -> new ManagerView(user).setVisible(true));
                        break;
                    case "Segreteria":
                        SwingUtilities.invokeLater(() -> new SecretaryView(user).setVisible(true));
                        break;
                }

                f.dispose();
            } else {
                JOptionPane.showMessageDialog(f,
                        "Wrong login data",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
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
