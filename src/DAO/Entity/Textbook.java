package DAO.Entity;

public class Textbook {
    private String textbookId;
    private String textbookName;
    private String publicHouse;
    private String publicationTime;
    private String contributionRank;
    private String supportMaterial;

    public Textbook(){
    }

    public Textbook(String textbookId, String textbookName, String publicHouse, String publicationTime, String contributionRank, String supportMaterial) {
        this.textbookId = textbookId;
        this.textbookName = textbookName;
        this.publicHouse = publicHouse;
        this.publicationTime = publicationTime;
        this.contributionRank = contributionRank;
        this.supportMaterial = supportMaterial;
    }

    public String getTextbookId() {
        return textbookId;
    }

    public void setTextbookId(String textbookId) {
        this.textbookId = textbookId;
    }

    public String getTextbookName() {
        return textbookName;
    }

    public void setTextbookName(String textbookName) {
        this.textbookName = textbookName;
    }

    public String getPublicHouse() {
        return publicHouse;
    }

    public void setPublicHouse(String publicHouse) {
        this.publicHouse = publicHouse;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(String publicationTime) {
        this.publicationTime = publicationTime;
    }

    public String getContributionRank() {
        return contributionRank;
    }

    public void setContributionRank(String contributionRank) {
        this.contributionRank = contributionRank;
    }

    public String getSupportMaterial() {
        return supportMaterial;
    }

    public void setSupportMaterial(String supportMaterial) {
        this.supportMaterial = supportMaterial;
    }

    @Override
    public String toString() {
        return "Textbook{" +
                "textbookId='" + textbookId + '\'' +
                ", textbookName='" + textbookName + '\'' +
                ", publicHouse='" + publicHouse + '\'' +
                ", publicationTime='" + publicationTime + '\'' +
                ", contributionRank='" + contributionRank + '\'' +
                ", supportMaterial='" + supportMaterial + '\'' +
                '}';
    }
}
