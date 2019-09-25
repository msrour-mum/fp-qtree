package App;

import model.Question;
import model.Tag;
import model.User;

import java.util.List;

public class Qtree {

    private List<User> users;
    private List<Question> questions;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    private List<Tag> tags;

    public Qtree( List<Question> questions,  List<User> users, List<Tag> tags) {
        this.users = users;
        this.questions = questions;
        this.tags=tags;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
