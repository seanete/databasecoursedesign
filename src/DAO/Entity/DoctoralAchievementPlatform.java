package DAO.Entity;

public class DoctoralAchievementPlatform {
    private Graduate graduate;
    private Platform platform;

    public DoctoralAchievementPlatform(){
    }

    public DoctoralAchievementPlatform(Graduate graduate, Platform platform) {
        this.graduate = graduate;
        this.platform = platform;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "DoctoralAchievementPlatform{" +
                "graduate=" + graduate +
                ", platform=" + platform +
                '}';
    }
}
