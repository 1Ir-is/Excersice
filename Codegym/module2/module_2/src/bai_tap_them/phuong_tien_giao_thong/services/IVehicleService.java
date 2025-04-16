package bai_tap_them.phuong_tien_giao_thong.services;

import bai_tap_them.phuong_tien_giao_thong.models.Vehicle;

public interface IVehicleService {
    Vehicle[] getAllVehicles();

    void addVehicle(Vehicle vehicle);
}
