package App;

import model.Question;
import model.User;

import java.util.List;

public class Qtree {

    private List<User> users;
    private List<Question> questions;

    public Qtree(List<User> users, List<Question> questions) {
        this.users = users;
        this.questions = questions;
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
