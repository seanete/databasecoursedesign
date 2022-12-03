package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Platform;
import DAO.Entity.MasterAchievementPlatform;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface MasterAchievementPlatformDAO {
    void addMasterAchievementPlatform(MasterAchievementPlatform masterAchievementPlatform);
    void updateMasterAchievementPlatform(MasterAchievementPlatform masterAchievementPlatform);
    void deleteMasterAchievementPlatform(Graduate graduate, Platform platform);
    MasterAchievementPlatform getMasterAchievementPlatform(Graduate graduate, Platform platform);
    List<MasterAchievementPlatform> findMasterAchievementPlatforms(MasterAchievementPlatform masterAchievementPlatform);
}