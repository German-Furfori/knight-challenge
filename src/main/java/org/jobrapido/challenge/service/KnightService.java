package org.jobrapido.challenge.service;

import lombok.RequiredArgsConstructor;
import org.jobrapido.challenge.dto.output.FinalPositionDto;
import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.enums.DirectionEnum;
import org.jobrapido.challenge.enums.StatusEnum;
import org.jobrapido.challenge.model.Board;
import org.jobrapido.challenge.model.Commands;

@RequiredArgsConstructor
public class KnightService {

    private final BoardService boardService;

    private final CommandsService commandsService;

    public ResultDto moveKnight() {
        Board board = boardService.getBoard();
        Commands commands = commandsService.getCommands();

        return this.executeCommands(board, commands);
    }

    private ResultDto executeCommands(Board board, Commands commands) {
        FinalPositionDto finalPosition = new FinalPositionDto();
        finalPosition.setX(1);
        finalPosition.setY(1);
        finalPosition.setDirection(DirectionEnum.SOUTH);

        return new ResultDto(
                finalPosition,
                StatusEnum.SUCCESS
        );
    }

}
