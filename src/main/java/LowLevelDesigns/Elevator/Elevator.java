package LowLevelDesigns.Elevator;

import com.sun.source.tree.Tree;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

public class Elevator {
    int id;
    private int currentFloor = 0;
    private Direction direction = Direction.IDLE;
    private Status status = Status.STOPPED;

    //user TreeSet for ordered stoppage.

    private final TreeSet<Integer> upStops = new TreeSet<>();
    private final TreeSet<Integer> downStops = new TreeSet<>(Collections.reverseOrder());

    public Elevator(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean isIdeal() {
        return this.direction == Direction.IDLE;
    }

    public void addStop(int floor) {
        if(floor > currentFloor) {
            upStops.add(floor);
        } else if(floor < currentFloor) {
            downStops.add(floor);
        }
    }

    public void step() {
        if(direction == Direction.IDLE) {
            chooseDirection();
        }

        if(direction == Direction.UP) {
            moveUp();
        } else if(direction == Direction.DOWN) {
            moveDown();
        }
    }

    public void chooseDirection() {
        if(!upStops.isEmpty()) {
            direction = Direction.UP;
        } else if(!downStops.isEmpty()) {
            direction = Direction.DOWN;
        } else {
            direction = Direction.IDLE;
        }
    }

    public void moveUp() {
        status = Status.MOVING;
        currentFloor++;

        if(upStops.contains(currentFloor)) {
            stopAtFloor(currentFloor);
            upStops.remove(currentFloor);
        }

        if(upStops.isEmpty()) {
            chooseDirection();
        }
    }

    public void moveDown() {
        status = Status.MOVING;
        currentFloor--;

        if(downStops.contains(currentFloor)) {
            stopAtFloor(currentFloor);
            downStops.remove(currentFloor);
        }

        if(downStops.isEmpty()) {
            chooseDirection();
        }
    }

    public void stopAtFloor(int floor) {
        status = Status.STOPPED;
        System.out.println("Elevator " + id + " stopped at floor " + floor);
        openDoor();
        closeDoor();
    }

    private void openDoor() {
        System.out.println("Door opened");
    }

    private void closeDoor() {
        System.out.println("Door closed");
    }
}
