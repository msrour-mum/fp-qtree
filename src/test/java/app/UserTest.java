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
import java.util.function.BiFunction;

public class UserTest {

    private UserStatistics userStatistics;
    private  Qtree qtree = null;


    @Before
    public void setup() throws IOException {
        userStatistics = new UserStatistics();

        FileTestDataReader reader=new FileTestDataReader();
        qtree = reader.Read();
    }

    @Test
    public void testGetTopKUsersHaveQuestions(){

        List<User> users = userStatistics.getTopKUsersHaveQuestions.apply(qtree,1);
        System.out.println("testGetTopKUsersHaveQuestions");
        System.out.println(users);

        Assert.assertEquals("Number of users", 1, users.size());
        Assert.assertEquals("User that has more questions:", 8, users.get(0).getId());
        System.out.println("Tested");
    }

    @Test
    public void testGetTopKUsersHaveQuestions2() {

        List<User> users = userStatistics.getTopKUsersHaveQuestions.apply(qtree,2);

        Assert.assertEquals("Number of users", 2, users.size());
        System.out.println("testGetTopKUsersHaveQuestions2");
        System.out.println(users);
        Assert.assertEquals("User that has more questions_2:", 8, users.get(0).getId());
        System.out.println("Tested");
    }

    @Test
    public void testGetTopKUsersReputationBasedOnAnswersVotes(){

        List<User> users = userStatistics.getTopKUsersReputationBasedOnAnswersVotes.apply(qtree,3);

        System.out.println("testGetTopKUsersReputationBasedOnAnswersVotes");
        System.out.println(users);
        Assert.assertEquals("Number of users", 3, users.size());
        Assert.assertEquals("User that has more questions:", 2, users.get(1).getId());
        System.out.println("Tested");
    }

    @Test
    public void testGetTopKUsersReputationBasedOnQuestionAndAnswers(){

        List<User> users = userStatistics.getTopKUsersReputationBasedOnQuestionAndAnswers.apply(qtree,3);

        System.out.println("testGetTopKUsersReputationBasedOnQuestionAndAnswers");
        System.out.println(users);
        Assert.assertEquals("Number of users", 3, users.size());
        Assert.assertEquals("User that has more questions:", 8, users.get(0).getId());
        System.out.println("Tested");
    }




    @Test
    public void testMostAnsweringUser(){

    }

    @Test
    public void testTopActiveUser(){

    }

    @Test
    public void testTopReputatedUser(){

        List<User> users = qtree.getUsers();
        BiFunction<Qtree,List<User>,String> topReputatedUser = (q,l) ->l.stream().
                max((user1,user2)-> (int) (userStatistics.getUserReputationByAnswers.apply(q,user2)- userStatistics.
                                        getUserReputationByAnswers.apply(q,user1))).get().getName() ;

        String expected = topReputatedUser.apply(qtree,users);

        String result = userStatistics.topReputatedUser.apply(qtree);

        Assert.assertEquals("top Reputated User",expected,result);
    }


}
