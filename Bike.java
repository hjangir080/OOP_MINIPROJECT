package vehicles;

public class Bike extends Vehicle {
    public Bike(String numPlate, int floor) {
        super(numPlate, floor);
    }

    @Override
    public String getType() {
        return "Bike";
    }
}
