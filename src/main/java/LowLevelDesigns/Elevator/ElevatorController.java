package LowLevelDesigns.Elevator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElevatorController {

    List<Elevator> elevators;

    public ElevatorController(int count) {
        elevators = new ArrayList<>();
        for(int i = 0; i< count; i++) {
            elevators.add(new Elevator(i));
        }
    }

    public void handleExternalRequest(int source, int destination) {
        Request request = new Request(source, destination);
        Elevator e = assignElevator(request);
        e.addStop(source);
        e.addStop(destination);
    }

    private Elevator assignElevator(Request request) {
        Elevator best = null;
        int minDist = Integer.MAX_VALUE;

        for(Elevator e : elevators) {
            if(e.isIdeal() || e.getDirection() == request.direction) {
                int dist = Math.abs(e.getCurrentFloor() - request.sourceFloor);
                if(dist < minDist) {
                    minDist = dist;
                    best = e;
                }
            }
        }

        if(best == null) {
            best = elevators.get(0);
        }

        return best;
    }

    // Simulate time ticks
    public void stepAll() {
        for (Elevator e : elevators) {
            e.step();
        }
    }

}
