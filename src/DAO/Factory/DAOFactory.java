package DAO.Factory;

import DAO.Entity.AcademicExchangeActivitiy;
import DAO.Entity.GraduateParticipateActivitiy;
import DAO.Entity.Mentor;
import DAO.Entity.Subject;
import DAO.Implement.*;
import DAO.Interface.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    static{
        daoFactory = new DAOFactory();
    }

    public static DAOFactory getInstance(){
        return daoFactory;
    }

    public DAOFactory getDaoFactory() {
        return daoFactory;
    }

    public CourseDAO getCourseDAO(){
        return new CourseDAOImpl();
    }

    public GraduateDAO getGraduateDAO() {return new GraduateDAOImpl();}

    public TeacherDAO getTeacherDAO() {return new TeacherDAOImpl();}

    public GraduateTutorDAO getGraduateTutorDAO() {return new GraduateTutorDAOImpl();}

    public GraduateTutorVolunteerDAO getGraduateTutorVolunteerDAO() {return new GraduateTutorVolunteerImpl();}

    public TeacherTeachingDAO getTeacherTeachingDAO() {return new TeacherTeachingDAOImpl();}

    public MentorDAO getMentorDAO() {return new MentorDAOImpl();}

    public AdministratorDAO getAdministratorDAO(){
        return new AdministratorDAOImpl();
    }

    public MentorLeadGraduateDAO getMentorLeadGraduateDAO() {return new MentorLeadGraduateDAOImpl();}

    public AcademicExchangeActivitiyDAO getAcademicExchangeActivitiyDAO() {return new AcademicExchangeActivitiyDAOImpl();}

    public GraduateParticipateActivitiyDAO getGraduateParticipateActivitiyDAO() {return new GraduateParticipateActivitiyDAOImpl();}

    public ProjectDAO getProjectDAO() {return new ProjectDAOImpl();}

    public GraduateParticipateProjectDAO getGraduateParticipateProjectDAO() {return new GraduateParticipateProjectDAOImpl();}

    public SubjectDAO  getSubjectDAO() {return new SubjectDAOImpl();}

    public AwardDAO getAwardDAO(){return new AwardDAOImpl();}

    public PaperDAO getPaperDAO(){return new PaperDAOImpl();}

    public PatentDAO getPatentDAO(){return new PatentDAOImpl();}

    public PlatformDAO getPlatformDAO(){return new PlatformDAOImpl();}

    public ReportDAO getReportDAO(){return new ReportDAOImpl();}

    public StandardDAO getStandardDAO(){return new StandardDAOImpl();}

    public TextbookDAO getTextbookDAO(){return new TextbookDAOImpl();}

    public DoctoralAchievementAwardDAO getDoctoralAchievementAwardDAO(){return new DoctoralAchievementAwardDAOImpl();}

    public DoctoralAchievementPaperDAO getDoctoralAchievementPaperDAO(){return new DoctoralAchievementPaperDAOImpl();}

    public DoctoralAchievementPatentDAO getDoctoralAchievementPatentDAO(){return new DoctoralAchievementPatentDAOImpl();}

    public DoctoralAchievementPlatformDAO getDoctoralAchievementPlatformDAO(){return new DoctoralAchievementPlatformDAOImpl();}

    public DoctoralAchievementReportDAO getDoctoralAchievementReportDAO(){return new DoctoralAchievementReportDAOImpl();}

    public DoctoralAchievementStandardDAO getDoctoralAchievementStandardDAO(){return new DoctoralAchievementStandardDAOImpl();}

    public DoctoralAchievementTextbookDAO getDoctoralAchievementTextbookDAO(){return new DoctoralAchievementTextbookDAOImpl();}

    public MasterAchievementPaperDAO getMasterAchievementPaperDAO(){return new MasterAchievementPaperDAOImpl();}

    public MasterAchievementPatentDAO getMasterAchievementPatentDAO(){return new MasterAchievementPatentDAOImpl();}

    public MasterAchievementPlatformDAO getMasterAchievementPlatformDAO(){return new MasterAchievementPlatformDAOImpl();}

    public MasterAchievementReportDAO getMasterAchievementReportDAO(){return new MasterAchievementReportDAOImpl();}

    public MasterAchievementStandardDAO getMasterAchievementStandardDAO(){return new MasterAchievementStandardDAOImpl();}

    public MasterAchievementTextbookDAO getMasterAchievementTextbookDAO(){return new MasterAchievementTextbookDAOImpl();}

    public SubjectHeaderDAO getSubjectHeaderDAO(){
        return new SubjectHeaderDAOImpl();
    }

    /*助教子系统*/
    public graduateAssistantViewDAO getGraduateAssistantViewDAO() {return new graduateAssistantViewDAOImpl();}

    public teacherAssitantViewDAO getTeacherAssitantViewDAO() {return new teacherAssitantViewDAOImpl();}

    public subjectHeaderViewDAO getSubjectHeaderViewDAO() {return new subjectHeaderViewDAOImpl();}

    public evaluateViewDAO getEvaluateViewDAO() {return new evaluateViewDAOImpl();}

}
