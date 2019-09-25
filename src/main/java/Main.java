import App.FileTestDataReader;
import App.Qtree;
import App.UserStatistics;
import model.*;
import model.Question;
import model.QuestionTag;
import model.Tag;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static Qtree qtree=null;
    public static void main(String[] args)throws IOException
    {
        FileTestDataReader reader=new FileTestDataReader();
        qtree= reader.Read();

        consoleApp();
        //List<User> list=    UserStatistics.topUserComments.apply(qtree,3);
    }

    public static void consoleApp(){
        System.out.println("                        Welcome to Qtree-fun Project ");
        System.out.println();
        System.out.println(" please choose the number ");
        System.out.println();
        System.out.print("1-top active user\n2-top Reputated user \n3-most answering user \n4-top user has question "+
                "\n5-calculate reputation by answers \n6-calculate reputation by ques&answers \n7-top commented Answer "+
                "\n8-top commented questions \n9- num of Question by tag &date \n10- top k viewed question \n11- tag analysis "+
                "\n12- trending k tag \n13- top Fake answer \n14- top active user \n15- top rated answers \n16- top user comments ");
        System.out.println('\n');
        System.out.println("choice : ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        selectQuetion(num);
    }
    public static void selectQuetion(int quetionNum){
        Scanner sc = new Scanner(System.in);
        switch (quetionNum){
            case 1 :{
                System.out.println("Function: top active user");
                UserStatistics st=new UserStatistics();
                System.out.println(st.topActiveUser.apply(qtree));
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
                break;
            }
            case 8: {
                System.out.println("method 8");
                break;
            }
            case 9: {
                System.out.println("method 9");
                break;
            }
            case 10: {
                System.out.println("method 10");
                break;
            }
            case 11: {
                System.out.println("method 11");
                break;
            }
            case 12: {
                System.out.println("method 12");
                break;
            }
            case 13: {
                System.out.println("method 13");
                break;
            }
            case 14: {
                System.out.println("method 14");
                break;
            }
            case 15: {
                System.out.println("method 15");
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
                System.out.println("method 16");
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
