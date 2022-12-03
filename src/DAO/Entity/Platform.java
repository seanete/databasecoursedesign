package DAO.Entity;

public class Platform {
    private String platformId;
    private String platformName;
    private String serviceUnit;
    private String platformTime;
    private String contributionRank;
    private String supportMaterial;

    public Platform(){
    }

    public Platform(String platformId, String platformName, String serviceUnit, String platformTime, String contributionRank, String supportMaterial) {
        this.platformId = platformId;
        this.platformName = platformName;
        this.serviceUnit = serviceUnit;
        this.platformTime = platformTime;
        this.contributionRank = contributionRank;
        this.supportMaterial = supportMaterial;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getServiceUnit() {
        return serviceUnit;
    }

    public void setServiceUnit(String serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    public String getPlatformTime() {
        return platformTime;
    }

    public void setPlatformTime(String platformTime) {
        this.platformTime = platformTime;
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
        return "Platform{" +
                "platformId='" + platformId + '\'' +
                ", platformName='" + platformName + '\'' +
                ", serviceUnit='" + serviceUnit + '\'' +
                ", platformTime='" + platformTime + '\'' +
                ", contributionRank='" + contributionRank + '\'' +
                ", supportMaterial='" + supportMaterial + '\'' +
                '}';
    }
}
