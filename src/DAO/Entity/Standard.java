package DAO.Entity;

public class Standard {
    private String standardId;
    private String standardName;
    private String standardLevel;
    private String standardTime;
    private String supportMaterial;

    public Standard(){
    }

    public Standard(String standardId, String standardName, String standardLevel, String standardTime, String supportMaterial) {
        this.standardId = standardId;
        this.standardName = standardName;
        this.standardLevel = standardLevel;
        this.standardTime = standardTime;
        this.supportMaterial = supportMaterial;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getStandardLevel() {
        return standardLevel;
    }

    public void setStandardLevel(String standardLevel) {
        this.standardLevel = standardLevel;
    }

    public String getStandardTime() {
        return standardTime;
    }

    public void setStandardTime(String standardTime) {
        this.standardTime = standardTime;
    }

    public String getSupportMaterial() {
        return supportMaterial;
    }

    public void setSupportMaterial(String supportMaterial) {
        this.supportMaterial = supportMaterial;
    }

    @Override
    public String toString() {
        return "Standard{" +
                "standardId='" + standardId + '\'' +
                ", standardName='" + standardName + '\'' +
                ", standardLevel='" + standardLevel + '\'' +
                ", standardTime='" + standardTime + '\'' +
                ", supportMaterial='" + supportMaterial + '\'' +
                '}';
    }
}
