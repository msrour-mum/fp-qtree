package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer {

    private long id;
    private String text;
    private Date date;
    private User user;
    private boolean isVerified;
    private List<Comment> comments;
    private List<Vote> votes;

    public Answer(long id, String text,  User user, Date date,boolean isVerified, List<Comment> comments, List<Vote> votes) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.user = user;
        this.isVerified = isVerified;
        this.comments = comments;
        this.votes = votes;

        if (this.comments==null)
            this.comments=new ArrayList<>();
        if (this.votes==null)
            this.votes=new ArrayList<>();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
