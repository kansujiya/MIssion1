package LowLevelDesigns.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotController {
    List<ParkingFloor> parkingFloors = new ArrayList<>();
    Map<Integer, Ticket> activeTicket = new HashMap<>();
    private int ticketSequence = 1;

    public void addFloor(ParkingFloor parkingFloor) {
        parkingFloors.add(parkingFloor);
    }

    //Entry
    public Ticket parkVehicle(Vehicle v) {
        for(ParkingFloor floor: parkingFloors) {
            ParkingSpot spot = floor.getFreeParkingSpot(v);
            if(spot != null) {
                spot.park(v);
                Ticket ticket = new Ticket(ticketSequence++, v, spot);
                activeTicket.put(ticket.id, ticket);
                System.out.println("Parked " + v.number + " at spot " + spot.id);
                return ticket;
            }
        }
        System.out.println("No spot available for " + v.type);
        return null;
    }

    //Exit
    public double unParkVehicle(int ticketId) {
        Ticket t = activeTicket.get(ticketId);
        if(t == null) {
            throw new RuntimeException("Invalid ticket");
        }

        t.spot.unPark();

        long duration = System.currentTimeMillis() - t.entryTime;
        double hours = Math.ceil(duration / (1000.0 * 60 * 60));

        double amount = calculateFee(t.vehicle.type, hours);
        System.out.println("Vehicle " + t.vehicle.number + " exited. Fee = " + amount);
        return amount;
    }

    private double calculateFee(VehicleType type, double hours) {
        switch (type) {
            case BIKE: return hours * 10;
            case CAR: return hours * 20;
            case TRUCK: return hours * 40;
            default: return 0;
        }
    }
}
