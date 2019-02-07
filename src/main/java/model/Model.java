package model;

/**
 * A generic Model.
 */
public abstract class Model {
    /**
     * Save in the database the current state of the object, creating a new entry or more entries.
     *
     * @return <code>true</code> if the object was stored, <code>false</code> otherwise.
     */
    public abstract boolean store();
}
