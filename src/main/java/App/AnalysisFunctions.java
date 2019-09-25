package App;

import FuncInterface.FunctionX4;
import model.*;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;


public class AnalysisFunctions {
    public List<Question> getKViewedQuestions(List<Question> questions, Integer k) {
        List<Question> result = null;
        BiFunction<List<Question>, Integer, List<Question>> getKViewedQuest =
                (q, n) -> q.stream()
                        .sorted((q1, q2) -> q2.getViews().size() - q1.getViews().size())
                        .limit(n)
                        .collect(Collectors.toList());
        result = getKViewedQuest.apply(questions, k);
        return result;
    }

    public List<Tag> getKTrendingTags(List<Question> questions, Date from, Date to, Integer K) {
        List<Tag> result = null;
        FunctionX4<List<Question>, Date, Date, Integer, List<Tag>> getKTrendingTags =
                (q, df, dt, n) -> q.stream()
                        .filter((question) -> question.getDate().compareTo(df) <= 0 && question.getDate().compareTo(dt) >= 0)
                        .flatMap((qt) -> qt.getTags().stream())
                        .collect(Collectors.groupingBy(QuestionTag::getTag))
                        .entrySet()
                        .stream()
                        .map(tagListEntry -> (Tag) tagListEntry.getKey())
                        .limit(n)
                        .collect(Collectors.toList());
        result = getKTrendingTags.apply(questions, from, to, K);
        return result;
    }
    public List<Answer> getKFakeAnswers(List<Question> questions, Integer topCount)
    {
        List<Answer> result=null;
        BiFunction<List<Question>, Integer, List<Answer>> getTopFakedAnswers =
                (q, n) -> q.stream()
                        .flatMap(question -> question.getAnswers().stream())
                        .filter(answer -> isFakeAnswer(answer))
                        .limit(n)
                        .collect(Collectors.toList());
        result = getTopFakedAnswers.apply(questions,topCount);
        return result;
    }
    private boolean isFakeAnswer(Answer answer)
    {
        Function<Answer,Boolean> isFakeAnswer=a -> answer.getVotes().stream()
                .collect(Collectors.groupingBy(Vote::isLike,Collectors.counting()))
                .entrySet()
                .stream()
                .max((o1, o2) -> o2.getValue().intValue()-o1.getValue().intValue())
                .map(booleanLongEntry -> (Boolean) booleanLongEntry.getKey() )
                .orElse(false);

        return isFakeAnswer.apply(answer);
    }
}


//10- top k viewed question					yasser
//12- trending k tag						yasser
//13- top Fake answer						yasser