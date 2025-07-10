package org.jobrapido.challenge.service;

import org.jobrapido.challenge.MainTest;
import org.jobrapido.challenge.dto.output.ResultDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightServiceTest extends MainTest {


    BoardService boardService = Mockito.mock(BoardService.class);

    CommandsService commandsService = Mockito.mock(CommandsService.class);

    KnightService knightService = new KnightService(boardService, commandsService);

    @Test
    void executeKnightMovements_withoutGettingObstacles_expectCorrectOutput() {
        Mockito.when(boardService.getBoard()).thenReturn(this.getBoard(
                5, 5, Set.of("20", "21", "22", "23")
        ));
        Mockito.when(commandsService.getCommands()).thenReturn(this.getCommands(
                List.of("START 0,0,NORTH",
                        "MOVE 4",
                        "ROTATE EAST",
                        "MOVE 4",
                        "ROTATE SOUTH",
                        "MOVE 4")
        ));

        ResultDto result = this.knightService.moveKnight();

        assertEquals(4, result.getPosition().getX());
        assertEquals(0, result.getPosition().getY());
        assertEquals("SOUTH", result.getPosition().getDirection());
        assertEquals("SUCCESS", result.getStatus());
    }

    @Test
    void executeKnightMovements_gettingObstacles_expectCorrectOutput() {
        Mockito.when(boardService.getBoard()).thenReturn(this.getBoard(
                10, 10,
                Set.of("08", "18", "17", "16", "15", "14", "22", "32", "42", "54",
                        "53", "52", "51", "64", "61", "74", "84", "81", "94", "91")
        ));
        Mockito.when(commandsService.getCommands()).thenReturn(this.getCommands(
                List.of("START 3,7,SOUTH",
                        "MOVE 4",
                        "ROTATE WEST",
                        "MOVE 2",
                        "ROTATE SOUTH",
                        "MOVE 2",
                        "ROTATE EAST",
                        "MOVE 10",
                        "ROTATE SOUTH",
                        "MOVE 1",
                        "ROTATE EAST",
                        "MOVE 3",
                        "ROTATE NORTH",
                        "MOVE 8")
        ));

        ResultDto result = this.knightService.moveKnight();

        assertEquals(7, result.getPosition().getX());
        assertEquals(3, result.getPosition().getY());
        assertEquals("NORTH", result.getPosition().getDirection());
        assertEquals("SUCCESS", result.getStatus());
    }

}
