package DAO.Interface;

import DAO.Entity.Award;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface AwardDAO {
    void addAward(Award award);
    void updateAward(Award award);
    void deleteAward(String awardID);
    Award getAward(String awardID);
    List<Award> findAwards(Award award);
}
