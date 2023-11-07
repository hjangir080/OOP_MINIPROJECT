package parking;

import vehicles.Vehicle;

public class ParkingSlot {
    private Vehicle vehicle;

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle retrieveVehicle() {
        Vehicle parkedVehicle = this.vehicle;
        this.vehicle = null;
        return parkedVehicle;
    }

    boolean isOccupied() {
        return this.vehicle != null;
    }
}
