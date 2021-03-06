package App;

import FuncInterface.FunctionX4;
import model.*;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Analysis {
    public List<String> getKViewedQuestions(List<Question> questions, Integer k) {
        List<String> result = null;
        BiFunction<List<Question>, Integer, List<String>> getKViewedQuest =
                (q, n) -> q.stream()
                        .sorted((q1, q2) -> q2.getViews().size() - q1.getViews().size())
                        .limit(n)
                        .map(question -> question.getText())
                        .collect(Collectors.toList());
        result = getKViewedQuest.apply(questions, k);
        return result;
    }
    public List<String> getKTrendingTags(List<Question> questions, Date from, Date to, Integer K) {
        List<String> result = null;
        FunctionX4<List<Question>, Date, Date, Integer, List<String>> getKTrendingTags =
                (q, df, dt, n) -> q.stream()
                        .filter((question) -> question.getDate().compareTo(df) >0 && question.getDate().compareTo(dt) < 0)
                        .flatMap((qt) -> qt.getTags().stream())
                        .collect(Collectors.groupingBy(QuestionTag::getTag, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .map(tagListEntry -> (Tag) tagListEntry.getKey())
                        .limit(n)
                        .map(tag -> tag.getName())
                        .sorted()
                        .collect(Collectors.toList());
        result = getKTrendingTags.apply(questions, from, to, K);
        return result;
    }
    public List<String> getKMostDislikedAnswers(List<Question> questions, Integer topCount)
    {
        List<String> result=null;
        BiFunction<List<Question>, Integer, List<String>> getTopFakedAnswers =
                (q, n) -> q.stream()
                        .flatMap(question -> question.getAnswers().stream())
                        .filter(answer -> isFakeAnswer(answer))
                        .limit(n)
                        .map(answer -> answer.getText())
                        .collect(Collectors.toList());
        result = getTopFakedAnswers.apply(questions,topCount);
        return result;
    }
    private boolean isFakeAnswer(Answer answer)
    {
        Function<Answer,Boolean> isFakeAnswer=a -> answer.getVotes().stream()
                .filter(vote -> vote!=null)
                .collect(Collectors.groupingBy(Vote::isLike,Collectors.counting()))
                .entrySet()
                .stream()
                .max((o1, o2) -> o2.getValue().intValue()-o1.getValue().intValue())
                .map(booleanLongEntry -> (Boolean) booleanLongEntry.getKey() )
                .orElse(true);

        return isFakeAnswer.apply(answer);
    }
}


//10- top k viewed question					yasser
//12- trending k tag						yasser
//13- top Fake answer						yasser