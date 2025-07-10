package org.jobrapido.challenge;

import org.jobrapido.challenge.model.Board;
import org.jobrapido.challenge.model.Commands;

import java.util.List;
import java.util.Set;

public abstract class MainTest {

    protected Board getBoard(Integer x, Integer y, Set<String> obstacles) {
        return new Board(x, y, obstacles);
    }

    protected Commands getCommands(List<String> commands) {
        return new Commands(commands);
    }
}
