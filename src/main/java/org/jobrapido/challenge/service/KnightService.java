package org.jobrapido.challenge.service;

import lombok.RequiredArgsConstructor;
import org.jobrapido.challenge.dto.output.PositionDto;
import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.enums.DirectionEnum;
import org.jobrapido.challenge.enums.StatusEnum;
import org.jobrapido.challenge.model.Board;
import org.jobrapido.challenge.model.Commands;
import org.jobrapido.challenge.model.Point;
import java.util.List;

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
        List<String> commandList = commands.getCommands();

        PositionDto position = this.validateStartingPoint(commands.getCommands().get(0), board);

        for (int i = 1; i < commandList.size(); i++) {
            position = this.makeMovement(commandList.get(i), position, board);
        }

        return new ResultDto(position, StatusEnum.SUCCESS);
    }

    private PositionDto validateStartingPoint(String startingPoint, Board board) {
        String[] xy = startingPoint.split(" ")[1].split(",");
        Point point = new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));

        if (point.getX() >= board.getWidth() || point.getY() >= board.getHeight()
        || point.getX() < 0 || point.getY() < 0) this.invalidStartPosition();
        if (!this.isNotObstacle(point.getX(), point.getY(), board)) this.invalidStartPosition();

        return new PositionDto(point.getX(), point.getY(), DirectionEnum.valueOf(xy[2]));
    }

    private PositionDto makeMovement(String command, PositionDto position, Board board) {
        String[] movement = command.split(" ");

        if ("ROTATE".equals(movement[0])) {
            position.setDirection(DirectionEnum.valueOf(movement[1]));
        } else {
            int moves = Integer.parseInt(movement[1]);
            for (int i = 0; i < moves; i++) {
                switch (position.getDirection()) {
                    case SOUTH:
                        if (position.getY() - 1 < 0) this.outOfTheBoard();
                        if (this.isNotObstacle(position.getX(), position.getY() - 1, board)) position.setY(position.getY() - 1);
                        break;
                    case NORTH:
                        if (position.getY() + 1 >= board.getHeight()) this.outOfTheBoard();
                        if (this.isNotObstacle(position.getX(), position.getY() + 1, board)) position.setY(position.getY() + 1);
                        break;
                    case WEST:
                        if (position.getX() - 1 < 0) this.outOfTheBoard();
                        if (this.isNotObstacle(position.getX() - 1, position.getY(), board)) position.setX(position.getX() - 1);
                        break;
                    case EAST:
                        if (position.getX() + 1 >= board.getWidth()) this.outOfTheBoard();
                        if (this.isNotObstacle(position.getX() + 1, position.getY(), board)) position.setX(position.getX() + 1);
                }
            }
        }

        return position;
    }

    private Boolean isNotObstacle(Integer x, Integer y, Board board) {
        return !board.getObstacles().contains(String.format("%d%d", x, y));
    }

    private void outOfTheBoard() {
        System.out.println(new ResultDto(StatusEnum.OUT_OF_THE_BOARD));
        System.exit(1);
    }

    private void invalidStartPosition() {
        System.out.println(new ResultDto(StatusEnum.INVALID_START_POSITION));
        System.exit(1);
    }

}
