package ui;

import entity.Vehicle;
import operation.VehicleRentalService;
import service.FileHandler;
import customexception.VehicleNotAvailableException;

import java.util.Scanner;

public class VehicleRentalUI {
    private static final String FILE_NAME = "vehicles.dat";

    public static void main(String[] args) {
        VehicleRentalService service = new VehicleRentalService();

        // Load vehicles from file if they exist
        try {
            service.getAllVehicles().addAll(FileHandler.loadVehicles(FILE_NAME));
            System.out.println("Vehicles loaded from file.");
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
        }

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nVehicle Rental System:");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Rent Vehicle");
            System.out.println("3. Return Vehicle");
            System.out.println("4. Display All Vehicles");
            System.out.println("5. Save & Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle ID: ");
                    String vehicleId = scanner.nextLine();
                    System.out.print("Enter Model: ");
                    String model = scanner.nextLine();
                    service.addVehicle(new Vehicle(vehicleId, model));
                    System.out.println("Vehicle added.");
                    break;
                case 2:
                    System.out.print("Enter Vehicle ID to Rent: ");
                    vehicleId = scanner.nextLine();
                    try {
                        service.rentVehicle(vehicleId);
                    } catch (VehicleNotAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter Vehicle ID to Return: ");
                    vehicleId = scanner.nextLine();
                    try {
                        service.returnVehicle(vehicleId);
                    } catch (VehicleNotAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("All Vehicles:");
                    for (Vehicle vehicle : service.getAllVehicles()) {
                        System.out.println(vehicle);
                    }
                    break;
                case 5:
                    try {
                        FileHandler.saveVehicles(service.getAllVehicles(), FILE_NAME);
                        System.out.println("Data saved. Exiting...");
                    } catch (Exception e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
