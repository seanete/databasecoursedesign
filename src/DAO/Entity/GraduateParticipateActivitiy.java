package DAO.Entity;

import java.awt.*;

public class GraduateParticipateActivitiy {
    private Graduate graduate;
    private AcademicExchangeActivitiy academicExchangeActivitiy;
    private String reportNameChineseEnglish;
    private String pictureAttendence;
    private String remark;

    private String mentorSign;

    public GraduateParticipateActivitiy() {
    }

    public GraduateParticipateActivitiy(Graduate graduate, AcademicExchangeActivitiy academicExchangeActivitiy, String reportNameChineseEnglish, String pictureAttendence, String remark, String mentorSign) {
        this.graduate = graduate;
        this.academicExchangeActivitiy = academicExchangeActivitiy;
        this.reportNameChineseEnglish = reportNameChineseEnglish;
        this.pictureAttendence = pictureAttendence;
        this.remark = remark;
        this.mentorSign = mentorSign;
    }

    public String getMentorSign() {
        return mentorSign;
    }

    public void setMentorSign(String mentorSign) {
        this.mentorSign = mentorSign;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public AcademicExchangeActivitiy getAcademicExchangeActivitiy() {
        return academicExchangeActivitiy;
    }

    public void setAcademicExchangeActivitiy(AcademicExchangeActivitiy academicExchangeActivitiy) {
        this.academicExchangeActivitiy = academicExchangeActivitiy;
    }

    public String getReportNameChineseEnglish() {
        return reportNameChineseEnglish;
    }

    public void setReportNameChineseEnglish(String reportNameChineseEnglish) {
        this.reportNameChineseEnglish = reportNameChineseEnglish;
    }

    public String getPictureAttendence() {
        return pictureAttendence;
    }

    public void setPictureAttendence(String pictureAttendence) {
        this.pictureAttendence = pictureAttendence;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "GraduateParticipateActivitiy{" +
                "graduate=" + graduate +
                ", academicExchangeActivitiy=" + academicExchangeActivitiy +
                ", reportNameChineseEnglish='" + reportNameChineseEnglish + '\'' +
                ", pictureAttendence='" + pictureAttendence + '\'' +
                ", remark='" + remark + '\'' +
                ", mentorSign='" + mentorSign + '\'' +
                '}';
    }
}
