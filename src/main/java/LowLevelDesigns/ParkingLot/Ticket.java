package LowLevelDesigns.ParkingLot;

public class Ticket {
    int id;
    Vehicle vehicle;
    ParkingSpot spot;
    long entryTime;

    public Ticket(int id, Vehicle v, ParkingSpot spot) {
        this.id = id;
        this.vehicle = v;
        this.spot = spot;
        this.entryTime = System.currentTimeMillis();
    }
}
