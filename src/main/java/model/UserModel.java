package model;

public class UserModel {
    private String email;
    private String role;
    private StoreModel store;

    public UserModel(String email, String role, StoreModel store) {
        this.email = email;
        this.role = role;
        this.store = store;
    }

    public UserModel(String email, String role) {
        this(email, role, null);
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

    public StoreModel getStore() {
        return store;
    }

    public void setStore(StoreModel store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "UserModel: email: " + email + ", role: " + role;
    }
}
