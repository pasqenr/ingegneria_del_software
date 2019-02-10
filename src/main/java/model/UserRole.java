package model;

/**
 * Represent the possible User roles.
 */
public enum UserRole {
    WORKER("Magazziniere"),
    SECRETARY("Segreteria"),
    MANAGER("Responsabile");

    private final String value;
    UserRole(final String value) { this.value = value; }
    public String getValue() { return value; }

    public static UserRole ofName(String name) {
        switch (name) {
            case "Magazziniere":
                return WORKER;
            case "Segreteria":
                return SECRETARY;
            case "Responsabile":
                return MANAGER;
        }

        return WORKER;
    }
}
