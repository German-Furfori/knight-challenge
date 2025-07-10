package org.jobrapido.challenge;

import org.jobrapido.challenge.model.Board;
import org.jobrapido.challenge.model.Point;

import java.util.Set;

public abstract class MainTest {

    protected Board getBoard() {
        return new Board(5, 5, Set.of("20", "21", "22", "23"));
    }

}
