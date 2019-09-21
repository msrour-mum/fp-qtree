package model;

import java.util.Date;

public class Vote {

    private User user;
    private Date date;
    private boolean isLike;

    public Vote(User user, Date date, boolean isLike) {
        this.user = user;
        this.date = date;
        this.isLike = isLike;
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
