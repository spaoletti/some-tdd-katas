package org.tdd.katas.marsrover;

public class Position {

    private final Coordinates coordinates;
    private final char direction;
    private boolean blocked;

    public Position(Coordinates coordinates, char direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public char getDirection() {
        return direction;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        String blocked = isBlocked() ? "0:" : "";
        return blocked + getCoordinates().x() + ":" + getCoordinates().y() + ":" + getDirection();
    }

}
