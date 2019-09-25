package app;

import App.FileTestDataReader;
import App.Qtree;
import App.UserStatistics;
import model.Answer;
import model.Question;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserStatisticsTest {
    FileTestDataReader reader = null;
    Qtree qtree = null;

    @Before
    public void setup() throws IOException {
        FileTestDataReader reader = new FileTestDataReader();
        Qtree qtree = reader.Read();
    }


    @Test
   public void topUserCommentsTest() throws IOException {

        FileTestDataReader reader = new FileTestDataReader();
        Qtree qtree = reader.Read();

        //List<Question> input = qtree.getQuestions();
        List<User> output = new UserStatistics().topUserComments.apply(qtree, 3);

        List<User> expectedResult = new ArrayList<>();
        expectedResult.add(reader.GetUser(2));
        expectedResult.add(reader.GetUser(5));
        expectedResult.add(reader.GetUser(6));
        System.out.println(expectedResult);
        Assert.assertEquals(expectedResult, output);
        System.out.println("Function topUserComments() testing passed");
    }


    @Test
   public void topRatedAnswersTest() throws IOException {

        FileTestDataReader reader = new FileTestDataReader();
        Qtree qtree = reader.Read();

        //List<Question> input = qtree.getQuestions();
        List<Answer> output = new UserStatistics().topRatedAnswers.apply(qtree, 3);

        List<Answer> expectedResult = new ArrayList<>();
        expectedResult.add(reader.GetAnswer(3));
        expectedResult.add(reader.GetAnswer(7));
        expectedResult.add(reader.GetAnswer(15));
        System.out.println(expectedResult);
        Assert.assertEquals(expectedResult, output);
        System.out.println("Function topRatedAnswers() testing passed");
    }

    @Test
    public void topAnsweredQuestionsTest() throws IOException {

        FileTestDataReader reader = new FileTestDataReader();
        Qtree qtree = reader.Read();

        List<Question> input = qtree.getQuestions();
        List<Question> output = new UserStatistics().topAnsweredQuestions.apply(qtree, 3);

        List<Question> expectedResult = new ArrayList<>();
        expectedResult.add(reader.GetQuestion(68));
        expectedResult.add(reader.GetQuestion(1));
        expectedResult.add(reader.GetQuestion(43));
        System.out.println(expectedResult);
        Assert.assertEquals(expectedResult, output);

        System.out.println("Function topAnsweredQuestions() testing passed");
    }
}