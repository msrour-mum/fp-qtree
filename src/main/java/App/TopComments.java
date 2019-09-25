package App;

import FuncInterface.FunctionX3;
import model.Answer;
import model.Question;
import model.Tag;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TopComments {

    private   Function<Question,Long> countComments=(q)->q.getAnswers().stream()
            .flatMap(ans->ans.getComments().stream()).count();

 public   Function<List<Question>, Optional<Question>> TopCommentedQuestion=
            (q)->q.stream().max((q1,q2)->countComments.apply(q2).intValue() - countComments.apply(q1).intValue() );


              //      .max((q1,q2)->q2.getComments().size()-q1.getComments().size());

  public  Function<List<Question>,Optional<Answer>> topCommentedAnswer=
            (q)->q.stream()
            .flatMap(que->que.getAnswers().stream())
                    .max((ans1,ans2)->ans2.getComments().size()-ans1.getComments().size());

 public FunctionX3<List<Question>, Tag, Date,Long> getQuestionCountByTagAndDate=
            (q,t,d)->q.stream()
                    .filter(qes->qes.getTags().contains(t) && qes.getDate()==d)
                    .count();
}
