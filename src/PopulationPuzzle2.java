import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PopulationPuzzle2 {

    ArrayList<Tower> population;
    int sumscore;


    public PopulationPuzzle2() {
        this.population = new ArrayList<>();
    }

    void populate(ArrayList<TowerBlock> tbs) {

        ArrayList<TowerBlock> temp = tbs;

        for(int i = 0; i < 100; i++) {
            ArrayList<TowerBlock> blocks = new ArrayList<>();
            Collections.shuffle(temp);
            //System.out.println("temp: " + temp.toString());
            // inserting a random number of shuffled towerblocks into blocks
            for (int j = 0; j < (ThreadLocalRandom.current().nextInt(3, 5 + 1)); j++) {
               // System.out.println("Cur block: " + temp.get(j));

                blocks.add(temp.get(j));
            }
            //System.out.println("block: " + blocks.toString());
            if (!blocks.isEmpty() && blocks.get(0).getType().equals("Door") && blocks.get(blocks.size() - 1).getType().equals("Lookout")) {
                Tower twr = new Tower(blocks);
                //System.out.println(twr.toString());
                population.add(twr);
            }
            else {
                i--;
            }

        }

        //System.out.println("Population: " + population.toString());

    }

    int getFittestIndex() {
        int fittestIndex = 0;
        System.out.println("Pop size at fittest: " + population.size());
        for(int i = 0; i < population.size(); i++) {
            System.out.println("Population i: " + population.get(i));
            int currentFitness = population.get(i).getScore();
            int mostFit = population.get(fittestIndex).getScore();
            //sumscore += population.get(i).getScore();
            if(mostFit < currentFitness) {
                fittestIndex = i;
            }
        }
        return fittestIndex;
    }

    int getSecondFittestIndex() {
        int fittestIndex = getFittestIndex();
        int secondFittestIndex = 0;
        System.out.println("Pop size at secondfittest: " + population.size());
        for(int i = 0; i < population.size(); i++) {
            int currentFitness = population.get(i).getScore();
            int secondFittest = population.get(secondFittestIndex).getScore();
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
            int currentFit = population.get(i).getScore();
            int leastFit = population.get(lowestFittestIndex).getScore();
            if(currentFit < leastFit) {
                lowestFittestIndex = i;
            }
        }
        return lowestFittestIndex;
    }

    ArrayList<Integer> getInvalidTowerIndex() {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < population.size(); i++) {
            if (!population.get(i).isValid()) {
                index.add(i);
            }
        }
        return index;
    }

    void totalScore() {
        //System.out.println("Popoppo: " + population.toString());
        //System.out.println("Popoppo 0 score: " + population.get(0).getScore());
        System.out.println("Hi");

        for (int i = 0; i < population.size(); i++) {
            System.out.println("Population i in totalScore: " + population.get(i).toString());
        }

        for(int i = 0; i < population.size(); i++) {
            //if(population.get(i).isValid()) {
                System.out.println("get score: " + population.get(i).getScore());

                sumscore += population.get(i).getScore();
                System.out.println("Total score: " + sumscore);
            //}
        }

    }

    void setTotalScore() {
        System.out.println("Pop size: " + population.size());
        for (int i = 0; i < population.size(); i++) {
            System.out.println("Cur score: " + Math.abs(population.get(i).getScore()));
            sumscore += Math.abs(population.get(i).getScore());
            System.out.println("Cur sum: " + sumscore);
        }
    }

    int getTotalScore() {
        setTotalScore();
        return Math.abs(sumscore);
    }

    Tower get(int index) { return population.get(index); }

    void add(Tower twr) {
        population.add(twr);
    }

    void remove(int index) {
        population.remove(index);
    }

    float getProbability(int index, int sum) {
        //totalScore();
        int indivScore = Math.abs(population.get(index).getScore());
        //int totalScore = totalScore();
        System.out.println("Sum: " + sum);
        float probability = indivScore/sum;
        return probability;
    }

}
