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

    public double retrieveVehicle(int floor, String numPlate,int hoursParked) {
        if (floor >= 0 && floor < floors.length) {
            Vehicle retrievedVehicle= floors[floor].retrieveVehicle(numPlate,hoursParked);
            if (retrievedVehicle != null) {
                double payment = retrievedVehicle.getPayment();
                System.out.println("Payment for vehicle with Num Plate " + numPlate + " is: $" + payment);
                return payment;
            }
            else {
                System.out.println("Invalid floor number.");
            }
        }
        return 0;
    }
}
