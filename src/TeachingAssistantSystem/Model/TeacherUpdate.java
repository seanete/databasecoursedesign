package TeachingAssistantSystem.Model;

import DAO.Entity.GraduateTutor;
import DAO.Entity.GraduateTutorVolunteer;
import DAO.Entity.TeacherTeaching;
import DAO.Factory.DAOFactory;
import DAO.Interface.GraduateTutorDAO;
import DAO.Interface.GraduateTutorVolunteerDAO;
import DAO.Interface.TeacherTeachingDAO;

public class TeacherUpdate {

    public void DeleteGraduateTutorVolunteer(String studentNo){
        DAOFactory.getInstance().getGraduateTutorVolunteerDAO().deleteGraduateTutorVolunteer(studentNo);
    }

    public void UpdateTeacherTeaching(TeacherTeaching teacherTeaching){
        DAOFactory.getInstance().getTeacherTeachingDAO().updateTeacherTeaching(teacherTeaching);
    }

    public void AddGraduateTutor(GraduateTutor graduateTutor){
        DAOFactory.getInstance().getGraduateTutorDAO().addGraduateTutor(graduateTutor);
    }

    public void updateGraduateTutor(GraduateTutor graduateTutor) throws  Exception{
        DAOFactory.getInstance().getGraduateTutorDAO().updateGraduateTutor(graduateTutor);
    }
}
