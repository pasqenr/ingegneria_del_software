package controller;

import model.UserModel;
import model.UserRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest extends GenericControllerTest {
    @Test
    void loginSuccessTest() {
        final String validEmail = "mail@mail.com";
        final String validPassword = "qwerty";
        final UserRole validRole = UserRole.WORKER;
        final UserModel referenceUserModel = new UserModel(validEmail, validRole);
        final LoginController loginController = new LoginController();
        final UserModel validUserModel = loginController.login(validEmail, validPassword);

        assertNotNull(validUserModel);
        assertEquals(referenceUserModel.getEmail(), validUserModel.getEmail());
        assertEquals(referenceUserModel.getRole(), validUserModel.getRole());
        assertEquals(validEmail, validUserModel.getEmail());
        assertEquals(validRole, validUserModel.getRole());
        assertTrue(loginController.isLogged());
        assertTrue(loginController.isLogged(referenceUserModel));
    }

    @Test
    void loginFailTest() {
        final LoginController loginController = new LoginController();
        final UserModel invalidUserModel = loginController.login("invalid@mail.com", "invalidPassword");

        assertNull(invalidUserModel);
        assertFalse(loginController.isLogged());
        assertFalse(loginController.isLogged(null));
    }
}
