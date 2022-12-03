package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Paper;
import DAO.Entity.DoctoralAchievementPaper;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface DoctoralAchievementPaperDAO {
    void addDoctoralAchievementPaper(DoctoralAchievementPaper doctoralAchievementPaper);
    void updateDoctoralAchievementPaper(DoctoralAchievementPaper doctoralAchievementPaper);
    void deleteDoctoralAchievementPaper(Graduate graduate, Paper paper);
    DoctoralAchievementPaper getDoctoralAchievementPaper(Graduate graduate, Paper paper);
    List<DoctoralAchievementPaper> findDoctoralAchievementPapers(DoctoralAchievementPaper doctoralAchievementPaper);
}