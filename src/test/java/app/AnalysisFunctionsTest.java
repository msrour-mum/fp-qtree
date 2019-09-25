package app;

import App.AnalysisFunctions;
import App.FileTestDataReader;
import App.Qtree;
import model.Answer;
import model.Question;
import model.Tag;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;


class AnalysisFunctionsTest {

    @Test
    void getKViewedQuestionsTest() throws IOException {
        FileTestDataReader reader=new FileTestDataReader();
        Qtree data= reader.Read();

        List<Question> input= data.getQuestions();
        List<Question> output=new AnalysisFunctions().getKViewedQuestions(input,3);

        List<Question> expectedResult= new ArrayList<>();
        expectedResult.add(reader.GetQuestion(56));
        expectedResult.add(reader.GetQuestion(22));
        expectedResult.add(reader.GetQuestion(68));

        Assert.assertEquals(expectedResult,output);
    }

    @Test
    void getKTrendingTags() throws IOException {
        FileTestDataReader reader=new FileTestDataReader();
        Qtree data= reader.Read();

        List<Question> input= data.getQuestions();
        Date from= new GregorianCalendar(2007, 1, 1).getTime();
        Date to= new GregorianCalendar(2009, 1, 1).getTime();

        List<String> output=new AnalysisFunctions().getKTrendingTags(input,from,to,1);

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add(reader.GetTag(7).getName());

        Assert.assertEquals(expectedResult,output);
    }

    @Test
    void getKFakeAnswers() throws IOException {
        FileTestDataReader reader=new FileTestDataReader();
        Qtree data= reader.Read();

        List<Question> input= data.getQuestions();

        List<String> output=new AnalysisFunctions().getKFakeAnswers(input,3);

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("Senate inquiry, interviews, first dates");
        expectedResult.add("YouTube channel");
        expectedResult.add("lol how you don't know that?");

        Assert.assertEquals(expectedResult,output);
    }
}