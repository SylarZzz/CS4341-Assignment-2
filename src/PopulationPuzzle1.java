package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class PopulationPuzzle1 {

    ArrayList<AllBins> population;

    public PopulationPuzzle1() {
        this.population = new ArrayList<>();
    }

    void populate(ArrayList<Float> allNums) {
        ArrayList<Float> temp = allNums;
        //Populations size of 20 for now
        for(int i = 0; i < 20; i++) {
            Collections.shuffle(temp);
            AllBins individual = new AllBins();
            individual.fillAllBins(temp);
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
    
    int getMedianFittestIndex() {
        int medianFittestIndex = population.size() / 2;
        medianFittestIndex = medianFittestIndex > 0 && medianFittestIndex % 2 == 0 ? medianFittestIndex - 1 : medianFittestIndex;
        return medianFittestIndex;
    }

    int lowestFittestIndex() {
        int lowestFittestIndex = 0;
        for(int i = 0; i < population.size(); i++) {
            float currentFit = population.get(i).getScore();
            float leastFit = population.get(lowestFittestIndex).getScore();
            if(currentFit < leastFit) {
                lowestFittestIndex = i;
            }
        }
        return lowestFittestIndex;
    }

    float totalScore() {
        float totalScore = 0;
        for(int i = 0; i < population.size(); i++) {
            totalScore = totalScore + population.get(i).getScore();
        }
        return totalScore;
    }

    AllBins get(int index) {
        return population.get(index);
    }

    void add(AllBins individual) {
        population.add(individual);
    }

    void remove(int index) {
        population.remove(index);
    }

    float getProbability(int index) {
        float indivScore = population.get(index).getScore();
        float totalScore = totalScore();
        float probability = indivScore/totalScore;
        return probability;
    }

}
