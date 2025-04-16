package bai_tap_them.phuong_tien_giao_thong.repositories;

import bai_tap_them.phuong_tien_giao_thong.models.Car;
import bai_tap_them.phuong_tien_giao_thong.models.Motorcycle;
import bai_tap_them.phuong_tien_giao_thong.models.Truck;
import bai_tap_them.phuong_tien_giao_thong.models.Vehicle;

public class VehicleRepository implements IVehicleRepository {
    private static final int MAX_VEHICLES = 100;
    private static final Vehicle[] vehicles = new Vehicle[MAX_VEHICLES];

    static {
        vehicles[0] = new Truck("51C-12345", "Hyundai", 2015, "Huynh Minh Huy", 5.0);
        vehicles[1] = new Truck("51C-67890", "Isuzu", 2018, "Tran Trung Chien", 8.0);
        vehicles[2] = new Truck("51C-54321", "Hino", 2020, "Nguyen Duc Vinh", 10.0);

        vehicles[3] = new Car("30A-12345", "Toyota", 2017, "Phan Ta Anh Vuong", 5, "Du lich");
        vehicles[4] = new Car("30A-67890", "Honda", 2019, "Le Van Tam", 7, "Xe khach");
        vehicles[5] = new Car("30A-54321", "Mazda", 2021, "Nguyen Thanh Nhon", 4, "Du lich");

        vehicles[6] = new Motorcycle("59B1-12345", "Yamaha", 2016, "Ton That Duy", 150.0);
        vehicles[7] = new Motorcycle("59B1-67890", "Honda", 2018, "Le Thu Thao", 125.0);
        vehicles[8] = new Motorcycle("59B1-54321", "Suzuki", 2020, "Pham Ngoc Khanh", 175.0);
    }

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
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null && vehicle.getLicensePlate().equals(licensePlate)) {
                return vehicle;
            }
        }
        // ko tim thay
        return null;
    }
}
