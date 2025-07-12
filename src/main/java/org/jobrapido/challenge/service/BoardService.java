package org.jobrapido.challenge.service;

import lombok.RequiredArgsConstructor;
import org.jobrapido.challenge.dto.input.BoardDto;
import org.jobrapido.challenge.dto.input.PointDto;
import org.jobrapido.challenge.exception.InvalidStartPositionException;
import org.jobrapido.challenge.model.Board;
import org.jobrapido.challenge.model.Position;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class BoardService {

    private final DataFetcherService dataFetcherService;

    private static final String INVALID_START_POSITION = "INVALID_START_POSITION";

    public Board getBoard() {
        BoardDto response = dataFetcherService.getBoard("BOARD_API");

        return this.mapBoardDtoToBoard(response);
    }

    public Position validateStartingPoint(Position position, Board board) {
        if (this.isOutOfTheBoard(position, board)) throw new InvalidStartPositionException(INVALID_START_POSITION);
        if (!this.isNotObstacle(position.getX(), position.getY(), board)) throw new InvalidStartPositionException(INVALID_START_POSITION);

        return position;
    }

    public boolean isNotObstacle(Integer x, Integer y, Board board) {
        return !board.getObstacles().contains(String.format("%d%d", x, y));
    }

    private boolean isOutOfTheBoard(Position position, Board board) {
        return position.getX() >= board.getWidth() || position.getY() >= board.getHeight()
                || position.getX() < 0 || position.getY() < 0;
    }

    private Board mapBoardDtoToBoard(BoardDto boardDto) {
        Set<String> obstacles = new HashSet<>();

        for (PointDto point : boardDto.getObstacles()) {
            obstacles.add(String.format("%d%d", point.getX(), point.getY()));
        }

        return new Board(
                boardDto.getHeight(),
                boardDto.getWidth(),
                obstacles
        );
    }

}
