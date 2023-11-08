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

    Vehicle retrieveVehicle(String numPlate,int hoursParked) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].isOccupied() && slots[i].retrieveVehicle().getNumPlate().equals(numPlate)) {
                System.out.println("Vehicle with Num Plate " + numPlate + " retrieved successfully from Slot " + i + " on this floor.");
                Vehicle retrievedVehicle = slots[i].retrieveVehicle();
                if (retrievedVehicle != null) {
                    retrievedVehicle.setHoursParked(hoursParked); // Set user-input hours parked
                    calculatePayment(retrievedVehicle);
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
    private void calculatePayment(Vehicle vehicle) {
        int hours = vehicle.getHoursParked();
        if (hours < 1) {
            vehicle.setPayment(20);
        } else if (hours < 2) {
            vehicle.setPayment(40);
        } else if (hours < 4) {
            vehicle.setPayment(60);
        } else {
            vehicle.setPayment(1000);
        }
    }
}
