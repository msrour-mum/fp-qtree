package model;

import java.util.Date;

public class QuestionViews {

    private Question question;
    private Date date;

    public QuestionViews(Question question, Date date) {
        this.question = question;
        this.date = date;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
