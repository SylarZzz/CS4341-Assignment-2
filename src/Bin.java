package src;

import java.util.ArrayList;

public class Bin {

    ArrayList<Float> inBin;

    public Bin() {
        this.inBin = new ArrayList<>();
    }

    void add(float num) {
        inBin.add(num);
    }

    int size() {
        return inBin.size();
    }

    float get(int index) {
        return inBin.get(index);
    }
}
