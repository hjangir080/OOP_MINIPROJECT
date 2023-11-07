package vehicles;

public class Truck extends Vehicle {
    public Truck(String numPlate, int floor) {
        super(numPlate, floor);
    }

    @Override
    public String getType() {
        return "Truck";
    }
}
