package DAO.Entity;

public class MasterAchievementPaper {
    private Graduate graduate;
    private Paper paper;

    public MasterAchievementPaper(){
    }

    public MasterAchievementPaper(Graduate graduate, Paper paper) {
        this.graduate = graduate;
        this.paper = paper;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    @Override
    public String toString() {
        return "MasterAchievementPaper{" +
                "graduate=" + graduate +
                ", paper=" + paper +
                '}';
    }
}
