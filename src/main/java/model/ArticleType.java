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
}
