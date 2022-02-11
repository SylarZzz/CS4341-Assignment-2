package src;

import java.util.ArrayList;

public class PopulationPuzzle1 {

    ArrayList<AllBins> population;

    public PopulationPuzzle1() {
        this.population = new ArrayList<>();
    }

    void populate(ArrayList<Float> allNums) {
        //Populations size of 20 for now
        for(int i = 0; i < 20; i++) {
            ArrayList<Float> allNumsRandom = allNums;
            AllBins individual = new AllBins();
            individual.fillAllBins(allNumsRandom);
            population.add(individual);
        }
    }

    int getFittestIndex() {
        int fittestIndex = 0;
        for(int i = 0; i < 20; i++) {
            float currentFitness = population.get(i).getScore();
            float mostFit = population.get(fittestIndex).getScore();
            if(mostFit < currentFitness) {
                fittestIndex = i;
            }
        }
        return fittestIndex;
    }

    int getSecondFittestIndex() {
        int fittestIndex = getFittestIndex();
        int secondFittestIndex = 0;
        for(int i = 0; i < 20; i++) {
            float currentFitness = population.get(i).getScore();
            float secondFittest = population.get(secondFittestIndex).getScore();
            if(currentFitness > secondFittest && i != fittestIndex) {
                secondFittestIndex = i;
            }
        }
        return secondFittestIndex;
    }

    int lowestFittestIndex() {
        int lowestFittestIndex = 0;
        for(int i = 0; i < 20; i++) {
            float currentFit = population.get(i).getScore();
            float leastFit = population.get(lowestFittestIndex).getScore();
            if(currentFit < leastFit) {
                lowestFittestIndex = i;
            }
        }
        return lowestFittestIndex;
    }

    AllBins get(int index) {
        return population.get(index);
    }

    void add(AllBins individual) {
        population.add(individual);
    }
}
