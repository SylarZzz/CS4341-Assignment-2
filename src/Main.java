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

        /*  For testing purposes

        // Valid tower
        TowerBlock door = new TowerBlock("Door", 5, 3, 2);
        TowerBlock wall1 = new TowerBlock("Wall", 5, 5, 1);
        TowerBlock wall2 = new TowerBlock("Wall", 4, 3, 1);
        TowerBlock lookout = new TowerBlock("Lookout", 3, 1, 2);

        ArrayList<TowerBlock> tbs1 = new ArrayList<>();
        tbs1.add(door);
        tbs1.add(wall1);
        tbs1.add(wall2);
        tbs1.add(lookout);

        Tower twr = new Tower(tbs1);
        System.out.println("Is door & lookout valid?: " +twr.isValidDoorAndLook());
        System.out.println("Are walls valid?: " +twr.isValidWalls());
        System.out.println("Is tower valid?: " +twr.isValid());
        System.out.println("Tower score: " + twr.getScore());


        // Invalid Tower. Fails rule 4 and rule 5 but has valid doors, lookouts, and continuous walls.
        TowerBlock door_1 = new TowerBlock("Door", 5, 3, 2);
        TowerBlock wall1_1 = new TowerBlock("Wall", 4, 3, 1);
        TowerBlock wall2_1 = new TowerBlock("Wall", 5, 5, 1);
        TowerBlock wall3_1 = new TowerBlock("Wall", 3, 3, 2);
        TowerBlock lookout_1 = new TowerBlock("Lookout", 2, 2, 3);

        ArrayList<TowerBlock> tbs2 = new ArrayList<>();
        tbs2.add(door_1);
        tbs2.add(wall1_1);
        tbs2.add(wall2_1);
        tbs2.add(wall3_1);
        tbs2.add(lookout_1);

        Tower twr2 = new Tower(tbs2);
        System.out.println("Is second door & lookout valid?: " +twr2.isValidDoorAndLook());
        System.out.println("Are second walls valid?: " +twr2.isValidWalls());
        System.out.println("Is second tower valid?: " +twr2.isValid());
        System.out.println("Second Tower score: " + twr2.getScore());
        */
    }
}
