package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // For future args input. Now directly use file path
        // File file = new File(args[1]);
        File file = new File("src/pieces.txt");
        Scanner sc = new Scanner(file);
        ArrayList<TowerBlock> towerBlocks = new ArrayList<>();

        // read blocks in file and store to array list
        while(sc.hasNextLine()) {
            String[] line = sc.nextLine().trim().split(", ");
            for (int i = 0; i < line.length; i++) {
                TowerBlock tb = new TowerBlock(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));
                towerBlocks.add(tb);
            }

        }
        sc.close();


    }
}
