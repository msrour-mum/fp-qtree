package App;

import FuncInterface.FunctionX3;
import FuncInterface.FunctionX4;
import model.Answer;
import model.Question;
import model.Tag;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopComments {

    private   Function<Question,Long> countComments=(q)->q.getAnswers().stream()
            .map(ans->ans.getComments()).count();

 public   Function<List<Question>, Optional<Question>> TopCommentedQuestion=
            (q)->q.stream().max((q1,q2)->countComments.apply(q2).intValue() - countComments.apply(q1).intValue() );


 /*public Function<Qtree,Question> getQuestiondjdd = (app)->
         app.getQuestions().stream().flatMap(q-> q.getAnswers().stream())
                 .flatMap()
         .collect(Collectors.groupingBy(Question:*/


              //      .max((q1,q2)->q2.getComments().size()-q1.getComments().size());

  public  Function<List<Question>,Optional<Answer>> topCommentedAnswer=
            (q)->q.stream()
            .flatMap(que->que.getAnswers().stream())
                    .max((ans1,ans2)->ans2.getComments().size()-ans1.getComments().size());

 public FunctionX4<List<Question>, String, Date,Date,Long> getQuestionCountByTagAndDate=
            (q,t,dataFrom,dataTo)->q.stream()
                    .filter(x->x.getDate().compareTo(dataFrom)>0 && x.getDate().compareTo(dataTo)<0)
                    .flatMap(s->s.getTags().stream())
                    .map(a->a.getTag())
                    .filter(d->d.getName().toLowerCase().equals(t.toLowerCase()))
                    .count();

}
