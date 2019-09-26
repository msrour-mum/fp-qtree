package App;

import model.*;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserStatistics {

    private Function<Answer,Long> getAnswerDownVotesCount = (a)->
            a.getVotes().stream().filter(v-> !v.isLike()).count();

    private Function<Answer,Long> getAnswerUpVotesCount = (a)->
            a.getVotes().stream().filter(v-> v.isLike()).count();

    /*
    isAnswer has good reputation if it's marked as answered or number of up votes more than number of down votes by 1.6
     */
    private Predicate<Answer> isAnswerHasGoodReputation = (a)->
            a.isVerified() || (getAnswerUpVotesCount.apply(a)/ (getAnswerDownVotesCount.apply(a) == 0 ? 1 : getAnswerDownVotesCount.apply(a))) > 5;

    private Predicate<Question> isQuestionHasGoodReputation = (q)->
            q.getAnswers().size() >= 3 || q.getAnswers().stream().anyMatch(Answer::isVerified);
    /*
     Get top N users who has most questions that have at least one voted answer.
     */

    public BiFunction<Qtree, Integer, List<User>> getTopKUsersHaveQuestions =
            (app, k) ->
                    app.getQuestions().stream()
                            .filter(q -> q.getAnswers().stream().anyMatch(a -> a.getVotes().size() > 0))
                            .collect(Collectors.groupingBy(Question::getUser, Collectors.counting()))
                            .entrySet().stream().sorted((g1, g2) -> g2.getValue().intValue() - g1.getValue().intValue()).limit(k)
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toList());

    /*
    get all user's answers that has good reputation,  good reputation calculation depends on number of up votes and number
    down votes.
       ex: good reputation = number of up votes / number of down votes > 1.6
    */
    private BiFunction<Qtree,User,List<Answer>> getUserAnswersWithGoodReputation =
            (app, user)->
                    app.getQuestions().stream()
                            .filter(q -> q.getUser().getId() == user.getId())
                            .flatMap(q-> q.getAnswers().stream())
                            .filter(a -> a.getUser().getId() == user.getId())
                            .filter(a-> isAnswerHasGoodReputation.test(a))
                            .collect(Collectors.toList());

    /*
     User gains 10 points for each answer has good reputation
     */
    public BiFunction<Qtree,User,Long> getUserReputationByAnswers =
            (app,user) -> getUserAnswersWithGoodReputation.apply(app, user)
                    .stream()
                    .filter(a-> isAnswerHasGoodReputation.test(a)).count() * 10;

    public BiFunction<Qtree,User,Long> getUserReputationBasedOnQuestionAndAnswers =
            (app, u)-> app.getQuestions().stream()
                    .filter(q -> q.getUser().getId()==u.getId())
                    .filter(q -> isQuestionHasGoodReputation.test(q))
                    .count()*10 ;



    /*
    Get top N users reputation who has the most answers that has the up votes more than down votes by 1.6 times
    */
    public BiFunction<Qtree,Integer,List<User>> getTopKUsersReputationBasedOnAnswersVotes =
            (app, k)-> app.getUsers().stream()
                    .sorted((u1, u2)-> getUserReputationByAnswers.apply(app, u2).intValue() - getUserReputationByAnswers.apply(app, u1).intValue())
                    .limit(k)
                    .collect(Collectors.toList());

    /*
   Get top N users reputation who has the question that have at least 3 answers or one verified answer
   */
    public BiFunction<Qtree,Integer,List<User>> getTopKUsersReputationBasedOnQuestionAndAnswers =
            (app, k)-> app.getQuestions().stream()
                    .filter(q -> isQuestionHasGoodReputation.test(q))
                    .collect(Collectors.groupingBy(Question::getUser, Collectors.counting()))
                    .entrySet().stream().sorted((g1, g2) -> g2.getValue().intValue() - g1.getValue().intValue()).limit(k)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

    private BiFunction<Qtree,User,List<Answer>> getUserAnswers =
            (app, user) ->
                    app.getQuestions().stream()
                            .flatMap(q-> q.getAnswers().stream())
                            .filter(a-> a.getUser().getId() == user.getId()).collect(Collectors.toList());

    private BiFunction<Qtree,User,List<Question>> getUserQuestions =
            (app, user)->
                    app.getQuestions().stream().filter(q-> q.getUser().getId() == user.getId()).collect(Collectors.toList());

    private BiFunction<Qtree,User,Long> totalReputation = (q,u)-> getUserReputationByAnswers.apply(q,u)+
            getUserReputationBasedOnQuestionAndAnswers.apply(q,u) ;

    public Function<Qtree, String> mostAnsweringUser = (q) -> {
        return q.getQuestions().stream()
                .flatMap(question -> question.getAnswers().stream())
                .collect(Collectors.groupingBy(Answer::getUser)).entrySet().stream()
                .max((map1, map2) -> map2.getValue().size() - map1.getValue().size())

                .map(map -> map.getKey()).get().getName();
    };
    // Answer::getUser = answer -> answer.getUser()

    public Function<Qtree, String> topReputatedUser = (q) ->{
        return  q.getUsers().stream()
                .max((user1, user2) -> (int) (getUserReputationByAnswers.apply(q,user2) - getUserReputationByAnswers.apply(q,user1)))
                .get().getName();
    };

    public Function<Qtree,String> topActiveUser = q -> q.getUsers().stream().
            max((user1,user2)->(int) (getUserReputationByAnswers.apply(q,user2)+getUserReputationBasedOnQuestionAndAnswers.apply(q,user2)
                    -(getUserReputationByAnswers.apply(q,user1)-getUserReputationBasedOnQuestionAndAnswers.apply(q,user1)))).
            get().getName() ;

    //top user comments
    public BiFunction<Qtree,Integer, List<User>> topUserComments = (q,k)->q.getQuestions().stream()
            .flatMap(a->a.getAnswers().stream())
            .flatMap(c->c.getComments().stream())
            .collect(Collectors.groupingBy(Comment::getUser,Collectors.counting()))
            .entrySet()
            .stream()
            .sorted((a1,a2)->a2.getValue().intValue()-a1.getValue().intValue())
            .map(f->(User)f.getKey())
            .limit(k)
            .collect(Collectors.toList());

    //Zain :top rated answers
    public BiFunction<Qtree,Integer,List<Answer>> topRatedAnswers = (q,k)->q.getQuestions().stream()
            .flatMap(a->a.getAnswers().stream())
            .sorted((a1,a2)->a2.getVotes().size()-a1.getVotes().size())
            .limit(k)
            .collect(Collectors.toList());

  /*  BiFunction<Qtree,Integer,List<Answer>> topRatedAnswers = (q,k)->q.getQuestions().stream()
            .flatMap(a->a.getAnswers().stream())
            .flatMap(a->a.getVotes().stream())
            .collect(Collectors.groupingBy(Vote::isLike),Collectors.summingInt(Vote::isLike))
     */



    //Zain :Top Active Users [Date]
    private Function<Qtree,Map<User,Long>> subTopUserQuestion=q->q.getQuestions().stream()
            .collect(Collectors.groupingBy(Question::getUser,Collectors.counting()))
            .entrySet()
            .stream()
            .sorted((a1,a2)->a2.getValue().intValue()-a1.getValue().intValue())
            .collect(Collectors.toMap(x->x.getKey(),x->x.getValue()));

    private Function<Qtree,Map<User,Long>> subTopUserAnswers=q->q.getQuestions().stream()
            .flatMap(a->a.getAnswers().stream())
            .collect(Collectors.groupingBy(Answer::getUser,Collectors.counting()))
            .entrySet()
            .stream()
            .sorted((a1,a2)->a2.getValue().intValue()-a1.getValue().intValue())
            .collect(Collectors.toMap(x->x.getKey(),x->x.getValue()));

    private Function<Qtree,Map<User,Long>> subTopUserComments=q->q.getQuestions().stream()
            .flatMap(a->a.getAnswers().stream())
            .flatMap(a->a.getComments().stream())
            .collect(Collectors.groupingBy(Comment::getUser,Collectors.counting()))
            .entrySet()
            .stream()
            .sorted((a1,a2)->a2.getValue().intValue()-a1.getValue().intValue())
            .collect(Collectors.toMap(x->x.getKey(),x->x.getValue()));
}
