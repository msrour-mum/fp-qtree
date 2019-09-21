package model;

import java.util.Date;
import java.util.List;

public class Question {

    private long id;
    private String text;
    private User user;
    private Date date;
    private List<QuestionTag> tags;

    public Question(long id, String text, User user, Date date) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<QuestionTag> getTags() {
        return tags;
    }

    public void setTags(List<QuestionTag> tags) {
        this.tags = tags;
    }
}
