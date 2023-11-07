import java.util.Scanner;
import parking.ParkingLot;
import vehicles.Car;
import vehicles.Bike;
import vehicles.Truck;
import vehicles.Vehicle;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize parking lot with 3 floors, each with a capacity of 10 vehicles
        ParkingLot parkingLot = new ParkingLot(3);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Park a car");
            System.out.println("2. Park a bike");
            System.out.println("3. Park a truck");
            System.out.println("4. Retrieve a vehicle");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character after reading the choice

            switch (choice) {
                case 1:
                    System.out.println("Enter Num plate for car:");
                    String carNumPlate = sc.nextLine();
                    Car car = new Car(carNumPlate, 1); // Cars are assigned to floor 1
                    try {
                        parkingLot.parkVehicle(car);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Enter Num plate for bike:");
                    String bikeNumPlate = sc.nextLine();
                    Bike bike = new Bike(bikeNumPlate, 2); // Bikes are assigned to floor 2
                    try {
                        parkingLot.parkVehicle(bike);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Enter Num plate for truck:");
                    String truckNumPlate = sc.nextLine();
                    Truck truck = new Truck(truckNumPlate, 0); // Trucks are assigned to floor 0
                    try {
                        parkingLot.parkVehicle(truck);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Enter Floor Number (0, 1, 2):");
                    int floorNumber = sc.nextInt();
                    sc.nextLine(); // Consume the newline character after reading the floor number
                    System.out.println("Enter Num Plate of the vehicle:");
                    String numPlate = sc.nextLine();
                    Vehicle retrievedVehicle = parkingLot.retrieveVehicle(floorNumber, numPlate);
                    if (retrievedVehicle != null) {
                        System.out.println("Vehicle with Num Plate " + numPlate + " retrieved successfully from Floor " + floorNumber + ".");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the parking management system. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
