package app;

import App.FileTestDataReader;
import App.Qtree;
import App.TopComments;
import App.UserStatistics;
import model.Answer;
import model.Question;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

public class TopCommentsTest {
    private TopComments topCommentsObj;
    private Qtree qtree = null;
    @Before
    public void setup() throws IOException {
        topCommentsObj = new TopComments();

        FileTestDataReader reader=new FileTestDataReader();
        qtree = reader.Read();
    }

    @Test
    public void testTopCommentedQuestion(){
        Optional<Question> question =topCommentsObj.TopCommentedQuestion.apply(qtree.getQuestions());
        System.out.println("testTopCommentedQuestion");
        //System.out.println(question);
        Assert.assertEquals("Top Commented Question ", 13, question.get().getId());
    }

    @Test
    public void testTopCommentedAnswer(){
        Optional<Answer> answer =topCommentsObj.topCommentedAnswer.apply(qtree.getQuestions());
        System.out.println("testTopCommentedAnswer");
        //System.out.println(question);
        Assert.assertEquals("Top Commented Answer", 11, answer.get().getId());
    }

    @Test
    public void testgetQuestionCountByTagAndDate(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date dateFrom = null;
        Date dateTo = null;
        while (dateFrom == null) {
            String line1 ="2022-01-01" ;
            String line2 ="2019-01-01" ;
            try {
                dateFrom = format.parse(line1);
                dateTo=format.parse(line2);
            } catch (ParseException e) {
            }
        }

        Long count =topCommentsObj.getQuestionCountByTagAndDate.apply(qtree.getQuestions(),"Java",dateTo,dateFrom);
        System.out.println("testgetQuestionCountByTagAndDate");
        //System.out.println(question);
        Assert.assertEquals("count of Question Count By Tag And Date", 6, count.intValue());
    }
}
