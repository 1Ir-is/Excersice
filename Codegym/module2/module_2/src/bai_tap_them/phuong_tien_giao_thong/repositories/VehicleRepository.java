package bai_tap_them.phuong_tien_giao_thong.repositories;

import bai_tap_them.phuong_tien_giao_thong.models.Car;
import bai_tap_them.phuong_tien_giao_thong.models.Motorcycle;
import bai_tap_them.phuong_tien_giao_thong.models.Truck;
import bai_tap_them.phuong_tien_giao_thong.models.Vehicle;

import java.util.ArrayList;

public class VehicleRepository implements IVehicleRepository {
    private static final ArrayList<Vehicle> vehicles = new ArrayList<>();

    static {
        vehicles.add(new Truck("51C-12345", "Hyundai", 2015, "Huynh Minh Huy", 5.0));
        vehicles.add(new Truck("51C-67890", "Isuzu", 2018, "Tran Trung Chien", 8.0));
        vehicles.add(new Truck("51C-54321", "Hino", 2020, "Nguyen Duc Vinh", 10.0));

        vehicles.add(new Car("30A-12345", "Toyota", 2017, "Phan Ta Anh Vuong", 5, "Du lich"));

        vehicles.add(new Motorcycle("59B1-12345", "Yamaha", 2016, "Ton That Duy", 150.0));
    }

    @Override
    public ArrayList<Vehicle> getAllVehicles() {
        return vehicles;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public void deleteVehicle(String licensePlate) {
        vehicles.remove(findByLicensePlate(licensePlate));
    }


    @Override
    public Vehicle findByLicensePlate(String licensePlate) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i) != null && vehicles.get(i).getLicensePlate().equals(licensePlate)) {
                return vehicles.get(i);
            }
        }
        // Không tìm thấy
        return null;
    }
}
