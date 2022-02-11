public class TowerBlock {

    private String type; // door, wall, lookout
    private int width;
    private int strength;
    private int cost;

    public TowerBlock(String type, int width, int strength, int cost) {
        this.type = type;
        this.width = width;
        this.strength = strength;
        this.cost = cost;
    }

    public String getType() {
        return this.type;
    }

    public int getWidth() {
        return this.width;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getCost() {
        return this.cost;
    }
}
