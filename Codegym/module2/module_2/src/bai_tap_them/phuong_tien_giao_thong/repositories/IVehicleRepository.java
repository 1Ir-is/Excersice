package bai_tap_them.phuong_tien_giao_thong.repositories;

import bai_tap_them.phuong_tien_giao_thong.models.Vehicle;

import java.util.ArrayList;

public interface IVehicleRepository {
    ArrayList<Vehicle> getAllVehicles();

    void addVehicle(Vehicle vehicle);

    void deleteVehicle(String licensePlate);

    Vehicle findByLicensePlate(String licensePlate);
}
