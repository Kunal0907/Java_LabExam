package service;

import entity.Vehicle;

import java.io.*;
import java.util.List;

public class FileHandler {

    public static void saveVehicles(List<Vehicle> vehicles, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(vehicles);
        }
    }

    public static List<Vehicle> loadVehicles(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Vehicle>) ois.readObject();
        }
    }
}
