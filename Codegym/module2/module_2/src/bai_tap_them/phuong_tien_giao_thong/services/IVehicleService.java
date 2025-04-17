package bai_tap_them.phuong_tien_giao_thong.services;

import bai_tap_them.phuong_tien_giao_thong.models.Vehicle;

import java.util.ArrayList;

public interface IVehicleService {
    ArrayList<Vehicle> getAllVehicles();
    void addVehicle(Vehicle vehicle);
    Vehicle findByLicensePlate(String licensePlate);
    void deleteVehicle(String licensePlate);
}
