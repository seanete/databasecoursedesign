package TeachingAssistantSystem.Model;

import DAO.Entity.GraduateTutor;
import DAO.Entity.GraduateTutorVolunteer;
import DAO.Factory.DAOFactory;

public class Update {

    public void InsertGraduateTutorVolunteer(GraduateTutorVolunteer graduateTutorVolunteer) throws Exception{
        DAOFactory.getInstance().getGraduateTutorVolunteerDAO().addGraduateTutorVolunteer(graduateTutorVolunteer);
    }

    public void updateGraduateTutor(GraduateTutor graduateTutor) throws  Exception{
        DAOFactory.getInstance().getGraduateTutorDAO().updateGraduateTutor(graduateTutor);
    }
}
