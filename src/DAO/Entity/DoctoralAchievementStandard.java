package DAO.Entity;

public class DoctoralAchievementStandard {
    private Graduate graduate;
    private Standard standard;

    public DoctoralAchievementStandard(){
    }

    public DoctoralAchievementStandard(Graduate graduate, Standard standard) {
        this.graduate = graduate;
        this.standard = standard;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    @Override
    public String toString() {
        return "DoctoralAchievementStandard{" +
                "graduate=" + graduate +
                ", standard=" + standard +
                '}';
    }
}
