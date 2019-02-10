package controller;

import model.UserModel;
import model.UserRole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {
    private String validEmail = "mail@mail.com";
    private String validPassword = "qwerty";
    private UserRole validRole = UserRole.WORKER;
    private UserModel referenceUserModel;

    @Test
    void loginSuccessTest() {
        referenceUserModel = new UserModel(validEmail, validRole);
        LoginController loginController = new LoginController();
        UserModel validUserModel = loginController.login(validEmail, validPassword);

        assertNotNull(validUserModel);
        assertEquals(referenceUserModel.getEmail(), validUserModel.getEmail());
        assertEquals(referenceUserModel.getRole(), validUserModel.getRole());
        assertEquals(validEmail, validUserModel.getEmail());
        assertEquals(validRole, validUserModel.getRole());
    }

    @Test
    void loginFailTest() {
        LoginController loginController = new LoginController();
        UserModel invalidUserModel = loginController.login("invalid@mail.com", "invalidPassword");

        assertNull(invalidUserModel);
    }
}
