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

public class Main {

    public static void main(String[] args) throws IOException {

        FileTestDataReader reader=new FileTestDataReader();
        reader.Read();
    }

}
