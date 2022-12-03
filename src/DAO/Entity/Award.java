package DAO.Entity;

public class Award {
    private String awardId;
    private String awardLevel;
    private String getAwardLevel;
    private String rank;
    private String awardTime;
    private String supportMaterial;

    public Award() {
    }

    public Award(String awardId, String awardLevel, String getAwardLevel, String rank, String awardTime, String supportMaterial) {
        this.awardId = awardId;
        this.awardLevel = awardLevel;
        this.getAwardLevel = getAwardLevel;
        this.rank = rank;
        this.awardTime = awardTime;
        this.supportMaterial = supportMaterial;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(String awardLevel) {
        this.awardLevel = awardLevel;
    }

    public String getGetAwardLevel() {
        return getAwardLevel;
    }

    public void setGetAwardLevel(String getAwardLevel) {
        this.getAwardLevel = getAwardLevel;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }

    public String getSupportMaterial() {
        return supportMaterial;
    }

    public void setSupportMaterial(String supportMaterial) {
        this.supportMaterial = supportMaterial;
    }

    @Override
    public String toString() {
        return "Award{" +
                "awardId='" + awardId + '\'' +
                ", awardLevel='" + awardLevel + '\'' +
                ", getAwardLevel='" + getAwardLevel + '\'' +
                ", rank='" + rank + '\'' +
                ", awardTime='" + awardTime + '\'' +
                ", supportMaterial='" + supportMaterial + '\'' +
                '}';
    }
}
