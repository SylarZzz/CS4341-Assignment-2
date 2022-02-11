package src;

import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithm {

    //These are for the scenario that the file given is for puzzle 1
    PopulationPuzzle1 popPuz1 = new PopulationPuzzle1();
    ArrayList<Float> allNums = new ArrayList<>();

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

    //Removing 30% of the population
    void culling() {
        for(int i = 0; i < 6; i++) {
            int leastFitindex = popPuz1.lowestFittestIndex();
            popPuz1.remove(leastFitindex);
        }
    }

    //Selection
    void selection(int index1, int index2) {
        ArrayList<Float> probabilityList = new ArrayList<>();
        for(int i = 0; i < popPuz1.population.size(); i++) {
            float prob = 0;
            prob = popPuz1.getProbability(i);
            probabilityList.add(prob);
        }
        float random1 = -1;
        float random2 = -1;
        for(int i = 0; i < probabilityList.size(); i++) {
            if(i == 0) {
                if(random1 > 0 && random1 < probabilityList.get(i)) {
                    index1 = i;
                }
                if(random2 > 0 && random2 < probabilityList.get(i) && index1 != i) {
                    index2 = i;
                }
            } else {
                if(random1 > probabilityList.get(i-1) && random1 < probabilityList.get(i)) {
                    index1 = i;
                }
                if(random2 > probabilityList.get(i-1) && random2 < probabilityList.get(i) && index1 != i) {
                    index2 = i;
                }
            }
        }
        //In case both random floats are in the same range
        while(index2 == -1) {
            random2 = -1; //This will reselect random2
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
        PopulationPuzzle1 nextGenPop = new PopulationPuzzle1();
        while(genCount < 20) {  //Here we have some sort of value, or selecting what our minimum fitness level is
            genCount++;

            //Add two fittest parents from previous gen (Elitism)
            AllBins fittest1;
            AllBins fittest2;
            fittest1 = popPuz1.get(popPuz1.getFittestIndex());
            fittest2 = popPuz1.get(popPuz1.getSecondFittestIndex());
            nextGenPop.add(fittest1);
            nextGenPop.add(fittest2);

            //Remove the bottom 30% from the current pop before selection (Culling)
            culling();

            //Selection
            int selectedIndex1 = -1;
            int selectedIndex2 = -1;
            selection(selectedIndex1,selectedIndex2);
            AllBins selected1 = popPuz1.get(selectedIndex1);
            AllBins selected2 = popPuz1.get(selectedIndex2);

            //Crossover
            crossOver(selected1, selected2, nextGenPop);

            //Fill in rest of population
            populateWithChildren(nextGenPop,allNums);

            //
        }
    }
}
