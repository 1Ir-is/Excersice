package bai_tap_them.phuong_tien_giao_thong.repositories;

import bai_tap_them.phuong_tien_giao_thong.models.Car;
import bai_tap_them.phuong_tien_giao_thong.models.Motorcycle;
import bai_tap_them.phuong_tien_giao_thong.models.Truck;
import bai_tap_them.phuong_tien_giao_thong.models.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {
    private static final String CAR_FILE = "bai_tap_them/phuong_tien_giao_thong/data/cars.csv";
    private static final String TRUCK_FILE = "bai_tap_them/phuong_tien_giao_thong/data/trucks.csv";
    private static final String MOTORCYCLE_FILE = "bai_tap_them/phuong_tien_giao_thong/data/motorcycles.csv";

    private final List<Vehicle> vehicles = new ArrayList<>();

    public VehicleRepository() {
        readFromAllCSV();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        writeToSeparateFiles();
    }

    @Override
    public void deleteVehicle(String licensePlate) {
        vehicles.remove(findByLicensePlate(licensePlate));
        writeToSeparateFiles();
    }

    @Override
    public Vehicle findByLicensePlate(String licensePlate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    private void writeToSeparateFiles() {
        List<String> cars = new ArrayList<>();
        List<String> trucks = new ArrayList<>();
        List<String> motorcycles = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                cars.add(vehicle.toCSV());
            } else if (vehicle instanceof Truck) {
                trucks.add(vehicle.toCSV());
            } else if (vehicle instanceof Motorcycle) {
                motorcycles.add(vehicle.toCSV());
            }
        }

        writeListToFile(CAR_FILE, cars);
        writeListToFile(TRUCK_FILE, trucks);
        writeListToFile(MOTORCYCLE_FILE, motorcycles);
    }

    private void writeListToFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi ghi file " + filePath + ": " + e.getMessage());
        }
    }


    private void readFromAllCSV() {
        vehicles.clear();
        readCarFile();
        readTruckFile();
        readMotorcycleFile();
    }

    private void readCarFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CAR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String license = data[1];
                String manufacturer = data[2];
                int year = Integer.parseInt(data[3]);
                String owner = data[4];
                int seats = Integer.parseInt(data[5]);
                String carType = data[6];
                vehicles.add(new Car(license, manufacturer, year, owner, seats, carType));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Lỗi khi đọc file Car: " + e.getMessage());
        }
    }

    private void readTruckFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRUCK_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String license = data[1];
                String manufacturer = data[2];
                int year = Integer.parseInt(data[3]);
                String owner = data[4];
                double load = Double.parseDouble(data[7]);
                vehicles.add(new Truck(license, manufacturer, year, owner, load));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Lỗi khi đọc file Truck: " + e.getMessage());
        }
    }

    private void readMotorcycleFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MOTORCYCLE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String license = data[1];
                String manufacturer = data[2];
                int year = Integer.parseInt(data[3]);
                String owner = data[4];
                double power = Double.parseDouble(data[8]);
                vehicles.add(new Motorcycle(license, manufacturer, year, owner, power));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Lỗi khi đọc file Motorcycle: " + e.getMessage());
        }
    }
}
