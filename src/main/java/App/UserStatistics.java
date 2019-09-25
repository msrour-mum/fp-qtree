package App;

import model.Answer;
import model.Question;
import model.User;
import model.Vote;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserStatistics {

    public static Function<Answer,Long> getAnswerDownVotesCount = (a)->
            a.getVotes().stream().filter(v-> !v.isLike()).count();

    public static Function<Answer,Long> getAnswerUpVotesCount = (a)->
            a.getVotes().stream().filter(v-> v.isLike()).count();

    /*
    isAnswer has good reputation if it's marked as answered or number of up votes more than number of down votes by 1.6
     */
    public static Predicate<Answer> isAnswerHasGoodReputation = (a)->
            a.isVerified() || (getAnswerUpVotesCount.apply(a)/ getAnswerUpVotesCount.apply(a) == 0 ? 1 : getAnswerDownVotesCount.apply(a)) > 1.6;

    /*
     Get top N users who has most questions that have at least one voted answer.
     */

    public static BiFunction<List<Question>, Integer, List<User>> getTopKUsersHaveQuestions =
            (qt, k) ->
                     qt.stream()
                    .filter(q -> q.getAnswers().stream().filter(a -> a.getVotes().size() > 0).count() > 0)
                    .collect(Collectors.groupingBy(Question::getUser, Collectors.counting()))
                    .entrySet().stream().sorted((g1, g2) -> g2.getValue().intValue() - g1.getValue().intValue()).limit(k)
                    .map(g -> g.getKey())
                    .collect(Collectors.toList());

    /*
    get all user's answers that has good reputation,  good reputation calculation depends on number of up votes and number
    down votes.
       ex: good reputation = number of up votes / number of down votes > 1.6
    */
    public static BiFunction<Qtree,User,List<Answer>> getUserAnswersWithGoodReputation =
            (app, user)->
                    app.getQuestions().stream().flatMap(q-> q.getAnswers().stream())
                            .filter(a-> isAnswerHasGoodReputation.test(a))
                            .collect(Collectors.toList());

    /*
     User gains 10 points for each answer has good reputation
     */
    public static BiFunction<Qtree,User,Long> getUserReputationByAnswers =
            (app,user) -> getUserAnswersWithGoodReputation.apply(app, user)
                    .stream()
                    .filter(a-> isAnswerHasGoodReputation.test(a)).count() * 10;

    /*
    Get top N users reputation who has the most answers that has the up votes more than down votes by 1.6 times
    */
    public static BiFunction<Qtree,Integer,List<User>> getTopKUsersReputationBasedOnAnswersVotes =
            (app, k)-> app.getUsers().stream()
                        .sorted((u1, u2)-> getUserReputationByAnswers.apply(app, u2).intValue() - getUserReputationByAnswers.apply(app, u1).intValue())
                        .limit(k)
                        .collect(Collectors.toList());

    /*
   Get top N users reputation who has the question that have at least 3 answers or one verified answer
   */
    public static BiFunction<Qtree,Integer,List<User>> getTopKUsersReputationBasedOnQuestionAndAnswers =
            (app, k)-> app.getQuestions().stream()
                    .filter(q -> q.getAnswers().size() >= 3 || q.getAnswers().stream().anyMatch(a-> a.isVerified()))
                    .collect(Collectors.groupingBy(Question::getUser, Collectors.counting()))
                    .entrySet().stream().sorted((g1, g2) -> g2.getValue().intValue() - g1.getValue().intValue()).limit(k)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

    public static BiFunction<Qtree,User,List<Answer>> getUserAnswers =
        (app, user) ->
      app.getQuestions().stream()
                .flatMap(q-> q.getAnswers().stream())
                .filter(a-> a.getUser().getId() == user.getId()).collect(Collectors.toList());

    public static BiFunction<Qtree,User,List<Question>> getUserQuestions =
         (app, user)->
         app.getQuestions().stream().filter(q-> q.getUser().getId() == user.getId()).collect(Collectors.toList());

}
