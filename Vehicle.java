package vehicles;

public abstract class Vehicle {
    private final String numPlate;
    private final int floor;
    private int hoursParked;
    private double payment;

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

    public int getHoursParked() {
        return hoursParked;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(int i) {
        payment=i;
    }

    public void setHoursParked(int hoursParked2) {
        hoursParked=hoursParked2;
    }
}
