package model;

import java.util.Collection;

/**
 * GenericDAO interface.
 */
public interface GenericDAO {
    /**
     * Returns the Model identified by the id.
     *
     * @param id A valid identifier.
     * @return The Model identified by the id.
     */
    Model find(String id);

    /**
     * Returns all the Models.
     *
     * @return A list of all the Models in the database.
     */
    Collection<? extends Model> findAll();

    /**
     * Save in the database the current state of the object, creating a new entry or more entries.
     *
     * @return <code>true</code> if the object was stored, <code>false</code> otherwise.
     */
    boolean store();
}
