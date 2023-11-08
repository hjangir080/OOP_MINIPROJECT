package javafxapplication;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parking.ParkingLot;
import parking.exceptions.ParkingException;
import vehicles.Car;
import vehicles.Bike;
import vehicles.Truck;
import vehicles.Vehicle;

public class ParkingApp extends Application {

    private final ParkingLot parkingLot = new ParkingLot(3); // Initialize parking lot with 3 floors

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Parking Management System");

        Button parkButton = new Button("Park Vehicle");
        parkButton.setOnAction(e -> showParkDialog());
        parkButton.setAlignment(Pos.CENTER);

        Button retrieveButton = new Button("Retrieve Vehicle");
        retrieveButton.setOnAction(e -> showRetrieveDialog());
        retrieveButton.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(parkButton, retrieveButton);
        vbox.setAlignment(Pos.CENTER); // Center the VBox content

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showParkDialog() {
        Stage parkStage = new Stage();
        parkStage.setTitle("Park Vehicle");

        Label typeLabel = new Label("Vehicle Type:");
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("Car", "Bike", "Truck");

        Label numPlateLabel = new Label("Vehicle Number Plate:");
        TextField numPlateField = new TextField();

        Button parkButton = new Button("Park");
        parkButton.setOnAction(e -> {
            String type = typeComboBox.getValue().toLowerCase(); // Retrieve selected item from combo box
            String numPlate = numPlateField.getText();

            try {
                if ("car".equals(type)) {
                    parkVehicle(new Car(numPlate, 1)); // Cars are assigned to floor 1
                } else if ("bike".equals(type)) {
                    parkVehicle(new Bike(numPlate, 2)); // Bikes are assigned to floor 2
                } else if ("truck".equals(type)) {
                    parkVehicle(new Truck(numPlate, 0)); // Trucks are assigned to floor 0
                } else {
                    throw new ParkingException("Invalid vehicle type.");
                }

                parkStage.close();
            } catch (ParkingException ex) {
                showAlert("Error", ex.getMessage());
            }
        });
        parkButton.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(typeLabel, typeComboBox, numPlateLabel, numPlateField, parkButton);
        vbox.setAlignment(Pos.CENTER); // Center the VBox content

        Scene scene = new Scene(vbox, 300, 150);
        parkStage.setScene(scene);
        parkStage.show();
    }

    private void parkVehicle(Vehicle vehicle) {
        try {
            int floor = vehicle.getFloor();
            Object slot = parkingLot.parkVehicle(vehicle); // Assuming parkVehicle method returns the slot number
            showAlert("Success", vehicle.getType() + " parked successfully on Floor " + floor + ", Slot " + slot + ".");
        } catch (ParkingException e) {
            showAlert("Error", "Parking Error: " + e.getMessage());
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private void showRetrieveDialog() {
        Stage retrieveStage = new Stage();
        retrieveStage.setTitle("Retrieve Vehicle");

        Label floorLabel = new Label("Floor Number (0, 1, 2):");
        TextField floorField = new TextField();

        Label numPlateLabel = new Label("Vehicle Number Plate:");
        TextField numPlateField = new TextField();

        Label hoursParkedLabel = new Label("Hours Parked:");
        TextField hoursParkedField = new TextField();

        Button retrieveButton = new Button("Retrieve");
        retrieveButton.setOnAction(e -> {
            int floor = Integer.parseInt(floorField.getText());
            String numPlate = numPlateField.getText();
            int hoursParked = Integer.parseInt(hoursParkedField.getText());

            Object retrievedVehicle = parkingLot.retrieveVehicle(floor, numPlate,hoursParked);
            if (retrievedVehicle != null) {
                double payment = calculatePayment(hoursParked);
                showAlertWithPayment("Success", "Vehicle retrieved successfully.", payment);
            }

            retrieveStage.close();
        });
        retrieveButton.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(floorLabel, floorField, numPlateLabel, numPlateField, hoursParkedLabel, hoursParkedField, retrieveButton);
        vbox.setAlignment(Pos.CENTER); // Center the VBox content

        Scene scene = new Scene(vbox, 300, 200);
        retrieveStage.setScene(scene);
        retrieveStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlertWithPayment(String title, String message, double payment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message + "\nPayment: $" + payment);
        alert.showAndWait();
    }

    private double calculatePayment(int hoursParked) {
        if (hoursParked < 1) {
            return 20;
        } else if (hoursParked < 2) {
            return 40;
        } else if (hoursParked < 4) {
            return 60;
        } else {
            return 1000;
        }
    }
}



//MAIN CLASS
package javafxapplication;

public class Main {
    public static void main(String[] args) {
        ParkingApp.main(args);
    }
}

