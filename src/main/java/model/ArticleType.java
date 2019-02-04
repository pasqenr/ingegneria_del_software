package model;

public class ArticleType {
    private String name;
    private String description;
    private String materials;
    private SportModel sport;

    public ArticleType(String name, String description, String materials, SportModel sport) {
        this.name = name;
        this.description = description;
        this.materials = materials;
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public SportModel getSport() {
        return sport;
    }

    public void setSport(SportModel sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  ArticleType)) {
            return false;
        }

        ArticleType articleType = (ArticleType)o;

        return name.equals(articleType.name) &&
                name.equals(articleType.description) &&
                materials.equals(articleType.materials) &&
                sport.equals(articleType.sport);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + name.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + materials.hashCode();
        result = 31 * result + sport.hashCode();

        return result;
    }
}
