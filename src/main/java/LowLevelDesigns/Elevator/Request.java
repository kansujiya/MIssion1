package LowLevelDesigns.Elevator;


enum Direction {
    UP, DOWN, IDLE
}

enum Status {
    MOVING, STOPPED
}

class Request {
    int sourceFloor;
    int destinationFloor;
    Direction direction;

    public Request(int source, int dest) {
        this.sourceFloor = source;
        this.destinationFloor = dest;
        this.direction = dest > source ? Direction.UP : Direction.DOWN;
    }
}
