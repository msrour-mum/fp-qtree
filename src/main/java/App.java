import model.Question;
import model.QuestionTag;
import model.Tag;
import model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {


    public static void main(String[] args)
    {
        User user1 = new User(1, "Admin");
        User user2 = new User(2, "Regular");
        User user3 = new User(3, "Custom");

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

        Date now = new Date();

        Question qust1 = new Question(1, "How can we create pure java project", user1, now);

        List<QuestionTag> qust1Tags = new ArrayList<QuestionTag>();
        QuestionTag qust1Tag1 = new QuestionTag(qust1, tag1);
        QuestionTag qust1Tag2 = new QuestionTag(qust1, tag4);
        qust1Tags.add(qust1Tag1);
        qust1Tags.add(qust1Tag2);
        qust1.setTags(qust1Tags);


    }

}
