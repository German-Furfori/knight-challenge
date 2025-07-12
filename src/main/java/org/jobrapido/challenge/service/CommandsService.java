package org.jobrapido.challenge.service;

import lombok.RequiredArgsConstructor;
import org.jobrapido.challenge.dto.input.CommandsDto;
import org.jobrapido.challenge.exception.OutOfTheBoardException;
import org.jobrapido.challenge.mapper.CommandsMapper;
import org.jobrapido.challenge.model.Board;
import org.jobrapido.challenge.model.Commands;
import org.jobrapido.challenge.model.Command;
import org.jobrapido.challenge.model.Position;

@RequiredArgsConstructor
public class CommandsService {

    private final DataFetcherService dataFetcherService;

    private final BoardService boardService;

    private final CommandsMapper commandsMapper;

    private static final String OUT_OF_THE_BOARD = "OUT_OF_THE_BOARD";

    public Commands getCommands() {
        CommandsDto response = dataFetcherService.getCommands("COMMANDS_API");

        return commandsMapper.mapCommandsDtoToCommands(response);
    }

    public void executeCommand(Command command, Position position, Board board) {
        boolean thereIsNoObstacle = true;

        if ("ROTATE".equals(command.getName())) {
            position.setDirection(command.getAction());
        } else {
            int moves = Integer.parseInt(command.getAction());
            for (int i = 0; i < moves && thereIsNoObstacle; i++) {
                switch (position.getDirection()) {
                    case "SOUTH":
                        thereIsNoObstacle = this.moveSouth(position, board);
                        break;
                    case "NORTH":
                        thereIsNoObstacle = this.moveNorth(position, board);
                        break;
                    case "WEST":
                        thereIsNoObstacle = this.moveWest(position, board);
                        break;
                    case "EAST":
                        thereIsNoObstacle = this.moveEast(position, board);
                }
            }
        }
    }

    private boolean moveSouth(Position position, Board board) {
        if (position.getY() - 1 < 0) throw new OutOfTheBoardException(OUT_OF_THE_BOARD);
        if (boardService.isNotObstacle(position.getX(), position.getY() - 1, board)) {
            position.setY(position.getY() - 1);
            return true;
        } else {
            return false;
        }
    }

    private boolean moveNorth(Position position, Board board) {
        if (position.getY() + 1 >= board.getHeight()) throw new OutOfTheBoardException(OUT_OF_THE_BOARD);
        if (boardService.isNotObstacle(position.getX(), position.getY() + 1, board)) {
            position.setY(position.getY() + 1);
            return true;
        } else {
            return false;
        }
    }

    private boolean moveWest(Position position, Board board) {
        if (position.getX() - 1 < 0) throw new OutOfTheBoardException(OUT_OF_THE_BOARD);
        if (boardService.isNotObstacle(position.getX() - 1, position.getY(), board)) {
            position.setX(position.getX() - 1);
            return true;
        } else {
            return false;
        }
    }

    private boolean moveEast(Position position, Board board) {
        if (position.getX() + 1 >= board.getWidth()) throw new OutOfTheBoardException(OUT_OF_THE_BOARD);
        if (boardService.isNotObstacle(position.getX() + 1, position.getY(), board)) {
            position.setX(position.getX() + 1);
            return true;
        } else {
            return false;
        }
    }

}
