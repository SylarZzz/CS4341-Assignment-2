package src;

import java.util.ArrayList;
import java.util.*;

public class PopulationPuzzle2 {

    ArrayList<Tower> population;

    public PopulationPuzzle2() {
        this.population = new ArrayList<>();
    }

    void populate(ArrayList<TowerBlock> tbs) {
        ArrayList<TowerBlock> temp = tbs;
        ArrayList<TowerBlock> blocks = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Collections.shuffle(temp);
            // inserting a random number of shuffled towerblocks into blocks
            for (int j = 0; j < (int)Math.random() * tbs.size(); j++) {
                blocks.add(temp.get(j));
            }
            Tower twr = new Tower(blocks);
            population.add(twr);
        }

    }

    int getFittestIndex() {
        int fittestIndex = 0;
        for(int i = 0; i < 20; i++) {
            int currentFitness = population.get(i).getScore();
            int mostFit = population.get(fittestIndex).getScore();
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
            int currentFitness = population.get(i).getScore();
            int secondFittest = population.get(secondFittestIndex).getScore();
            if(currentFitness > secondFittest && i != fittestIndex) {
                secondFittestIndex = i;
            }
        }
        return secondFittestIndex;
    }

    int lowestFittestIndex() {
        int lowestFittestIndex = 0;
        for(int i = 0; i < population.size(); i++) {
            int currentFit = population.get(i).getScore();
            int leastFit = population.get(lowestFittestIndex).getScore();
            if(currentFit < leastFit) {
                lowestFittestIndex = i;
            }
        }
        return lowestFittestIndex;
    }

    int totalScore() {
        int totalScore = 0;
        for(int i = 0; i < population.size(); i++) {
            totalScore = totalScore + population.get(i).getScore();
        }
        return totalScore;
    }

    Tower get(int index) { return population.get(index); }

    void add(Tower twr) {
        population.add(twr);
    }

    void remove(int index) {
        population.remove(index);
    }

    float getProbability(int index) {
        int indivScore = population.get(index).getScore();
        int totalScore = totalScore();
        float probability = indivScore/totalScore;
        return probability;
    }
}
