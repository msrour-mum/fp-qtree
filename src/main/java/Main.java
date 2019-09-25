import App.*;
import model.*;
import model.Question;
import model.QuestionTag;
import model.Tag;
import model.User;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static Qtree qtree=null;
    private  static FileTestDataReader reader=null;
    public static void main(String[] args) throws IOException, ParseException {
        FileTestDataReader reader=new FileTestDataReader();
        qtree= reader.Read();

        consoleApp();
        //List<User> list=    UserStatistics.topUserComments.apply(qtree,3);
    }

    public static void consoleApp() throws IOException, ParseException {
        System.out.println("                        Welcome to Qtree-fun Project ");
        System.out.println();
        System.out.println(" please choose the number ");
        System.out.println();
        System.out.print("1-top active user\n2-top Reputated user \n3-most answering user \n4-top user has question "+
                "\n5-calculate reputation by answers \n6-calculate reputation by ques&answers \n7-top commented Answer "+
                "\n8-top commented questions \n9- num of Question by tag &date \n10- Top k viewed question \n11- Trending k tag "+
                "\n12- Top Fake answer \n14- top Answered Questions \n15- top rated answers \n16- top user comments ");
        System.out.println('\n');
        System.out.println("choice : ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        selectQuetion(num);
    }
    public static void selectQuetion(int quetionNum) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        switch (quetionNum){
            case 1 :{
                System.out.println("Function: top active K users");
                System.out.println("please enter number of needed active user");
                int k = sc.nextInt();

                UserStatistics st=new UserStatistics();
                System.out.println(st.topActiveKUser.apply(qtree,k));
               // System.out.println(st.);
                break;
            }
            case 2: {
                System.out.println("Function: top Reputated user");
                UserStatistics st=new UserStatistics();
                System.out.println(st.topReputatedUser.apply(qtree));
                break;
            }
            case 3: {
                System.out.println("Function: most answering user");
                UserStatistics st=new UserStatistics();
                System.out.println(st.mostAnsweringUser.apply(qtree));
                break;
            }
            case 4: {
                System.out.println("Enter rank number");
                int k= sc.nextInt();
                UserStatistics st=new UserStatistics();
                List<User> users= st.getTopKUsersHaveQuestions.apply(qtree,k);
                System.out.println("Function Result");
                System.out.println("User List: "+ users);
                break;
            }
            case 5: {
                System.out.println("Enter rank number");
                int k= sc.nextInt();
                UserStatistics st=new UserStatistics();
                List<User> users= st.getTopKUsersReputationBasedOnAnswersVotes.apply(qtree,k);
                System.out.println("Function Result");
                System.out.println("User List: "+ users);
                break;
            }
            case 6: {
                System.out.println("Enter rank number");
                int k= sc.nextInt();
                UserStatistics st=new UserStatistics();
                List<User> users= st.getTopKUsersReputationBasedOnQuestionAndAnswers.apply(qtree,k);
                System.out.println("Function Result");
                System.out.println("User List: "+ users);
                break;
            }
            case 7: {
                System.out.println("method 7");
                System.out.println("Function : Top Answer has Comments");
                TopComments Tc=new TopComments();
                Optional<Answer> topComAnswer=Tc.topCommentedAnswer.apply(qtree.getQuestions());
                System.out.println("Function Result");
                System.out.println("Top Commented Answer: "+topComAnswer.get().getText());
                break;
            }
            case 8: {
                System.out.println("method 8");
                System.out.println("Function : Top Question has Comments");
                TopComments Tc=new TopComments();
                Optional<Question> topComQuestion=Tc.TopCommentedQuestion.apply(qtree.getQuestions());
                System.out.println("Function Result");
                System.out.println("Top Commented Question: "+topComQuestion.get().getText());
                break;
            }
            case 9: {
                System.out.println("method 9");
                System.out.println("Function : Num of Question by tag & date");
                System.out.println("Enter tag ");
                String t= sc.nextLine();
                Tag tag=reader.GetTag(t);
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                System.out.println("Enter date and time in the format yyyy-MM-dd");
                System.out.println("For example, it is now " + format.format(new Date()));
                Date date = null;
                while (date == null) {
                    String line = sc.nextLine();
                    try {
                        date = format.parse(line);
                    } catch (ParseException e) {
                        System.out.println("Sorry, that's not valid. Please try again.");
                    }
                }

                TopComments Tc=new TopComments();
                Long count = Tc.getQuestionCountByTagAndDate.apply(qtree.getQuestions(),tag,date);
                System.out.println("Function Result");
                System.out.println("Count of Question: "+ count);
                break;
            }
            case 10: {
                System.out.println("method 10");
                System.out.println("Function : Top k viewed question");
                System.out.println("Enter K value");
                int k= sc.nextInt();

                List<Question> input= qtree.getQuestions();
                AnalysisFunctions analysisFunctions=new AnalysisFunctions();
                List<String> result= analysisFunctions.getKViewedQuestions(input,k);
                System.out.println("Function Result");
                //System.out.println("Top viewed questions: "+result);
                for (String q:result
                     ) {System.out.println("Question: "+q);
                }
                break;
            }
            case 11: {
                System.out.println("method 11");
                System.out.println("Function : Trending k tag");
                System.out.println("Enter K value");
                int k= sc.nextInt();
                System.out.println("Enter Date From (dd-MM-yyyy");
                String dfs=sc.next();
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date dateFrom=df.parse(dfs);
                System.out.println("Enter Date From (dd-MM-yyyy");
                String dft=sc.next();
                Date dateTo=df.parse(dft);


                List<Question> input= qtree.getQuestions();
                AnalysisFunctions analysisFunctions=new AnalysisFunctions();
                List<String> result= analysisFunctions.getKTrendingTags(input,dateFrom,dateTo,k);
                System.out.println("Function Result");
                for (String t:result
                ) {System.out.println("Tag: "+t);
                }
                break;
            }
            case 12: {
                System.out.println("method 12");
                System.out.println("Function : Top Fake answer");
                System.out.println("Enter K value");
                int k= sc.nextInt();

                List<Question> input= qtree.getQuestions();
                AnalysisFunctions analysisFunctions=new AnalysisFunctions();
                List<String> result= analysisFunctions.getKFakeAnswers(input,k);
                System.out.println("Function Result");
                //System.out.println("Top viewed questions: "+result);
                for (String a:result
                ) {System.out.println("Answer: "+a);
                }
                break;
            }
            case 13: {
                System.out.println("method 13");
                break;
            }
            case 14: {
                System.out.println("Function : Top Answered Questions");
                System.out.println("Enter rank number");
                int k= sc.nextInt();
                UserStatistics st=new UserStatistics();
                List<Question> answerList= st.topAnsweredQuestions.apply(qtree,k);
                System.out.println("Function Result");
                System.out.println("Question List: "+answerList.stream().collect(Collectors.toList()));
                break;
            }
            case 15: {

                System.out.println("Function : Top rated answers");
                System.out.println("Enter rank number");
                int k= sc.nextInt();
                UserStatistics st=new UserStatistics();
                List<Answer> answerList= st.topRatedAnswers.apply(qtree,k);
                System.out.println("Function Result");
                System.out.println("Answers List: "+answerList.stream().collect(Collectors.toList()));
                break;
            }
            case 16: {

                System.out.println("Function : Top user comments");
                System.out.println("Enter rank number");
                int k= sc.nextInt();
                UserStatistics st=new UserStatistics();
                List<User> users= st.topUserComments.apply(qtree,k);
                System.out.println("Function Result");
                System.out.println("User List: "+users.stream().collect(Collectors.toList()));
                break;
            }
            default: {
                System.out.println("please enter number between 1 : 16");
                consoleApp();
            }

        }
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("select another function");


        int num = sc.nextInt();
        selectQuetion(num);
    }


}
