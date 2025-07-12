package org.jobrapido.challenge.service;

import org.jobrapido.challenge.MainTest;
import org.jobrapido.challenge.dto.input.PointDto;
import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.exception.InvalidStartPositionException;
import org.jobrapido.challenge.exception.OutOfTheBoardException;
import org.jobrapido.challenge.mapper.BoardMapper;
import org.jobrapido.challenge.mapper.CommandsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class KnightServiceTest extends MainTest {

    DataFetcherService dataFetcherService = Mockito.mock(DataFetcherService.class);

    BoardMapper boardMapper = new BoardMapper();

    CommandsMapper commandsMapper = new CommandsMapper();

    BoardService boardService = new BoardService(dataFetcherService, boardMapper);

    CommandsService commandsService = new CommandsService(dataFetcherService, boardService, commandsMapper);

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
        Mockito.when(dataFetcherService.getBoard(any(String.class))).thenReturn(this.getBoard(
                5, 5, List.of(
                        new PointDto(2, 0), new PointDto(2, 1),
                        new PointDto(2, 2), new PointDto(2, 3))
        ));
        Mockito.when(dataFetcherService.getCommands(any(String.class))).thenReturn(this.getCommands(
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
        Mockito.when(dataFetcherService.getBoard(any(String.class))).thenReturn(this.getBoard(
                10, 10, List.of(
                        new PointDto(0, 8), new PointDto(1, 8), new PointDto(1, 7),
                        new PointDto(1, 6), new PointDto(1, 5), new PointDto(1, 4),
                        new PointDto(2, 2), new PointDto(3, 2), new PointDto(4, 2),
                        new PointDto(5, 4), new PointDto(5, 3), new PointDto(5, 2),
                        new PointDto(5, 1), new PointDto(6, 4), new PointDto(6, 1),
                        new PointDto(7, 4), new PointDto(8, 4), new PointDto(8, 1),
                        new PointDto(9, 4), new PointDto(9, 1)
                )
        ));
        Mockito.when(dataFetcherService.getCommands(any(String.class))).thenReturn(this.getCommands(
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
        Mockito.when(dataFetcherService.getBoard(any(String.class)))
                .thenReturn(this.getBoard(5, 5, List.of(new PointDto(2, 2))));
        Mockito.when(dataFetcherService.getCommands(any(String.class)))
                .thenReturn(this.getCommands(commands));

        InvalidStartPositionException ex =
                assertThrows(InvalidStartPositionException.class, () -> this.knightService.moveKnight());

        assertEquals("INVALID_START_POSITION", ex.getMessage());
    }

    @ParameterizedTest
    @MethodSource("multipleOutOfBoardMovements")
    void executeKnightMovements_withMultipleOutOfBoardMovements_expectOutOfTheBoardOutput(List<String> commands) {
        Mockito.when(dataFetcherService.getBoard(any(String.class)))
                .thenReturn(this.getBoard(5, 5, List.of()));
        Mockito.when(dataFetcherService.getCommands(any(String.class)))
                .thenReturn(this.getCommands(commands));

        OutOfTheBoardException ex = assertThrows(OutOfTheBoardException.class, () -> {
            this.knightService.moveKnight();
        });

        assertEquals("OUT_OF_THE_BOARD", ex.getMessage());
    }

}
