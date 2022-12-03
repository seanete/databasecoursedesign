package DAO.Entity;

public class DoctoralAchievementReport {
    private Graduate graduate;
    private Report report;

    public DoctoralAchievementReport(){
    }

    public DoctoralAchievementReport(Graduate graduate, Report report) {
        this.graduate = graduate;
        this.report = report;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "DoctoralAchievementReport{" +
                "graduate=" + graduate +
                ", report=" + report +
                '}';
    }
}
