package src;

import java.util.ArrayList;

public class GeneticAlgorithm {

    //These are for the scenario that the file given is for puzzle 1
    PopulationPuzzle1 popPuz1 = new PopulationPuzzle1();
    AllBins fittestPuz1;
    AllBins secFittestPuz1;


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

    void selectFittest() {
        fittestPuz1 = popPuz1.get(popPuz1.getFittestIndex());
        secFittestPuz1 = popPuz1.get(popPuz1.getSecondFittestIndex());
    }

    void crossOver() {
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
        popPuz1.add(child1);
        popPuz1.add(child2);
    }
}
