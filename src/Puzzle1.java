import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Puzzle1 {

    HashMap<Integer, ArrayList<Double>> myBins;
    ArrayList<Double> bin1; //the numbers are all multiplied together
    ArrayList<Double> bin2; //the numbers are all added together
    ArrayList<Double> bin3; //the smallest number is subtracted from the largest
    ArrayList<Double> bin4; //these numbers are ignored

    public Puzzle1() {
        this.myBins = new HashMap();
        this.bin1 = new ArrayList<>();
        this.bin2 = new ArrayList<>();
        this.bin3 = new ArrayList<>();
        this.bin4 = new ArrayList<>();
    }

    public void puzzle(String fileName) throws FileNotFoundException {
        //get list of the puzzle numbers from file
        ArrayList<Double> numList = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(fileName))) {
            while (s.hasNext()) {
                double target = s.nextDouble();
                numList.add(target);
            }
        }
        System.out.println("numList is: " + numList);

        fillBins(numList);

    }

    public void fillBins(ArrayList<Double> numList){
        ArrayList<ArrayList<Double>> listOfBins = new ArrayList<>();
        listOfBins.add(bin1);
        listOfBins.add(bin2);
        listOfBins.add(bin3);
        listOfBins.add(bin4);

//        for (ArrayList<Double> bin : listOfBins) {
//            while(bin.size() < 10){
//                //add number from input.txt file
//                //bin.add(numList.indexOf());
//            }
//        }

        setMyBins();
    }

    public void setMyBins() {
        myBins.put(1, bin1);
        myBins.put(2, bin2);
        myBins.put(3, bin3);
        myBins.put(4, bin4);
    }
}
