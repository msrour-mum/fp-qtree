package model;

public class QuestionTag {

    private  Question question;
    private  Tag tag;

    public QuestionTag(Question question, Tag tag) {
        this.question = question;
        this.tag = tag;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
