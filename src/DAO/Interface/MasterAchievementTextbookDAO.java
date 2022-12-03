package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Textbook;
import DAO.Entity.MasterAchievementTextbook;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface MasterAchievementTextbookDAO {
    void addMasterAchievementTextbook(MasterAchievementTextbook masterAchievementTextbook);
    void updateMasterAchievementTextbook(MasterAchievementTextbook masterAchievementTextbook);
    void deleteMasterAchievementTextbook(Graduate graduate, Textbook textbook);
    MasterAchievementTextbook getMasterAchievementTextbook(Graduate graduate, Textbook textbook);
    List<MasterAchievementTextbook> findMasterAchievementTextbooks(MasterAchievementTextbook masterAchievementTextbook);
}