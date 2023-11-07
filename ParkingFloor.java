package parking;

import vehicles.Vehicle;

public class ParkingFloor {
    private final ParkingSlot[] slots;

    ParkingFloor(int capacity) {
        slots = new ParkingSlot[capacity];
        for (int i = 0; i < capacity; i++) {
            slots[i] = new ParkingSlot();
        }
    }

    int parkVehicle(Vehicle vehicle) throws Exception {
        for (int i = 0; i < slots.length; i++) {
            if (!slots[i].isOccupied()) {
                slots[i].parkVehicle(vehicle);
                System.out.println(vehicle.getType() + " with Num Plate " + vehicle.getNumPlate() + " parked successfully in Slot " + i + " on Floor " + vehicle.getFloor());
                return i; // Return the slot number where the vehicle was parked
            }
        }
        throw new Exception("Floor is full. No available slots for " + vehicle.getType() + ".");
    }

    Vehicle retrieveVehicle(String numPlate) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].isOccupied() && slots[i].retrieveVehicle().getNumPlate().equals(numPlate)) {
                System.out.println("Vehicle with Num Plate " + numPlate + " retrieved successfully from Slot " + i + " on this floor.");
                Vehicle retrievedVehicle = slots[i].retrieveVehicle();
                if (retrievedVehicle != null) {
                    return retrievedVehicle;
                } else {
                    System.out.println("Slot empty.");
                    return null;
                }
            }
        }
        System.out.println("Invalid number plate or no matching vehicle found on this floor.");
        return null;
    }
}
