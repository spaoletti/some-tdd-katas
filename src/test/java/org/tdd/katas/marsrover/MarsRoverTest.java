package org.tdd.katas.marsrover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarsRoverTest {

    private MarsRover mr;

    @BeforeEach
    public void beforeEach() {
        mr = MarsRoverFactory.create(0, 0, 'N');
    }

    @Test
    public void when_a_null_command_is_given_it_throws_an_exception() {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> mr.execute(null)
        );
        assertEquals("Command is null.", e.getMessage());
    }

    @Test
    public void when_the_rover_receives_an_unknown_command_it_throws_an_exception() {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> mr.execute("X")
        );
        assertEquals("Unknown command: X", e.getMessage());
    }

    @Test
    public void when_the_rover_is_facing_NORTH_and_receive_LEFT_command_it_turns_WEST() {
        String outcome = mr.execute("L");
        Assertions.assertEquals("0:0:W", outcome);
    }

    @Test
    public void when_the_rover_is_facing_WEST_and_receive_LEFT_command_it_turns_SOUTH() {
        mr = MarsRoverFactory.create(0, 0, 'W');
        String outcome = mr.execute("L");
        Assertions.assertEquals("0:0:S", outcome);
    }

    @Test
    public void when_the_rover_is_facing_SOUTH_and_receive_LEFT_command_it_turns_EAST() {
        mr = MarsRoverFactory.create(0, 0, 'S');
        String outcome = mr.execute("L");
        Assertions.assertEquals("0:0:E", outcome);
    }

    @Test
    public void when_the_rover_is_facing_EAST_and_receive_LEFT_command_it_turns_NORTH() {
        mr = MarsRoverFactory.create(0, 0, 'E');
        String outcome = mr.execute("L");
        Assertions.assertEquals("0:0:N", outcome);
    }

    @Test
    public void when_the_rover_is_facing_NORTH_and_receive_RIGHT_command_it_turns_EAST() {
        String outcome = mr.execute("R");
        Assertions.assertEquals("0:0:E", outcome);
    }

    @Test
    public void when_the_rover_is_facing_EAST_and_receive_RIGHT_command_it_turns_SOUTH() {
        mr = MarsRoverFactory.create(0, 0, 'E');
        String outcome = mr.execute("R");
        Assertions.assertEquals("0:0:S", outcome);
    }

    @Test
    public void when_the_rover_is_facing_SOUTH_and_receive_RIGHT_command_it_turns_WEST() {
        mr = MarsRoverFactory.create(0, 0, 'S');
        String outcome = mr.execute("R");
        Assertions.assertEquals("0:0:W", outcome);
    }

    @Test
    public void when_the_rover_is_facing_WEST_and_receive_RIGHT_command_it_turns_NORTH() {
        mr = MarsRoverFactory.create(0, 0, 'W');
        String outcome = mr.execute("R");
        Assertions.assertEquals("0:0:N", outcome);
    }

    @Test
    public void when_the_command_M_is_given_the_rover_advance_toward_NORTH_if_its_facing_NORTH() {
        String outcome = mr.execute("M");
        Assertions.assertEquals("0:1:N", outcome);
    }

    @Test
    public void when_the_command_M_is_given_the_rover_advance_toward_EAST_if_its_facing_EAST() {
        mr = MarsRoverFactory.create(0, 0, 'E');
        String outcome = mr.execute("M");
        Assertions.assertEquals("1:0:E", outcome);
    }

    @Test
    public void when_the_command_M_is_given_the_rover_advance_toward_SOUTH_if_its_facing_SOUTH() {
        mr = MarsRoverFactory.create(0, 1, 'S');
        String outcome = mr.execute("M");
        Assertions.assertEquals("0:0:S", outcome);
    }

    @Test
    public void when_the_command_M_is_given_the_rover_advance_toward_WEST_if_its_facing_WEST() {
        mr = MarsRoverFactory.create(1, 0, 'W');
        String outcome = mr.execute("M");
        Assertions.assertEquals("0:0:W", outcome);
    }

    @Test
    public void when_the_command_M_is_given_if_the_rover_is_at_the_border_of_the_grid_it_wraps_around_on_the_X_axis() {
        mr = MarsRoverFactory.create(0, 0, 'W');
        String outcome = mr.execute("M");
        Assertions.assertEquals("9:0:W", outcome);
    }

    @Test
    public void when_the_command_M_is_given_if_the_rover_is_at_the_border_of_the_grid_it_wraps_around_on_the_Y_axis() {
        mr = MarsRoverFactory.create(0, 0, 'S');
        String outcome = mr.execute("M");
        Assertions.assertEquals("0:9:S", outcome);
    }

    @Test
    public void when_the_rover_receives_two_equal_commands_it_executes_them_both() {
        String outcome = mr.execute("MM");
        Assertions.assertEquals("0:2:N", outcome);
    }

    @Test
    public void when_the_rover_receives_ten_equal_commands_it_executes_them_all_and_wraps_around() {
        String outcome = mr.execute("MMMMMMMMMM");
        Assertions.assertEquals("0:0:N", outcome);
    }

    @Test
    public void when_the_rover_receives_many_different_commands_it_executes_them_all() {
        String outcome = mr.execute("MMRMMLM");
        Assertions.assertEquals("2:3:N", outcome);
    }

    @Test
    public void when_the_rover_encounters_an_obstacle_it_stops_and_prefixes_the_position_with_a_0() {
        List<Coordinates> obstacles = Arrays.asList(new Coordinates(2, 2));
        mr = MarsRoverFactory.create(0, 2, 'E', obstacles);
        String outcome = mr.execute("MMM");
        Assertions.assertEquals("0:1:2:E", outcome);
    }

}
