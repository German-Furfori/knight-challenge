package org.jobrapido.challenge.service;

import org.jobrapido.challenge.MainTest;
import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.exception.InvalidStartPositionException;
import org.jobrapido.challenge.exception.OutOfTheBoardException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KnightServiceTest extends MainTest {


    BoardService boardService = Mockito.mock(BoardService.class);

    CommandsService commandsService = Mockito.mock(CommandsService.class);

    KnightService knightService = new KnightService(boardService, commandsService);

    private static Stream<Arguments> multipleInvalidStartPositions() {
        return Stream.of(
                Arguments.of(List.of("START -1,0,NORTH", "MOVE 4")),
                Arguments.of(List.of("START 0,-1,NORTH", "MOVE 4")),
                Arguments.of(List.of("START 5,0,NORTH", "MOVE 4")),
                Arguments.of(List.of("START 0,5,NORTH", "MOVE 4")),
                Arguments.of(List.of("START 2,2,NORTH", "MOVE 4"))
        );
    }

    private static Stream<Arguments> multipleOutOfBoardMovements() {
        return Stream.of(
                Arguments.of(List.of("START 1,1,NORTH", "MOVE 10")),
                Arguments.of(List.of("START 1,1,NORTH", "ROTATE SOUTH", "MOVE 10")),
                Arguments.of(List.of("START 1,1,NORTH", "ROTATE EAST", "MOVE 10")),
                Arguments.of(List.of("START 1,1,NORTH", "ROTATE WEST", "MOVE 10"))
        );
    }

    @Test
    void executeKnightMovements_withoutGettingObstacles_expectSuccessOutput() {
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
    void executeKnightMovements_gettingObstacles_expectSuccessOutput() {
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

    @ParameterizedTest
    @MethodSource("multipleInvalidStartPositions")
    void executeKnightMovements_withMultipleInvalidStartPositions_expectInvalidStartPositionOutput(List<String> commands) {
        Mockito.when(boardService.getBoard()).thenReturn(this.getBoard(5, 5, Set.of("22")));
        Mockito.when(commandsService.getCommands()).thenReturn(this.getCommands(commands));

        InvalidStartPositionException ex = assertThrows(InvalidStartPositionException.class, () -> {
            this.knightService.moveKnight();
        });

        assertEquals("INVALID_START_POSITION", ex.getMessage());
    }

    @ParameterizedTest
    @MethodSource("multipleOutOfBoardMovements")
    void executeKnightMovements_withMultipleOutOfBoardMovements_expectOutOfTheBoardOutput(List<String> commands) {
        Mockito.when(boardService.getBoard()).thenReturn(this.getBoard(5, 5, Set.of()));
        Mockito.when(commandsService.getCommands()).thenReturn(this.getCommands(commands));

        OutOfTheBoardException ex = assertThrows(OutOfTheBoardException.class, () -> {
            this.knightService.moveKnight();
        });

        assertEquals("OUT_OF_THE_BOARD", ex.getMessage());
    }

}
