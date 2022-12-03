package DAO.Interface;

import DAO.Entity.teacherAssitantView;

import java.util.List;

public interface teacherAssitantViewDAO {
    List<teacherAssitantView> getTeacherAssitantView(String courseNo);
}
