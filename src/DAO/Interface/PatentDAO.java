package DAO.Interface;

import DAO.Entity.Patent;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface PatentDAO {
    void addPatent(Patent patent);
    void updatePatent(Patent patent);
    void deletePatent(String patentID);
    Patent getPatent(String patentID);
    List<Patent> findPatents(Patent patent);
}
