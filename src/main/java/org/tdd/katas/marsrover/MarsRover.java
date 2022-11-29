package org.tdd.katas.marsrover;

public class MarsRover {

    public static final char TURN_LEFT = 'L';
    public static final char TURN_RIGHT = 'R';
    public static final char MOVE = 'M';
    private final NavigationGrid navigationGrid;

    public MarsRover(NavigationGrid navigationGrid) {
        this.navigationGrid = navigationGrid;
    }

    public String execute(String command) {
        if (command == null) {
            throw new IllegalArgumentException("Command is null.");
        }
        commands:
        for (char c : command.toCharArray()) {
            switch (c) {
                case TURN_LEFT:
                    navigationGrid.turnLeft();
                    break;
                case TURN_RIGHT:
                    navigationGrid.turnRight();
                    break;
                case MOVE:
                    navigationGrid.move();
                    if (navigationGrid.getPosition().isBlocked()) {
                        break commands;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown command: " + c);
            }
        }
        return navigationGrid.getPosition().toString();
    }

}
