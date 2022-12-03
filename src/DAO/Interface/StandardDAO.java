package DAO.Interface;

import DAO.Entity.Standard;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface StandardDAO {
    void addStandard(Standard standard);
    void updateStandard(Standard standard);
    void deleteStandard(String standardID);
    Standard getStandard(String standardID);
    List<Standard> findStandards(Standard standard);
}
