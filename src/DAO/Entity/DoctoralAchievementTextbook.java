package DAO.Entity;

public class DoctoralAchievementTextbook {
    private Graduate graduate;
    private Textbook textbook;

    public DoctoralAchievementTextbook(){
    }

    public DoctoralAchievementTextbook(Graduate graduate, Textbook textbook) {
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
        return "DoctoralAchievementTextbook{" +
                "graduate=" + graduate +
                ", textbook=" + textbook +
                '}';
    }
}
