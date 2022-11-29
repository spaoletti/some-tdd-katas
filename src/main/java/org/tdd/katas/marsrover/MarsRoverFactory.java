package org.tdd.katas.marsrover;

import java.util.List;

public class MarsRoverFactory {

    public static MarsRover create(int initialX, int initialY, char initialDirection) {
        return new MarsRover(
                new NavigationGrid(
                        new Position(
                                new Coordinates(initialX, initialY),
                                initialDirection
                        )
                )
        );
    }

    public static MarsRover create(int initialX, int initialY, char initialDirection, List<Coordinates> obstacles) {
        return new MarsRover(
                new NavigationGrid(
                        new Position(
                                new Coordinates(initialX, initialY),
                                initialDirection
                        ),
                        obstacles
                )
        );
    }

}
