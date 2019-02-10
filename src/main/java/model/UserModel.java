package model;

import java.util.List;

/**
 * Represent a User, table <code>utente</code>.
 */
public class UserModel extends Model implements GenericDAO {
    private String email;
    private String role;
    private StoreModel store;

    /**
     * Create a new User.
     *
     * @param email An unique email.
     * @param role A valid role.
     * @param store A Store if the role is <code>Responsabile</code>, null otherwise.
     */
    public UserModel(String email, String role, StoreModel store) {
        this.email = email;
        this.role = role;
        this.store = store;
    }

    /**
     * Create a new User without the Store.
     *
     * @param email An unique email.
     * @param role A valid role.
     */
    public UserModel(String email, String role) {
        this(email, role, null);
    }

    /**
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The role.
     */
    public String getRole() {
        return role;
    }

    /**
     * @param email The new email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The Store.
     */
    public StoreModel getStore() {
        return store;
    }

    /**
     * @param store The new Store.
     */
    public void setStore(StoreModel store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "UserModel: email: " + email + ", role: " + role;
    }

    @Override
    public UserModel find(String id) {
        return null;
    }

    @Override
    public List<UserModel> findAll() {
        return null;
    }

    @Override
    public boolean store() {
        return false;
    }
}
