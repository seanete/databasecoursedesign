package DAO.Interface;

import DAO.Entity.Textbook;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface TextbookDAO {
    void addTextbook(Textbook textbook);
    void updateTextbook(Textbook textbook);
    void deleteTextbook(String textbookID);
    Textbook getTextbook(String textbookID);
    List<Textbook> findTextbooks(Textbook textbook);
}
