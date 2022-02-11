import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Please enter file name: ");
        Puzzle1 puzzle1 = new Puzzle1();
        puzzle1.puzzle(args[0]);


    }
}
