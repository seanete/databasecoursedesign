package TeachingAssistantSystem.Model;

import DAO.Entity.GraduateTutor;
import DAO.Factory.DAOFactory;

public class SignUpdate {
    public void UpdateSign(GraduateTutor graduateTutor){
        DAOFactory.getInstance().getGraduateTutorDAO().updateGraduateTutor(graduateTutor);
    }
}
