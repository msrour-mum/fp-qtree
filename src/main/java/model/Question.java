package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question {

    private long id;
    private String text;
    private User user;
    private Date date;
    private List<QuestionTag> tags;
    private List<Answer> answers;

    public List<QuestionViews> getViews() {
        return views;
    }

    public void setViews(List<QuestionViews> views) {
        this.views = views;
    }

    private List<QuestionViews> views;




    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    private List<Comment> comments;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Question(long id, String text, User user, Date date) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.date = date;

        if (this.answers==null)
            this.answers=new ArrayList<>();
        if (this.tags==null)
            this.tags=new ArrayList<>();
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
