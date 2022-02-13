package src;

import java.util.ArrayList;

public class AllBins {

    Bin bin1;
    Bin bin2;
    Bin bin3;
    Bin bin4;

    public AllBins() {
        this.bin1 = new Bin();
        this.bin2 = new Bin();
        this.bin3 = new Bin();
        this.bin4 = new Bin();
    }

    void fillAllBins(ArrayList<Float> allNums) {
        for(int i = 0; i < allNums.size(); i++) {
            if(i < 10) {
                bin1.add(allNums.get(i));
            } else if(i < 20) {
                bin2.add(allNums.get(i));
            } else if(i < 30) {
                bin3.add(allNums.get(i));
            } else if(i < 40) {
                bin4.add(allNums.get(i));
            }
        }
    }

    ArrayList<Float> emptyAllBins() {
        ArrayList<Float> allNums = new ArrayList<>();
        for(int i = 0; i < 40; i++) {
            if(i < 10) {
                allNums.add(bin1.get(i));
            } else if(i < 20) {
                allNums.add(bin2.get(i-10));
            } else if(i < 30) {
                allNums.add(bin3.get(i-20));
            } else if(i < 40) {
                allNums.add(bin4.get(i-30));
            }
        }
        for(int i = 9; i >= 0; i--) {
            bin1.remove(i);
            bin2.remove(i);
            bin3.remove(i);
            bin4.remove(i);
        }
        return allNums;
    }

    float getScore() {
        float scoreBin1 = 0;
        float scoreBin2 = 0;
        float scoreBin3 = 0;
        float totalScore = 0;
        for(int i = 0; i < 10; i++) {

            //Calculates score for bin 1
            if(i == 0) {
                //Prevents multiplying by 0
                scoreBin1 = bin1.get(i);
            } else {
                scoreBin1 = scoreBin1 * bin1.get(i);
            }

            //Calculates score for bin2
            scoreBin2 = scoreBin2 + bin2.get(i);

            //Calculates score for bin3
            float largest = 0;
            float smallest = 0;
            if(i == 0) {
                smallest = bin3.get(i);
                largest = bin3.get(i);
            } else {
                if(bin3.get(i) > largest) {
                    largest = bin3.get(i);
                }
                if(bin3.get(i) < smallest) {
                    smallest = bin3.get(i);
                }
            }
            if(i == 9) {
                scoreBin3 = largest - smallest;
            }
        }

        totalScore = scoreBin1 + scoreBin2 + scoreBin3;
        return totalScore;
    }

}
