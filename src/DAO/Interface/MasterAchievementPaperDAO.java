package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Paper;
import DAO.Entity.MasterAchievementPaper;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface MasterAchievementPaperDAO {
    void addMasterAchievementPaper(MasterAchievementPaper masterAchievementPaper);
    void updateMasterAchievementPaper(MasterAchievementPaper masterAchievementPaper);
    void deleteMasterAchievementPaper(Graduate graduate, Paper paper);
    MasterAchievementPaper getMasterAchievementPaper(Graduate graduate, Paper paper);
    List<MasterAchievementPaper> findMasterAchievementPapers(MasterAchievementPaper masterAchievementPaper);
}