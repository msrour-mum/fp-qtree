package service.statistics;

import model.User;

import java.util.function.Function;

public class CalculateUserReputation {
    Function<User,Integer> getUserReputationByAnswers = user -> (int)(Math.random()%10) *10;
    Function<User,Integer> getUserReputationByQuestions = user -> (int)(Math.random()%10) *10 ;
}
