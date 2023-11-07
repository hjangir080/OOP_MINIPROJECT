package vehicles;

public class Car extends Vehicle {
    public Car(String numPlate, int floor) {
        super(numPlate, floor);
    }

    @Override
    public String getType() {
        return "Car";
    }
}
