package AcademicExchangeSystem;

import DAO.Entity.*;
import DAO.Implement.AcademicExchangeActivitiyDAOImpl;
import DAO.Implement.GraduateParticipateActivityViewDAOImpl;
import DAO.Implement.MentorLeadGraduateDAOImpl;
import DAO.Interface.AcademicExchangeActivitiyDAO;
import DAO.Implement.GraduateParticipateActivitiyDAOImpl;
import DAO.Interface.GraduateParticipateActivitiyDAO;
import DAO.Interface.GraduateParticipateActivityViewDAO;
import DAO.Interface.MentorLeadGraduateDAO;

import java.util.List;

public class Model {
    // 插入活动表
    static String insertActivity(AcademicExchangeActivitiy aea){
        AcademicExchangeActivitiyDAO aeaDao = new AcademicExchangeActivitiyDAOImpl();
        aeaDao.addAcademicExchangeActivitiy(aea);
        return "插入模块运行完毕";
    }
    // 更新活动表
    static String updateActivity(AcademicExchangeActivitiy aea){
        AcademicExchangeActivitiyDAO aeaDAO = new AcademicExchangeActivitiyDAOImpl();
        aeaDAO.updateAcademicExchangeActivitiy(aea);
        return "更新模块运行完毕";
    }
    // 删除活动表项
    static String deleteActivity(String str){
        AcademicExchangeActivitiyDAO aeaDAO = new AcademicExchangeActivitiyDAOImpl();
        aeaDAO.deleteAcademicExchangeActivitiy(str);
        return "删除模块运行完毕";
    }
    // ID查询活动表项
    static AcademicExchangeActivitiy getActivity(String str){
        AcademicExchangeActivitiyDAO aeaDAO = new AcademicExchangeActivitiyDAOImpl();
        return aeaDAO.getAcademicExchangeActivitiy(str);
    }
    // 搜索活动表项
    static List<AcademicExchangeActivitiy> findActivity(AcademicExchangeActivitiy aea){
        AcademicExchangeActivitiyDAO aeaDAO = new AcademicExchangeActivitiyDAOImpl();
        return aeaDAO.findAcademicExchangeActivitiys(aea);
    }
    //参与表
    //插入参与表
    static String addParticipate(GraduateParticipateActivitiy gpa){
        GraduateParticipateActivitiyDAO gpaDAO = new GraduateParticipateActivitiyDAOImpl();
        gpaDAO.addGraduateParticipateActivitiy(gpa);
        return "插入模块运行完毕";
    }
    //更新参与表
    static String updateParticipate(GraduateParticipateActivitiy gpa){
        GraduateParticipateActivitiyDAO gpaDAO = new GraduateParticipateActivitiyDAOImpl();
        gpaDAO.updateGraduateParticipateActivitiy(gpa);
        return "更新模块运行完毕";
    }
    //教师更新参与表
    static String updateParticipateSign(GraduateParticipateActivitiy gpa, String username){
        // 验证导师学生身份
        MentorLeadGraduateDAO mlgDAO = new MentorLeadGraduateDAOImpl();
        Mentor mentor = new Mentor();
        mentor.setMentorNo(username);
        Graduate graduate = new Graduate();
        graduate.setStudentNo(gpa.getGraduate().getStudentNo());
        MentorLeadGraduate checkRes = mlgDAO.getMentorLeadGraduate(mentor, graduate);
        if (checkRes.getGraduate() == null)
            return "权限错误，请勿修改非自身研究生数据！";
        GraduateParticipateActivitiyDAO gpaDAO = new GraduateParticipateActivitiyDAOImpl();
        gpaDAO.updateGraduateParticipateActivitiySign(gpa);
        return "更新模块运行完毕";
    }
    //删除参与表
    static String deleteParticipate(GraduateParticipateActivitiy gpa){
        GraduateParticipateActivitiyDAO gpaDAO = new GraduateParticipateActivitiyDAOImpl();
        gpaDAO.deleteGraduateParticipateActivitiy(gpa.getGraduate(), gpa.getAcademicExchangeActivitiy());
        return "删除模块运行完毕";
    }
    //ID查询参与表
    static GraduateParticipateActivitiy getParticipate(GraduateParticipateActivitiy gpa){
        GraduateParticipateActivitiyDAO gpaDAO = new GraduateParticipateActivitiyDAOImpl();
        return gpaDAO.getGraduateParticipateActivitiy(gpa.getGraduate(), gpa.getAcademicExchangeActivitiy());
    }
    //ID查询参与表以认定
    static List<GraduateParticipateActivitiy> getParticipateToExport(String graduateNo){
        Graduate gra = new Graduate();
        gra.setStudentNo(graduateNo);
        GraduateParticipateActivityViewDAO gpavDAO = new GraduateParticipateActivityViewDAOImpl();
        return gpavDAO.exportGraduateParticipateActivitiys(gra);
    }
    //查找参与表
    static List<GraduateParticipateActivitiy> findParticipate(GraduateParticipateActivitiy gpa){
        GraduateParticipateActivitiyDAO gpaDAO = new GraduateParticipateActivitiyDAOImpl();
        return gpaDAO.findGraduateParticipateActivitiys(gpa);
    }
}
