package LowLevelDesigns.ParkingLot;

enum VehicleType {
    BIKE, CAR, TRUCK
}

enum SpotType {
    BIKE, CAR, TRUCK
}

public class Vehicle {
    String number;
    VehicleType type;

    Vehicle(String number, VehicleType type) {
        this.number = number;
        this.type = type;
    }
}
