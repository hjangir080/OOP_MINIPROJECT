package javafxapplication;

import javafx.application.Application;
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

        Button retrieveButton = new Button("Retrieve Vehicle");
        retrieveButton.setOnAction(e -> showRetrieveDialog());

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(parkButton, retrieveButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showParkDialog() {
        Stage parkStage = new Stage();
        parkStage.setTitle("Park Vehicle");

        Label typeLabel = new Label("Vehicle Type (Car/Bike/Truck):");
        TextField typeField = new TextField();

        Label numPlateLabel = new Label("Vehicle Number Plate:");
        TextField numPlateField = new TextField();

        Button parkButton = new Button("Park");
        parkButton.setOnAction(e -> {
            String type = typeField.getText().toLowerCase();
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

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(typeLabel, typeField, numPlateLabel, numPlateField, parkButton);

        Scene scene = new Scene(vbox, 300, 150);
        parkStage.setScene(scene);
        parkStage.show();
    }

    private void parkVehicle(Vehicle vehicle) throws ParkingException {
        try {
            parkingLot.parkVehicle(vehicle);
            showAlert("Success", vehicle.getType() + " parked successfully.");
        } catch (Exception e) {
            throw new ParkingException("Error: " + e.getMessage());
        }
    }

    private void showRetrieveDialog() {
        Stage retrieveStage = new Stage();
        retrieveStage.setTitle("Retrieve Vehicle");

        Label floorLabel = new Label("Floor Number (0, 1, 2):");
        TextField floorField = new TextField();

        Label numPlateLabel = new Label("Vehicle Number Plate:");
        TextField numPlateField = new TextField();

        Button retrieveButton = new Button("Retrieve");
        retrieveButton.setOnAction(e -> {
            int floor = Integer.parseInt(floorField.getText());
            String numPlate = numPlateField.getText();

            Vehicle retrievedVehicle = parkingLot.retrieveVehicle(floor, numPlate);
            if (retrievedVehicle != null) {
                showAlert("Success", "Vehicle retrieved successfully.");
            }

            retrieveStage.close();
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(floorLabel, floorField, numPlateLabel, numPlateField, retrieveButton);

        Scene scene = new Scene(vbox, 300, 150);
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
}
