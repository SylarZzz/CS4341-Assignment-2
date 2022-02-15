package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[]args) throws FileNotFoundException {
            ArrayList<Float> numberList = new ArrayList<>();

            File file = new File("numbers.txt");
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                float num = Float.parseFloat(line);
                numberList.add(num);

            }

            while(sc.hasNextLine()) {
                float num = sc.nextFloat();
                numberList.add(num);
            }
            sc.close();
            GeneticAlgorithm algo = new GeneticAlgorithm(numberList, null);

            long start = System.currentTimeMillis();
            long end = start + 10000;
            float bestFitnessScore = 0;
            AllBins bestBin = new AllBins();
            int generations = 0;
            int bestSolutionGen = 0;
            while (System.currentTimeMillis() < end) {
                if(algo.popPuz1.get(algo.popPuz1.getFittestIndex()).getScore() > bestFitnessScore) {
                    bestFitnessScore = algo.popPuz1.get(algo.popPuz1.getFittestIndex()).getScore();
                    bestBin = algo.popPuz1.get(algo.popPuz1.getFittestIndex());
                    bestSolutionGen = generations;
                }
                algo.runGeneticAlgorithm();
                generations++;
            }
            System.out.println("The best solution for this puzzle was:");
            System.out.println("Bin 1: " + bestBin.bin1.inBin);
            System.out.println("Bin 2: " + bestBin.bin2.inBin);
            System.out.println("Bin 3: " + bestBin.bin3.inBin);
            System.out.println("Bin 4: " + bestBin.bin4.inBin);
            System.out.println("With a score of: " + bestFitnessScore);
            System.out.println("It took " + bestSolutionGen + " generation to find this solution");
            System.out.println("The program ran for a total of " + generations + " generations");
            System.out.println("Complete");
        }
}
