package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test2 {

    public static void main(String[]args) throws FileNotFoundException {
        ArrayList<TowerBlock> towerBlocks = new ArrayList<>();

        File file = new File("src/pieces.txt");
        Scanner sc = new Scanner(file);

        // read blocks in file and store to array list
        while(sc.hasNextLine()) {
            String[] line = sc.nextLine().trim().split(", ");
            for (int i = 0; i < line.length; i++) {
                TowerBlock tb = new TowerBlock(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));
                towerBlocks.add(tb);
            }

        }
        sc.close();
        GeneticAlgorithm algo2 = new GeneticAlgorithm(null, towerBlocks);
        long start = System.currentTimeMillis();
        long end = start + 10000;
        while (System.currentTimeMillis() < end) {
            algo2.runGeneticAlgorithm2();
        }
        System.out.println("Complete");
    }
}
