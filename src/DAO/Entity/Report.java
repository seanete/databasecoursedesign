package DAO.Entity;

public class Report {
    private String reportId;
    private String reportName;
    private String reportType;
    private String serviceUnit;
    private String reportTime;
    private String supportMaterial;
    private String contributionRank;

    public Report(){
    }

    public Report(String reportId, String reportName, String reportType, String serviceUnit, String reportTime, String supportMaterial, String contributionRank) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.reportType = reportType;
        this.serviceUnit = serviceUnit;
        this.reportTime = reportTime;
        this.supportMaterial = supportMaterial;
        this.contributionRank = contributionRank;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getServiceUnit() {
        return serviceUnit;
    }

    public void setServiceUnit(String serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getSupportMaterial() {
        return supportMaterial;
    }

    public void setSupportMaterial(String supportMaterial) {
        this.supportMaterial = supportMaterial;
    }

    public String getContributionRank() {
        return contributionRank;
    }

    public void setContributionRank(String contributionRank) {
        this.contributionRank = contributionRank;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId='" + reportId + '\'' +
                ", reportName='" + reportName + '\'' +
                ", reportType='" + reportType + '\'' +
                ", serviceUnit='" + serviceUnit + '\'' +
                ", reportTime='" + reportTime + '\'' +
                ", supportMaterial='" + supportMaterial + '\'' +
                ", contributionRank='" + contributionRank + '\'' +
                '}';
    }
}
