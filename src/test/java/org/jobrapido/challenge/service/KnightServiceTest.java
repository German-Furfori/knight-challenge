package org.jobrapido.challenge.service;

import org.jobrapido.challenge.MainTest;
import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.enums.DirectionEnum;
import org.jobrapido.challenge.enums.StatusEnum;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightServiceTest extends MainTest {


    BoardService boardService = Mockito.mock(BoardService.class);

    CommandsService commandsService = Mockito.mock(CommandsService.class);

    KnightService knightService = new KnightService(boardService, commandsService);

    @Test
    void executeKnightMovements_withCorrectInput_expectCorrectOutput() {
        Mockito.when(boardService.getBoard()).thenReturn(this.getBoard());

        ResultDto result = this.knightService.moveKnight();

        assertEquals(1, result.getPosition().getX());
        assertEquals(1, result.getPosition().getY());
        assertEquals(DirectionEnum.SOUTH, result.getPosition().getDirection());
        assertEquals(StatusEnum.SUCCESS, result.getStatus());
    }

}
