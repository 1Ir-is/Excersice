package bai_tap_them.phuong_tien_giao_thong.repositories;

import bai_tap_them.phuong_tien_giao_thong.models.Vehicle;

public interface IVehicleRepository {
    Vehicle[] getAllVehicles();

    void addVehicle(Vehicle vehicle);

    void deleteVehicle(String licensePlate);

    Vehicle findByLicensePlate(String licensePlate);
}
