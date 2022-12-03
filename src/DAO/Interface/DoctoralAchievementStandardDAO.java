package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Standard;
import DAO.Entity.DoctoralAchievementStandard;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface DoctoralAchievementStandardDAO {
    void addDoctoralAchievementStandard(DoctoralAchievementStandard doctoralAchievementStandard);
    void updateDoctoralAchievementStandard(DoctoralAchievementStandard doctoralAchievementStandard);
    void deleteDoctoralAchievementStandard(Graduate graduate, Standard standard);
    DoctoralAchievementStandard getDoctoralAchievementStandard(Graduate graduate, Standard standard);
    List<DoctoralAchievementStandard> findDoctoralAchievementStandards(DoctoralAchievementStandard doctoralAchievementStandard);
}