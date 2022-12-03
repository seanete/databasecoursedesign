package DAO.Entity;

public class MentorLeadGraduate {
    private Mentor mentor;
    private Graduate graduate;

    public MentorLeadGraduate() {
    }

    public MentorLeadGraduate(Mentor mentor, Graduate graduate) {
        this.mentor = mentor;
        this.graduate = graduate;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    @Override
    public String toString() {
        return "MentorLeadGraduate{" +
                "mentor=" + mentor +
                ", graduate=" + graduate +
                '}';
    }
}
