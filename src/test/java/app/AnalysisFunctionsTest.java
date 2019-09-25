package app;

import App.Analysis;
import App.FileTestDataReader;
import App.Qtree;
import model.Question;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;


class AnalysisFunctionsTest {
    private Analysis analysis;
    private  Qtree data = null;
    private FileTestDataReader reader=null;

    @BeforeEach
    void setUp() throws IOException {
        analysis = new Analysis();
        reader=new FileTestDataReader();
        data = reader.Read();
    }


    @Test
    void getKViewedQuestionsTest() throws IOException {
        List<Question> input= data.getQuestions();
        List<String> output= analysis.getKViewedQuestions(input,3);

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("Obtain column data from One-To-Many join relation in Spring data JPA");
        expectedResult.add("What is Generics?");
        expectedResult.add("How to clear a canvas in netbeans?");

        Assert.assertEquals(expectedResult,output);
    }

    @Test
    void getKTrendingTags() throws IOException {
        List<Question> input= data.getQuestions();
        Date from= new GregorianCalendar(2007, 1, 1).getTime();
        Date to= new GregorianCalendar(2009, 1, 1).getTime();

        List<String> output= analysis.getKTrendingTags(input,from,to,3);

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("Angular");
        expectedResult.add("Git");
        expectedResult.add("Java");

        Assert.assertEquals(expectedResult,output);
    }

    @Test
    void getKMostDislikedAnswers() throws IOException {
        FileTestDataReader reader=new FileTestDataReader();
        Qtree data= reader.Read();

        List<Question> input= data.getQuestions();

        List<String> output= analysis.getKMostDislikedAnswers(input,3);

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("Senate inquiry, interviews, first dates");
        expectedResult.add("YouTube channel");
        expectedResult.add("lol how you don't know that?");

        Assert.assertEquals(expectedResult,output);
    }
}