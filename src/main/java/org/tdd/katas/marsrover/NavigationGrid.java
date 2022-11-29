package org.tdd.katas.marsrover;

import java.util.*;

public class NavigationGrid {

    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    private final String DIRECTIONS = "NESW";
    private final Map<Character, List<Integer>> movements = new HashMap<>();
    private List<Coordinates> obstacles = new ArrayList<>();
    private Position p;

    public NavigationGrid(Position initialPosition) {
        this.p = initialPosition;
        movements.put('N', Arrays.asList(0, 1));
        movements.put('E', Arrays.asList(1, 0));
        movements.put('S', Arrays.asList(0, -1));
        movements.put('W', Arrays.asList(-1, 0));
    }

    public NavigationGrid(Position initialPosition, List<Coordinates> obstacles) {
        this(initialPosition);
        this.obstacles = obstacles;
    }

    public Position getPosition() {
        return p;
    }

    public void turnLeft() {
        turn(LEFT);
    }

    public void turnRight() {
        turn(RIGHT);
    }

    private void turn(int d) {
        int newDir = wrapAround(DIRECTIONS.indexOf(p.getDirection()) + d, DIRECTIONS.length());
        p = new Position(
                new Coordinates(p.getCoordinates().x(), p.getCoordinates().y()),
                DIRECTIONS.charAt(newDir)
        );
    }

    public void move() {
        List<Integer> movement = movements.get(p.getDirection());
        Coordinates newCoords = new Coordinates(
                wrapAround(p.getCoordinates().x() + movement.get(0), WIDTH),
                wrapAround(p.getCoordinates().y() + movement.get(1), HEIGHT)
        );
        Position nextPosition = new Position(
                newCoords,
                p.getDirection()
        );
        if (isAnObstacle(nextPosition)) {
            p.setBlocked(true);
        } else {
            p = nextPosition;
        }
    }

    private int wrapAround(int n, int l) {
        if (n < 0) {
            return l - 1;
        } else if (n >= l) {
            return  0;
        }
        return n;
    }

    private boolean isAnObstacle(Position p) {
        return obstacles.contains(p.getCoordinates());
    }

}
