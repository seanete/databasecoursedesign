package DAO.Entity;

public class DoctoralAchievementAward {
    private Graduate graduate;
    private Award award;

    public DoctoralAchievementAward(){
    }

    public DoctoralAchievementAward(Graduate graduate, Award award) {
        this.graduate = graduate;
        this.award = award;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    @Override
    public String toString() {
        return "DoctoralAchievementAward{" +
                "graduate=" + graduate +
                ", award=" + award +
                '}';
    }
}
