package DAO.Interface;

import DAO.Entity.subjectHeaderView;

import java.util.List;

public interface subjectHeaderViewDAO {
    List<subjectHeaderView> getSubjectHeaderView(String hostNo);
}
