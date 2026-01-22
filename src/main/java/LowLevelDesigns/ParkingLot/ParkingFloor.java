package LowLevelDesigns.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    int floorNumber;
    List<ParkingSpot> spots = new ArrayList<>();

    public void ParkingSpot(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public ParkingSpot getFreeParkingSpot(Vehicle v) {
        for(ParkingSpot s: spots) {
            if(s.canFit(v)) return s;
        }
        return null;
    }
}
