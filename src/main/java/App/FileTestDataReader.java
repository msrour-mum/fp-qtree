package App;
import model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTestDataReader
{
    public FileTestDataReader()
    {
        Init();
    }

    private void Init()
    {
        User user1 = new User(1, "Admin");
        User user2 = new User(2, "Regular");
        User user3 = new User(3, "Custom");
        users =new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        users.add(new User(4,"Custom"));
        users.add(new User(5,"Custom"));
        users.add(new User(6,"Custom"));
        users.add(new User(7,"Custom"));
        users.add(new User(8,"Custom"));
        users.add(new User(9,"Custom"));
        users.add(new User(10,"Custom"));
        users.add(new User(11,"Custom"));
        users.add(new User(12,"Custom"));
        users.add(new User(13,"Custom"));
        users.add(new User(14,"Custom"));
        users.add(new User(15,"Custom"));
        users.add(new User(16,"Custom"));
        users.add(new User(17,"Custom"));



        Tag tag1 = new Tag(1, "Java");
        Tag tag2 = new Tag(2, "NodeJs");
        Tag tag3 = new Tag(3, "Hibernate");
        Tag tag4 = new Tag(4, "Spring");
        Tag tag5 = new Tag(5, "C#");
        Tag tag6 = new Tag(6, "MySql");
        Tag tag7 = new Tag(7, "Git");
        Tag tag8 = new Tag(8, "Functional Programming");
        Tag tag9 = new Tag(9, "Angular");
        Tag tag10 = new Tag(10, "Javascript");
        tags =new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);
        tags.add(tag5);
        tags.add(tag6);
        tags.add(tag7);
        tags.add(tag8);
        tags.add(tag9);
        tags.add(tag10);
    }
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    private List<User> users;
    private  List<Tag> tags;
    private List<Question> questions = null;


    public  Qtree Read() throws IOException {
        BufferedReader reader;
        Question currentQ=null;
        Answer currentAns=null;
        Comment currentComm=null;
        int id = 0;
        String fileName="";
        questions = new ArrayList<>();
        String cwd = System.getProperty("user.dir");
        List<Path> fileNames =  Files.walk(Paths.get(cwd+ "/src/main/java/TestData"))
                .filter(x->String.valueOf(x).endsWith(".txt"))
                .collect(Collectors.toList());

        for(Path file : fileNames) {

            try {
                fileName=String.valueOf(file);
                System.out.println("===============================================================================");
                System.out.println("Read File : "+fileName);
                System.out.println("===============================================================================");


                reader = new BufferedReader(new FileReader(fileName));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    id++;
                    // read next line
                    if (line.startsWith("Q:")) {
                        String[] arr = line.split("\\|");
                        int userId = Integer.parseInt(arr[0].split("\\:")[1]);
                        User u = GetUser(userId);
                        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(arr[1]);
                        int noView = Integer.parseInt(arr[2]);
                        String txt = arr[3];

                        currentQ = new Question(id, txt, u, date);

                        List<QuestionViews> views =new ArrayList<>();
                        for (int i=0;i<noView;i++)
                            views.add( new  QuestionViews(currentQ,date));
                        currentQ.setViews(views);

                        questions.add(currentQ);
                    }
                    if (line.startsWith("A:")) {
                        String[] arr = line.split("\\|");
                        int userId = Integer.parseInt(arr[0].split("\\:")[1]);
                        String txt = arr[3];
                        User u = GetUser(userId);
                        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(arr[1]);
                        boolean isVerified = Boolean.parseBoolean(arr[2]);
                        currentAns = new Answer(id, txt, u, date, isVerified, null, null);
                        currentQ.getAnswers().add(currentAns);
                    }
                    if (line.startsWith("C:")) {
                        String[] arr = line.split("\\|");
                        int userId = Integer.parseInt(arr[0].split("\\:")[1]);
                        String txt = arr[2];
                        User u = GetUser(userId);
                        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(arr[1]);
                        currentComm = new Comment(id, txt, u, date);
                        currentAns.getComments().add(currentComm);
                    }

                    if (line.startsWith("T:")) {
                        String[] arr = line.replace("T:", "").split("\\,");
                        for (String x : arr) {
                            currentQ.getTags().add(new QuestionTag(currentQ, GetTag(Integer.parseInt(x))));
                        }
                    }

                    if (line.startsWith("V:")) {
                        String[] arr = line.replace("V:", "").split("\\|");
                        for (String x : arr) {
                            String[] vArr = x.split("\\,");
                            User u = GetUser(Integer.parseInt(vArr[0]));
                            int v = Integer.parseInt(vArr[1]);
                            boolean isLike=false;
                            if (v==1) isLike=true;
                            Date date=new Date(2019,1,1);
                            currentAns.getVotes().add(new Vote(u,date , isLike));
                        }
                    }

                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException | ParseException e) {

                e.printStackTrace();
                return null;
            }

            System.out.println(questions);
        }

        Qtree qtree =new Qtree(questions,users,tags);

        return  qtree;
    }

    private  User GetUser(int id)
    {
        List<User>  list= users.stream().filter(u->u.getId()==id).collect(Collectors.toList());
        return list.get(0);
    }
    private  Tag GetTag(int id)
    {
        List<Tag>  list= tags.stream().filter(u->u.getId()==id).collect(Collectors.toList());
        return list.get(0);
    }


    private  Question GetQuestion(int id)
    {
        List<Question>  list= questions.stream().filter(u->u.getId()==id).collect(Collectors.toList());
        return list.get(0);
    }
}
