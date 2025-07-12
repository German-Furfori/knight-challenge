package org.jobrapido.challenge.service;

import lombok.RequiredArgsConstructor;
import org.jobrapido.challenge.dto.output.PositionDto;
import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.model.Board;
import org.jobrapido.challenge.model.Commands;
import org.jobrapido.challenge.model.Command;
import org.jobrapido.challenge.model.Position;

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
        Position knightPosition = boardService.validateStartingPoint(commands.getStartingPosition(), board);

        for (Command command : commands.getCommands()) {
            commandsService.executeCommand(command, knightPosition, board);
        }

        PositionDto positionDto = new PositionDto(
                knightPosition.getX(), knightPosition.getY(), knightPosition.getDirection()
        );

        return new ResultDto(positionDto, "SUCCESS");
    }

}
