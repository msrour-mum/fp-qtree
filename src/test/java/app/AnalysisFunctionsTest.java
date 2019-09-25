package app;

import App.AnalysisFunctions;
import App.FileTestDataReader;
import App.Qtree;
import App.UserStatistics;
import model.Answer;
import model.Question;
import model.Tag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;


class AnalysisFunctionsTest {
    private AnalysisFunctions analysisFunctions;
    private  Qtree data = null;
    private FileTestDataReader reader=null;

    @BeforeEach
    void setUp() throws IOException {
        analysisFunctions = new AnalysisFunctions();
        reader=new FileTestDataReader();
        data = reader.Read();
    }


    @Test
    void getKViewedQuestionsTest() throws IOException {
        List<Question> input= data.getQuestions();
        List<Question> output=analysisFunctions.getKViewedQuestions(input,3);

        List<Question> expectedResult= new ArrayList<>();
        expectedResult.add(reader.GetQuestion(56));
        expectedResult.add(reader.GetQuestion(22));
        expectedResult.add(reader.GetQuestion(68));

        Assert.assertEquals(expectedResult,output);
    }

    @Test
    void getKTrendingTags() throws IOException {
        List<Question> input= data.getQuestions();
        Date from= new GregorianCalendar(2007, 1, 1).getTime();
        Date to= new GregorianCalendar(2009, 1, 1).getTime();

        List<String> output=analysisFunctions.getKTrendingTags(input,from,to,3);

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("Angular");
        expectedResult.add("Git");
        expectedResult.add("Java");

        Assert.assertEquals(expectedResult,output);
    }

    @Test
    void getKFakeAnswers() throws IOException {
        FileTestDataReader reader=new FileTestDataReader();
        Qtree data= reader.Read();

        List<Question> input= data.getQuestions();

        List<String> output=analysisFunctions.getKFakeAnswers(input,3);

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("Senate inquiry, interviews, first dates");
        expectedResult.add("YouTube channel");
        expectedResult.add("lol how you don't know that?");

        Assert.assertEquals(expectedResult,output);
    }
}