package model;

public class SportModel {
    private String name;

    public SportModel(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  SportModel)) {
            return false;
        }

        SportModel sportModel = (SportModel)o;

        return name.equals(sportModel.name);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + name.hashCode();

        return result;
    }
}
