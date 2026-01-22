package LowLevelDesigns.ParkingLot;

public class ParkingSpot {

    int id;
    SpotType type;
    boolean isFree = true;
    Vehicle parkedVehicle;

    public ParkingSpot(int id, SpotType type) {
        this.id = id;
        this.type = type;
    }

    public boolean canFit(Vehicle v) {
        return isFree && type.name().equals(v.type.name());
    }

    public void park(Vehicle v) {
        this.parkedVehicle = v;
        this.isFree = false;
    }

    public void unPark() {
        this.parkedVehicle = null;
        isFree = true;
    }
}
