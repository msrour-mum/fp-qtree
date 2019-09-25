package app;

import App.FileTestDataReader;
import App.Qtree;
import App.UserStatistics;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class UserTest {

    private UserStatistics userStatistics;
    private  Qtree qtree = null;

    @Before
    public void setup() throws IOException {
        userStatistics = new UserStatistics();

        FileTestDataReader reader=new FileTestDataReader();
        qtree= reader.Read();
    }

    @Test
    public void testGetTopKUsersHaveQuestions()
    {
        List<User> users = userStatistics.getTopKUsersHaveQuestions.apply(qtree,1);

        Assert.assertEquals("Number of users", 1, users.size());
        Assert.assertEquals("User that has more questions:", 8, users.get(0).getId());
        System.out.println("Tested");
    }

    @Test
    public void testGetTopKUsersHaveQuestions2()
    {
        List<User> users = userStatistics.getTopKUsersHaveQuestions.apply(qtree,2);

        Assert.assertEquals("Number of users", 2, users.size());
        Assert.assertEquals("User that has more questions:", 8, users.get(0).getId());
        System.out.println("Tested");


    }

}
