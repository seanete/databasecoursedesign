package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Standard;
import DAO.Entity.MasterAchievementStandard;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface MasterAchievementStandardDAO {
    void addMasterAchievementStandard(MasterAchievementStandard masterAchievementStandard);
    void updateMasterAchievementStandard(MasterAchievementStandard masterAchievementStandard);
    void deleteMasterAchievementStandard(Graduate graduate, Standard standard);
    MasterAchievementStandard getMasterAchievementStandard(Graduate graduate, Standard standard);
    List<MasterAchievementStandard> findMasterAchievementStandards(MasterAchievementStandard masterAchievementStandard);
}