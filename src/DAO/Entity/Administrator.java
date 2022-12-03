package DAO.Entity;

public class Administrator {
    private String administratorNo;
    private String administratorName;

    public Administrator(){

    }

    public Administrator(String administratorNo, String administratorName) {
        this.administratorNo = administratorNo;
        this.administratorName = administratorName;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "administratorNo='" + administratorNo + '\'' +
                ", administratorName='" + administratorName + '\'' +
                '}';
    }

    public String getAdministratorNo() {
        return administratorNo;
    }

    public void setAdministratorNo(String administratorNo) {
        this.administratorNo = administratorNo;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }
}
