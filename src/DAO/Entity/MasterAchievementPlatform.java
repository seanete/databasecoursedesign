package DAO.Entity;

public class MasterAchievementPlatform {
    private Graduate graduate;
    private Platform platform;

    public MasterAchievementPlatform(){
    }

    public MasterAchievementPlatform(Graduate graduate, Platform platform) {
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
        return "MasterAchievementPlatform{" +
                "graduate=" + graduate +
                ", platform=" + platform +
                '}';
    }
}
