package DAO.Interface;

import DAO.Entity.Paper;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface PaperDAO {
    void addPaper(Paper paper);
    void updatePaper(Paper paper);
    void deletePaper(String paperId);
    Paper getPaper(String paperId);
    List<Paper> findPapers(Paper paper);
}
