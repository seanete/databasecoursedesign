package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Textbook;
import DAO.Entity.DoctoralAchievementTextbook;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface DoctoralAchievementTextbookDAO {
    void addDoctoralAchievementTextbook(DoctoralAchievementTextbook doctoralAchievementTextbook);
    void updateDoctoralAchievementTextbook(DoctoralAchievementTextbook doctoralAchievementTextbook);
    void deleteDoctoralAchievementTextbook(Graduate graduate, Textbook textbook);
    DoctoralAchievementTextbook getDoctoralAchievementTextbook(Graduate graduate, Textbook textbook);
    List<DoctoralAchievementTextbook> findDoctoralAchievementTextbooks(DoctoralAchievementTextbook doctoralAchievementTextbook);
}