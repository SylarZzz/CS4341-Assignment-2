package src;

import src.TowerBlock;

import java.util.ArrayList;
import java.lang.Math;

public class Tower {

    private int score;
    private int cost;
    ArrayList<TowerBlock> tbs = new ArrayList<>();

    public Tower(ArrayList<TowerBlock> towerBlocks) {
        score = 0;
        this.tbs = towerBlocks;
    }

    // Check if the bottom-most piece is a Door and the top piece is a lookout
    public boolean isValidDoorAndLook() {
        return tbs.get(0).getType().equals("Door") && tbs.get(tbs.size() - 1).getType().equals("Lookout");
    }

    // Check if the pieces between the top and bottom are wall segments
    public boolean isValidWalls() {
        int count = tbs.size() - 2; // how many walls there should be: TowerBlocks.size() - 2 walls.
        int n = 0; // number of walls in the current tower
        for (int i = 1; i < tbs.size() - 1; i++) {  // at this point it assumes the last block and the first block are lookout and door.
            if (tbs.get(i).getType().equals("Wall")) {
                n++;
            }
        }
        return n == count;
    }

    // Check if the tower is a valid tower
    public boolean isValid() {
        if (isValidDoorAndLook() && isValidWalls()) {

            for (int i = 0; i < tbs.size() - 1; i++) {
                // check the current width is larger or equal to the width of the above block
                if (tbs.get(i).getWidth() >= tbs.get(i + 1).getWidth()) {
                    // check strength
                    int curStrength = tbs.get(i).getStrength();
                    int count = 0;
                    for (int j = i + 1; j < tbs.size(); j++){
                        count++;
                    }
                    // check if the strength of the current tower block is greater or equal to the count of the tower blocks above it
                    if (curStrength >= count) {
                        return true;
                    }
                } else {
                    return false;
                }

            }

        }

        return false;
    }




    // get the height of the tower
    public int getHeight() {
        return tbs.size();
    }

    private void computeCost() {
        for (int i = 0; i < tbs.size(); i++) {
            cost += tbs.get(i).getCost();
        }
    }

    // get total Cost of the tower
    public int getCost() {
        computeCost();
        return cost;
    }

    // get the score of the tower
    public int getScore() {
        computeScore();
        return score;
    }

    private void computeScore() {
        if (isValid()) {
            score = 10 + (int)Math.pow(getHeight(), 2) - getCost();
        } else {
            score = 0;
        }
    }
    
    public void resetTower(ArrayList<TowerBlock> resettbs) {
        this.tbs = resettbs;
    }
}
