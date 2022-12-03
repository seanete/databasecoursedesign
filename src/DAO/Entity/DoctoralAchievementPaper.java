package DAO.Entity;

public class DoctoralAchievementPaper {
    private Graduate graduate;
    private Paper paper;

    public DoctoralAchievementPaper(){
    }

    public DoctoralAchievementPaper(Graduate graduate, Paper paper) {
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
        return "DoctoralAchievementPaper{" +
                "graduate=" + graduate +
                ", paper=" + paper +
                '}';
    }
}
