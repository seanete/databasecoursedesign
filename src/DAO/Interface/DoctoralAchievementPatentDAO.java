package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Patent;
import DAO.Entity.DoctoralAchievementPatent;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface DoctoralAchievementPatentDAO {
    void addDoctoralAchievementPatent(DoctoralAchievementPatent doctoralAchievementPatent);
    void updateDoctoralAchievementPatent(DoctoralAchievementPatent doctoralAchievementPatent);
    void deleteDoctoralAchievementPatent(Graduate graduate, Patent patent);
    DoctoralAchievementPatent getDoctoralAchievementPatent(Graduate graduate, Patent patent);
    List<DoctoralAchievementPatent> findDoctoralAchievementPatents(DoctoralAchievementPatent doctoralAchievementPatent);
}