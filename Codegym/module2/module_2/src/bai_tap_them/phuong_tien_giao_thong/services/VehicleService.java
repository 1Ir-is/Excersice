package bai_tap_them.phuong_tien_giao_thong.services;

import bai_tap_them.phuong_tien_giao_thong.models.Vehicle;
import bai_tap_them.phuong_tien_giao_thong.repositories.IVehicleRepository;
import bai_tap_them.phuong_tien_giao_thong.repositories.VehicleRepository;

public class VehicleService implements IVehicleService {
    private final IVehicleRepository repository = new VehicleRepository();


    @Override
    public Vehicle[] getAllVehicles() {
        return repository.getAllVehicles();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        repository.addVehicle(vehicle);
    }
}
