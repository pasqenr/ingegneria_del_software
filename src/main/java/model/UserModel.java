package model;

public class UserModel {
    private String email;
    private String role;

    public UserModel(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserModel: email: " + email + ", role: " + role;
    }
}
