package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GeneticAlgorithm {

    //These are for the scenario that the file given is for puzzle 1
    PopulationPuzzle1 popPuz1 = new PopulationPuzzle1();
    ArrayList<Float> allNums;

    PopulationPuzzle2 popPuz2 = new PopulationPuzzle2();
    ArrayList<TowerBlock> tbs;

    public GeneticAlgorithm(ArrayList<Float> numbers, ArrayList<TowerBlock> towerblks) {
        if (numbers != null) {
            this.allNums = numbers;
            popPuz1.populate(numbers);
        }
        if (towerblks != null) {
            this.tbs = towerblks;
            popPuz2.populate(towerblks);
        }
    }

    int genCount = 0;

    void geneticAlgo() {

    }

    int whichPuzzle() {
        if(true) {  //some function here that tell whether the file is puzzle 1 or 2
            return 1;
        } else {
            return 2;
        }
    }

    //Removing until no negative scores
    void culling() {
        if(popPuz1.get(popPuz1.getFittestIndex()).getScore() > 0) {
            while (popPuz1.get(popPuz1.lowestFittestIndex()).getScore() < 0) {
                int leastFitIndex = popPuz1.lowestFittestIndex();
                popPuz1.remove(leastFitIndex);
            }
        } else {
            for(int i = 0; i < 6; i++) {
                int leastFitIndex = popPuz1.lowestFittestIndex();
                popPuz1.remove(leastFitIndex);
            }
        }
    }

    //Removing until no negative scores for puzz2
    void culling2() {
        if(popPuz2.get(popPuz2.getFittestIndex()).getScore() > 0) {
            while (popPuz2.get(popPuz2.lowestFittestIndex()).getScore() < 0) {
                int leastFitIndex = popPuz2.lowestFittestIndex();
                popPuz2.remove(leastFitIndex);
            }
        } else {
            for(int i = 0; i < 6; i++) {
                int leastFitIndex = popPuz2.lowestFittestIndex();
                popPuz2.remove(leastFitIndex);
            }
        }
    }

    //Selection
    ArrayList<Integer> selection() {
        ArrayList<Integer> values = new ArrayList<>();
        int index1 = -1;
        int index2 = -1;
        ArrayList<Float> probabilityList = new ArrayList<>();
        float totalProb = 0;
        for(int i = 0; i < popPuz1.population.size(); i++) {
            float prob = popPuz1.getProbability(i);
            totalProb = totalProb + prob;
            probabilityList.add(totalProb);
            //System.out.println(totalProb);
        }
        Random random = new Random();
        float random1 = random.nextFloat();
        float random2 = random.nextFloat();
        for(int i = 0; i < probabilityList.size(); i++) {
            if(i == 0) {
                if(random1 > 0 && random1 < probabilityList.get(i)) {
                    index1 = i;
                }
            } else {
                if(random1 > probabilityList.get(i-1) && random1 < probabilityList.get(i)) {
                    index1 = i;
                }
            }
        }
        //In case both random floats are in the same range
        while(index2 == -1) {
            random2 = random.nextFloat(); //This will reselect random2
            for(int i = 0; i < probabilityList.size(); i++) {
                if(i == 0) {
                    if(random2 > 0 && random2 < probabilityList.get(i) && index1 != i) {
                        index2 = i;
                    }
                } else {
                    if(random2 > probabilityList.get(i-1) && random2 < probabilityList.get(i) && index1 !=i) {
                        index2 = i;
                    }
                }
            }
        }
        values.add(index1);
        values.add(index2);
        return values;
    }


    //Selection for puzz2
    ArrayList<Integer> selection2() {
        ArrayList<Integer> values = new ArrayList<>();
        int index1 = -1;
        int index2 = -1;
        ArrayList<Float> probabilityList = new ArrayList<>();
        float totalProb = 0;
        for(int i = 0; i < popPuz2.population.size(); i++) {
            float prob = popPuz2.getProbability(i);
            totalProb = totalProb + prob;
            probabilityList.add(totalProb);
            //System.out.println(totalProb);
        }
        Random random = new Random();
        float random1 = random.nextFloat();
        float random2 = random.nextFloat();
        for(int i = 0; i < probabilityList.size(); i++) {
            if(i == 0) {
                if(random1 > 0 && random1 < probabilityList.get(i)) {
                    index1 = i;
                }
            } else {
                if(random1 > probabilityList.get(i-1) && random1 < probabilityList.get(i)) {
                    index1 = i;
                }
            }
        }
        //In case both random floats are in the same range
        while(index2 == -1) {
            random2 = random.nextFloat(); //This will reselect random2
            for(int i = 0; i < probabilityList.size(); i++) {
                if(i == 0) {
                    if(random2 > 0 && random2 < probabilityList.get(i) && index1 != i) {
                        index2 = i;
                    }
                } else {
                    if(random2 > probabilityList.get(i-1) && random2 < probabilityList.get(i) && index1 !=i) {
                        index2 = i;
                    }
                }
            }
        }
        values.add(index1);
        values.add(index2);
        return values;
    }

    //Doing crossover between the two selected Individuals
    void crossOver(AllBins fittestPuz1, AllBins secFittestPuz1, PopulationPuzzle1 pop) {
        AllBins child1 = new AllBins();
        AllBins child2 = new AllBins();
        for(int i = 0; i < 10; i++) {
            //This is the crossover point, which is halfway through
            if(i < 5) {
                //Start of child 1 (comes from first parent)
                float bin1ValC1 = fittestPuz1.bin1.get(i);
                child1.bin1.add(bin1ValC1);
                float bin2ValC1 = fittestPuz1.bin2.get(i);
                child1.bin2.add(bin2ValC1);
                float bin3ValC1 = fittestPuz1.bin3.get(i);
                child1.bin3.add(bin3ValC1);
                float bin4ValC1 = fittestPuz1.bin4.get(i);
                child1.bin4.add(bin4ValC1);

                //Start of child 2 (comes from second parent)
                float bin1ValC2 = secFittestPuz1.bin1.get(i);
                child2.bin1.add(bin1ValC2);
                float bin2ValC2 = secFittestPuz1.bin2.get(i);
                child2.bin2.add(bin2ValC2);
                float bin3ValC2 = secFittestPuz1.bin3.get(i);
                child2.bin3.add(bin3ValC2);
                float bin4ValC2 = secFittestPuz1.bin4.get(i);
                child2.bin4.add(bin4ValC2);
            } else {
                //End of child 1 (comes from second parent)
                float bin1ValC1 = secFittestPuz1.bin1.get(i);
                child1.bin1.add(bin1ValC1);
                float bin2ValC1 = secFittestPuz1.bin2.get(i);
                child1.bin2.add(bin2ValC1);
                float bin3ValC1 = secFittestPuz1.bin3.get(i);
                child1.bin3.add(bin3ValC1);
                float bin4ValC1 = secFittestPuz1.bin4.get(i);
                child1.bin4.add(bin4ValC1);

                //End of child 2 (comes from first parent)
                float bin1ValC2 = fittestPuz1.bin1.get(i);
                child2.bin1.add(bin1ValC2);
                float bin2ValC2 = fittestPuz1.bin2.get(i);
                child2.bin2.add(bin2ValC2);
                float bin3ValC2 = fittestPuz1.bin3.get(i);
                child2.bin3.add(bin3ValC2);
                float bin4ValC2 = fittestPuz1.bin4.get(i);
                child2.bin4.add(bin4ValC2);
            }
        }
        pop.add(child1);
        pop.add(child2);
    }

    // TODO: crossover for puzz2
    void crossOver2(Tower fittestPuz2, Tower secFittestPuz2, PopulationPuzzle2 pop) {

    }

    void populateWithChildren(PopulationPuzzle1 newPop, ArrayList<Float> nums) {
        int numberOfChildren = newPop.population.size();
        ArrayList<Float> temp = nums;
        for(int i =0; i < (20 - numberOfChildren); i++) {
            Collections.shuffle(temp);
            AllBins indiv = new AllBins();
            indiv.fillAllBins(temp);
            newPop.add(indiv);
        }
    }

    void runGeneticAlgorithm() {
        while(genCount < 200
        ) {  //Here we have some sort of value, or selecting what our minimum fitness level is
            PopulationPuzzle1 nextGenPop = new PopulationPuzzle1();
            genCount++;

            //Add two fittest parents from previous gen (Elitism)
            AllBins fittest1;
            AllBins fittest2;
            fittest1 = popPuz1.get(popPuz1.getFittestIndex());
            fittest2 = popPuz1.get(popPuz1.getSecondFittestIndex());
            nextGenPop.add(fittest1);
            nextGenPop.add(fittest2);
            //Remove the bottom from the current pop before selection (Culling)
            culling();

            //Selection
            while(nextGenPop.population.size() != 20) {
                ArrayList<Integer> values = selection();
                int selectedIndex1 = values.get(0);
                int selectedIndex2 = values.get(1);
//            System.out.println(selectedIndex1);
//            System.out.println(selectedIndex2);
                AllBins selected1 = popPuz1.get(selectedIndex1);
                AllBins selected2 = popPuz1.get(selectedIndex2);

                //Crossover
                crossOver(selected1, selected2, nextGenPop);
            }

            //setting current pop
            popPuz1 = nextGenPop;

            //Mutation?
            for(int i = 0; i < popPuz1.population.size(); i++) {
                mutation(allNums, popPuz1.get(i));
            }

            //Showing improvement in fitness
            if (genCount == 100 || genCount == 200){
                System.out.println("This is the best fitness score for this generation is " + popPuz1.get(popPuz1.getFittestIndex()).getScore());
                System.out.println("This is the median fitness score for this generation is " + popPuz1.get(popPuz1.getMedianFittestIndex()).getScore());
                System.out.println("This is the worst fitness score for this generatiopn: " + popPuz1.get(popPuz1.lowestFittestIndex()).getScore());
            }
            else if (genCount < 4) {
                System.out.println("First three best fitness score for this generation is " + popPuz1.get(popPuz1.getFittestIndex()).getScore());
            }
        }
    }

    void runGeneticAlgorithm2() {
        while(genCount < 200
        ) {  //Here we have some sort of value, or selecting what our minimum fitness level is
            PopulationPuzzle2 nextGenPop = new PopulationPuzzle2();
            genCount++;

            //Add two fittest parents from previous gen (Elitism)
            Tower fittest1;
            Tower fittest2;
            fittest1 = popPuz2.get(popPuz2.getFittestIndex());
            fittest2 = popPuz2.get(popPuz2.getSecondFittestIndex());
            nextGenPop.add(fittest1);
            nextGenPop.add(fittest2);
            //Remove the bottom from the current pop before selection (Culling)
            culling();

            //Selection
            while(nextGenPop.population.size() != 20) {
                ArrayList<Integer> values = selection();
                int selectedIndex1 = values.get(0);
                int selectedIndex2 = values.get(1);
//            System.out.println(selectedIndex1);
//            System.out.println(selectedIndex2);
                Tower selected1 = popPuz2.get(selectedIndex1);
                Tower selected2 = popPuz2.get(selectedIndex2);

                //Crossover
                crossOver2(selected1, selected2, nextGenPop);
            }

            //setting current pop
            popPuz2 = nextGenPop;

            //Mutation?
            for(int i = 0; i < popPuz2.population.size(); i++) {
                mutation2(tbs, popPuz2.get(i));
            }

            //Showing improvement in fitness
            if (genCount == 100 || genCount == 200){
                System.out.println("This is the best fitness score for this generation is " + popPuz1.get(popPuz1.getFittestIndex()).getScore());
                System.out.println("This is the median fitness score for this generation is " + popPuz1.get(popPuz1.getMedianFittestIndex()).getScore());
                System.out.println("This is the worst fitness score for this generatiopn: " + popPuz1.get(popPuz1.lowestFittestIndex()).getScore());
            }
            else if (genCount < 4) {
                System.out.println("First three best fitness score for this generation is " + popPuz1.get(popPuz1.getFittestIndex()).getScore());
            }
        }
    }


    void mutation(ArrayList<Float> originalArray, AllBins bins) {
        ArrayList<Float> newArray = bins.emptyAllBins();
        ArrayList<Float> finalArray = new ArrayList<>();
        ArrayList<Float> tempOriginalArray = new ArrayList<>();
        ArrayList<Integer> toBeChanged = new ArrayList<>();
        for(int i = 0; i < originalArray.size(); i++) {
            tempOriginalArray.add(originalArray.get(i));
        }
        for(int i = 0; i < newArray.size(); i++) {
            for(int j = 0; j < tempOriginalArray.size(); j++) {
                if(newArray.get(i).equals(tempOriginalArray.get(j))) {
                    tempOriginalArray.remove(j);
                    finalArray.add(newArray.get(i));
                    j--;
                    break;
                } else if(j == tempOriginalArray.size()-1) {
                    toBeChanged.add(i);
                }
            }
        }
        for(int i = 0; i < toBeChanged.size(); i++) {
            finalArray.add(toBeChanged.get(i),tempOriginalArray.get(i));
        }
        bins.fillAllBins(finalArray);
    }

    // TODO: mutation for puzz2
    void mutation2(ArrayList<TowerBlock> originalArray, Tower twr) {
        ArrayList<TowerBlock> newArray = twr.getTowerBlocks();
        ArrayList<TowerBlock> finalArray = new ArrayList<>();
        ArrayList<TowerBlock> tempOriginalArray = new ArrayList<>();
        ArrayList<Integer> toBeChanged = new ArrayList<>();
        for(int i = 0; i < originalArray.size(); i++) {
            tempOriginalArray.add(originalArray.get(i));
        }
        for(int i = 0; i < newArray.size(); i++) {
            for(int j = 0; j < tempOriginalArray.size(); j++) {
                if(newArray.get(i).equals(tempOriginalArray.get(j))) {
                    tempOriginalArray.remove(j);
                    finalArray.add(newArray.get(i));
                    j--;
                    break;
                } else if(j == tempOriginalArray.size()-1) {
                    toBeChanged.add(i);
                }
            }
        }
        for(int i = 0; i < toBeChanged.size(); i++) {
            finalArray.add(toBeChanged.get(i),tempOriginalArray.get(i));
        }
        twr.resetTower(finalArray);
    }

    public static void main(String[]args) throws FileNotFoundException {
        
        // current best arg input: 1 src/pieces.txt 1

        int puzNum = Integer.parseInt(args[0]);
        long timeInput = Long.parseLong(args[2]) * 1000;

        ArrayList<TowerBlock> towerBlocks = new ArrayList<>();
        ArrayList<Float> numberList = new ArrayList<>();
        Random random = new Random();

        File file = new File(args[1]);
       Scanner sc = new Scanner(file);

        if (puzNum == 1) {

            for(int i = 0; i < 40; i++) {
                float num = -10 + (random.nextFloat() * (10 - (-10)));
                numberList.add(num);;
                System.out.println("This is the number" + num + "and this is the count");
            }

            long start = System.currentTimeMillis();
            long end = start + timeInput;
            
            GeneticAlgorithm algo = new GeneticAlgorithm(numberList, null);
            algo.runGeneticAlgorithm();
            
            System.out.println("Times up!");
//        algo.culling();
//        algo.selection();

        }
        else if (puzNum == 2) {
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
            algo2.runGeneticAlgorithm2();
        }

    }
}

