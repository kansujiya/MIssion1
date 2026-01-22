package LowLevelDesigns.ParkingLot;

import ParkingLot.ParkingLot;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ParkingLotController parkingLot = new ParkingLotController();

        ParkingFloor parkingFloor1 = new ParkingFloor();
        parkingFloor1.addSpot(new ParkingSpot(1, SpotType.CAR));
        parkingFloor1.addSpot(new ParkingSpot(2, SpotType.BIKE));

        ParkingFloor parkingFloor2 = new ParkingFloor();
        parkingFloor2.addSpot(new ParkingSpot(3, SpotType.CAR));
        parkingFloor2.addSpot(new ParkingSpot(3, SpotType.TRUCK));

        parkingLot.addFloor(parkingFloor1);
        parkingLot.addFloor(parkingFloor2);

        Vehicle car = new Vehicle("KA-01", VehicleType.CAR);
        Ticket t = parkingLot.parkVehicle(car);

        Thread.sleep(2000);
        parkingLot.unParkVehicle(t.id);
    }

}
