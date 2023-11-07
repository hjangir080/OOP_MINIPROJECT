package parking;

import vehicles.Vehicle;

public class ParkingLot {
    private final ParkingFloor[] floors;

    public ParkingLot(int floorsCount) {
        floors = new ParkingFloor[floorsCount];
        for (int i = 0; i < floorsCount; i++) {
            floors[i] = new ParkingFloor(10); // Each floor has a capacity of 10 vehicles
        }
    }

    public int parkVehicle(Vehicle vehicle) throws Exception {
        int slotNumber = floors[vehicle.getFloor()].parkVehicle(vehicle);
        return slotNumber;
    }

    public Vehicle retrieveVehicle(int floor, String numPlate) {
        if (floor >= 0 && floor < floors.length) {
            return floors[floor].retrieveVehicle(numPlate);
        } else {
            System.out.println("Invalid floor number.");
        }
        return null;
    }
}
