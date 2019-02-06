package model;

public class PositionModel {
    private String pos;

    public PositionModel(String pos) {
        this.pos = pos;
    }

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
