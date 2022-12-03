package DAO.Interface;

import DAO.Entity.Administrator;

import java.util.List;

public interface AdministratorDAO {
    void addAdministrator(Administrator administrator);
    void updateAdministrator(Administrator administrator);
    void deleteAdministrator(String administratorNo);
    Administrator getAdministrator(String administratorNo);
    List<Administrator> findAdministrators(Administrator administrator);
}
