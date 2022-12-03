package DAO.Entity;

public class DoctoralAchievementPatent {
    private Graduate graduate;
    private Patent patent;

    public DoctoralAchievementPatent(){
    }

    public DoctoralAchievementPatent(Graduate graduate, Patent patent) {
        this.graduate = graduate;
        this.patent = patent;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Patent getPatent() {
        return patent;
    }

    public void setPatent(Patent patent) {
        this.patent = patent;
    }

    @Override
    public String toString() {
        return "DoctoralAchievementPatent{" +
                "graduate=" + graduate +
                ", patent=" + patent +
                '}';
    }
}
