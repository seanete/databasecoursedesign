package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Patent;
import DAO.Entity.MasterAchievementPatent;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface MasterAchievementPatentDAO {
    void addMasterAchievementPatent(MasterAchievementPatent masterAchievementPatent);
    void updateMasterAchievementPatent(MasterAchievementPatent masterAchievementPatent);
    void deleteMasterAchievementPatent(Graduate graduate, Patent patent);
    MasterAchievementPatent getMasterAchievementPatent(Graduate graduate, Patent patent);
    List<MasterAchievementPatent> findMasterAchievementPatents(MasterAchievementPatent masterAchievementPatent);
}