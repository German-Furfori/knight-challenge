package org.jobrapido.challenge;

import org.jobrapido.challenge.model.Board;
import org.jobrapido.challenge.model.Commands;
import org.jobrapido.challenge.model.Point;

import java.util.List;
import java.util.Set;

public abstract class MainTest {

    protected Board getBoard() {
        return new Board(5, 5, Set.of("20", "21", "22", "23"));
    }

    protected Commands getCommands() {
        return new Commands(List.of(
                "START 0,0,NORTH",
                "MOVE 4",
                "ROTATE EAST",
                "MOVE 4",
                "ROTATE SOUTH",
                "MOVE 4"
        ));
    }
}
