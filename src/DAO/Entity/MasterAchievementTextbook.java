package DAO.Entity;

public class MasterAchievementTextbook {
    private Graduate graduate;
    private Textbook textbook;

    public MasterAchievementTextbook(){
    }

    public MasterAchievementTextbook(Graduate graduate, Textbook textbook) {
        this.graduate = graduate;
        this.textbook = textbook;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Textbook getTextbook() {
        return textbook;
    }

    public void setTextbook(Textbook textbook) {
        this.textbook = textbook;
    }

    @Override
    public String toString() {
        return "MasterAchievementTextbook{" +
                "graduate=" + graduate +
                ", textbook=" + textbook +
                '}';
    }
}
