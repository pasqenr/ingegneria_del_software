package model;

import java.util.Collection;

/**
 * Represent a User, table <code>utente</code>.
 */
public class UserModel extends Model implements GenericDAO {
    private final String email;
    private final UserRole role;
    private final StoreModel store;

    /**
     * Create a new User.
     *
     * @param email An unique email.
     * @param role A valid role.
     * @param store A Store if the role is <code>Responsabile</code>, null otherwise.
     */
    public UserModel(String email, UserRole role, StoreModel store) {
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
    public UserModel(String email, UserRole role) {
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
    public UserRole getRole() {
        return role;
    }

    /**
     * @return The Store.
     */
    public StoreModel getStore() {
        return store;
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
    public Collection<UserModel> findAll() {
        return null;
    }

    @Override
    public boolean store() {
        return false;
    }
}
