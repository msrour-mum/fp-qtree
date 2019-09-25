import App.FileTestDataReader;
import App.Qtree;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)throws IOException
    {
        FileTestDataReader reader=new FileTestDataReader();
        Qtree qtree = reader.Read();

        consoleApp();
    }

    public static void consoleApp(){
        System.out.println("                        Welcome to Qtree-fun Project ");
        System.out.println();
        System.out.println(" please choose the number ");
        System.out.println();
        System.out.print("1-top active user\n2-top Reputed user \n3-most answering user \n4-top user has question "+
                "\n5-calculate reputation by answers \n6-calculate reputation by ques&answers \n7-top commented Answer "+
                "\n8-top commented questions \n9- num of Question by tag &date \n10- top k viewed question \n11- tag analysis "+
                "\n12- trending k tag \n13- top Fake answer \n14- top active user \n15- top rated answers \n16- top user comments ");
        System.out.println('\n');
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        selectQuetion(num);
    }
    public static void selectQuetion(int quetionNum){
        switch (quetionNum){
            case 1 :{
                System.out.println("method 1");
                break;
            }
            case 2: {
                System.out.println("method 2");
                break;
            }
            case 3: {
                System.out.println("method 3");
                break;
            }
            case 4: {
                System.out.println("method 4");
                break;
            }
            case 5: {
                System.out.println("method 5");
                break;
            }
            case 6: {
                System.out.println("method 6");
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
                break;
            }
            case 16: {
                System.out.println("method 16");
                break;
            }
            default: {
                System.out.println("please enter number between 1 : 16");
                consoleApp();
            }

        }
        System.out.println("select another function");

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        selectQuetion(num);
    }


}
