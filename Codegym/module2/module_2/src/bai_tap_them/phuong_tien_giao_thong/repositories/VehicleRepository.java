package bai_tap_them.phuong_tien_giao_thong.repositories;

import bai_tap_them.phuong_tien_giao_thong.models.Vehicle;

public class VehicleRepository implements IVehicleRepository {
    private static final int MAX_VEHICLES = 100;
    private final Vehicle[] vehicles = new Vehicle[MAX_VEHICLES];

    @Override
    public Vehicle[] getAllVehicles() {
        return vehicles;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] == null) {
                vehicles[i] = vehicle;
                return;
            }
        }
        System.out.print("Vehicle array is full");
    }

    @Override
    public void deleteVehicle(String licensePlate) {

    }

    @Override
    public Vehicle findByLicensePlate(String licensePlate) {
        return null;
    }
}
