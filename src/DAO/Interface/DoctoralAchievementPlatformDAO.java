package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Platform;
import DAO.Entity.DoctoralAchievementPlatform;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface DoctoralAchievementPlatformDAO {
    void addDoctoralAchievementPlatform(DoctoralAchievementPlatform doctoralAchievementPlatform);
    void updateDoctoralAchievementPlatform(DoctoralAchievementPlatform doctoralAchievementPlatform);
    void deleteDoctoralAchievementPlatform(Graduate graduate, Platform platform);
    DoctoralAchievementPlatform getDoctoralAchievementPlatform(Graduate graduate, Platform platform);
    List<DoctoralAchievementPlatform> findDoctoralAchievementPlatforms(DoctoralAchievementPlatform doctoralAchievementPlatform);
}