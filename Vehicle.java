package vehicles;

public abstract class Vehicle {
    private final String numPlate;
    private final int floor;

    Vehicle(String numPlate, int floor) {
        this.numPlate = numPlate;
        this.floor = floor;
    }

    public abstract String getType();

    public String getNumPlate() {
        return numPlate;
    }

    public int getFloor() {
        return floor;
    }
}

