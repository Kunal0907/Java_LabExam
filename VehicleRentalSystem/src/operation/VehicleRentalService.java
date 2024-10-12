package operation;

import entity.Vehicle;
import customexception.VehicleNotAvailableException;

import java.util.ArrayList;
import java.util.List;

public class VehicleRentalService {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void rentVehicle(String vehicleId) throws VehicleNotAvailableException {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                if (!vehicle.isRented()) {
                    vehicle.rent();
                    System.out.println("Vehicle rented successfully.");
                    return;
                } else {
                    throw new VehicleNotAvailableException("Vehicle is already rented.");
                }
            }
        }
        throw new VehicleNotAvailableException("Vehicle not found.");
    }

    public void returnVehicle(String vehicleId) throws VehicleNotAvailableException {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                if (vehicle.isRented()) {
                    vehicle.returnVehicle();
                    System.out.println("Vehicle returned successfully.");
                    return;
                } else {
                    throw new VehicleNotAvailableException("Vehicle is not rented.");
                }
            }
        }
        throw new VehicleNotAvailableException("Vehicle not found.");
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }
}
