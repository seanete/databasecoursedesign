package TeachingAssistantSystem.Model;

import DAO.Entity.Course;
import DAO.Factory.DAOFactory;

public class SubjectUpdate {
    public void UpdatePri(Course course){
        DAOFactory.getInstance().getCourseDAO().updateCourse(course);
    }
}
