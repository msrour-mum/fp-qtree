package model;
import java.util.Date;
import java.util.List;


public class Comment {

    private long id;
    private String text;
    private Date date;
    private User user;

    public Comment(long id, String text,User user, Date date ) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.user = user;
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

    @Override
    public String toString() {

        return "Comment: {Id:"+getId()+"-Text:"+getText()+"-User:"+getUser()+"-Date:"+getDate()+"}";
        //return super.toString();
    }
}
