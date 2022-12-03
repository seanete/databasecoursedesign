package AcademicExchangeSystem;

import DAO.Entity.AcademicExchangeActivitiy;
import DAO.Implement.AcademicExchangeActivitiyDAOImpl;
import DAO.Interface.AcademicExchangeActivitiyDAO;

import java.util.List;

public class Model {
    // 插入活动表
    static String insert(AcademicExchangeActivitiy aea){
        AcademicExchangeActivitiyDAO aeaDao = new AcademicExchangeActivitiyDAOImpl();
        aeaDao.addAcademicExchangeActivitiy(aea);
        return "插入模块运行完毕";
    }
    // 更新活动表
    static String update(AcademicExchangeActivitiy aea){
        AcademicExchangeActivitiyDAO aeaDAO = new AcademicExchangeActivitiyDAOImpl();
        aeaDAO.updateAcademicExchangeActivitiy(aea);
        return "更新模块运行完毕";
    }
    // 删除活动表项
    static String delete(String str){
        AcademicExchangeActivitiyDAO aeaDAO = new AcademicExchangeActivitiyDAOImpl();
        aeaDAO.deleteAcademicExchangeActivitiy(str);
        return "删除模块运行完毕";
    }
    // ID查询活动表项
    static AcademicExchangeActivitiy get(String str){
        AcademicExchangeActivitiyDAO aeaDAO = new AcademicExchangeActivitiyDAOImpl();
        return aeaDAO.getAcademicExchangeActivitiy(str);
    }
    // 搜索活动表项
    static List<AcademicExchangeActivitiy> find(AcademicExchangeActivitiy aea){
        AcademicExchangeActivitiyDAO aeaDAO = new AcademicExchangeActivitiyDAOImpl();
        return aeaDAO.findAcademicExchangeActivitiys(aea);
    }
}
