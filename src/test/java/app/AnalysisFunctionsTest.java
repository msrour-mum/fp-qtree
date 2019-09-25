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
        Date to= new GregorianCalendar(2010, 1, 1).getTime();

        List<Tag> output=new AnalysisFunctions().getKTrendingTags(input,from,to,2);

        List<Tag> expectedResult= new ArrayList<>();
        expectedResult.add(reader.GetTag(1));
        expectedResult.add(reader.GetTag(9));


        Assert.assertEquals(expectedResult,output);
    }

    @Test
    void getKFakeAnswers() throws IOException {
        FileTestDataReader reader=new FileTestDataReader();
        Qtree data= reader.Read();

        List<Question> input= data.getQuestions();

        List<Answer> output=new AnalysisFunctions().getKFakeAnswers(input,3);

        List<Answer> expectedResult= new ArrayList<>();
        expectedResult.add(reader.GetAnswer(28));
        expectedResult.add(reader.GetAnswer(74));

        Assert.assertEquals(expectedResult,output);
    }
}