package DAO.Entity;

public class Patent {
    private String patentId;
    private String patentName;
    private String patentType;
    private String patentIndex;
    private String patentTime;
    private String patentStatus;
    private String contributionRank;
    private String supportMaterial;

    public Patent(){
    }

    public Patent(String patentId, String patentName, String patentType, String patentIndex, String patentTime, String patentStatus, String contributionRank, String supportMaterial) {
        this.patentId = patentId;
        this.patentName = patentName;
        this.patentType = patentType;
        this.patentIndex = patentIndex;
        this.patentTime = patentTime;
        this.patentStatus = patentStatus;
        this.contributionRank = contributionRank;
        this.supportMaterial = supportMaterial;
    }

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
    }

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }

    public String getPatentType() {
        return patentType;
    }

    public void setPatentType(String patentType) {
        this.patentType = patentType;
    }

    public String getPatentIndex() {
        return patentIndex;
    }

    public void setPatentIndex(String patentIndex) {
        this.patentIndex = patentIndex;
    }

    public String getPatentTime() {
        return patentTime;
    }

    public void setPatentTime(String patentTime) {
        this.patentTime = patentTime;
    }

    public String getPatentStatus() {
        return patentStatus;
    }

    public void setPatentStatus(String patentStatus) {
        this.patentStatus = patentStatus;
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
        return "Patent{" +
                "patentId='" + patentId + '\'' +
                ", patentName='" + patentName + '\'' +
                ", patentType='" + patentType + '\'' +
                ", patentIndex='" + patentIndex + '\'' +
                ", patentTime='" + patentTime + '\'' +
                ", patentStatus='" + patentStatus + '\'' +
                ", contributionRank='" + contributionRank + '\'' +
                ", supportMaterial='" + supportMaterial + '\'' +
                '}';
    }
}
