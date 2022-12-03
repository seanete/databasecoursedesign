package DAO.Entity;

public class AcademicExchangeActivitiy {
    private String activityId;
    private String activityName;
    private String activityTime;
    private String activityLocation;

    public AcademicExchangeActivitiy() {
    }

    public AcademicExchangeActivitiy(String activityId, String activityName, String activityTime, String activityLocation) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityTime = activityTime;
        this.activityLocation = activityLocation;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }

    @Override
    public String toString() {
        return "AcademicExchangeActivitiy{" +
                "activityId='" + activityId + '\'' +
                ", activityName='" + activityName + '\'' +
                ", activityTime='" + activityTime + '\'' +
                ", activityLocation='" + activityLocation + '\'' +
                '}';
    }
}
