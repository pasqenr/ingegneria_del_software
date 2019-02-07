package model;

/**
 * Represent a Position, table <code>posizione</code>.
 */
public class PositionModel {
    private String pos;

    /**
     * Create a new Position.
     *
     * @param pos A valid Position code.
     */
    public PositionModel(String pos) {
        this.pos = pos;
    }

    /**
     * @return The String representation of the Position, that is, the Position identifier.
     */
    public String getRawPosition() {
        return pos;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  PositionModel)) {
            return false;
        }

        PositionModel position = (PositionModel)o;

        return pos.equals(position.pos);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + pos.hashCode();

        return result;
    }
}
