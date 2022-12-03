package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Award;
import DAO.Entity.DoctoralAchievementAward;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface DoctoralAchievementAwardDAO {
    void addDoctoralAchievementAward(DoctoralAchievementAward doctoralAchievementAward);
    void updateDoctoralAchievementAward(DoctoralAchievementAward doctoralAchievementAward);
    void deleteDoctoralAchievementAward(Graduate graduate, Award award);
    DoctoralAchievementAward getDoctoralAchievementAward(Graduate graduate, Award award);
    List<DoctoralAchievementAward> findDoctoralAchievementAwards(DoctoralAchievementAward doctoralAchievementAward);
}