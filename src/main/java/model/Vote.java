package model;

import java.util.Date;

public class Vote {
    private long id;
    private User user;
    private Date date;
    private boolean isLike;

    public Vote(long id, User user, Date date, boolean isLike) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.isLike = isLike;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
