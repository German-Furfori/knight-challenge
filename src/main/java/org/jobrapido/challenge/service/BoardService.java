package org.jobrapido.challenge.service;

import lombok.RequiredArgsConstructor;
import org.jobrapido.challenge.dto.input.BoardDto;
import org.jobrapido.challenge.exception.InvalidStartPositionException;
import org.jobrapido.challenge.mapper.BoardMapper;
import org.jobrapido.challenge.model.Board;
import org.jobrapido.challenge.model.Position;

@RequiredArgsConstructor
public class BoardService {

    private final DataFetcherService dataFetcherService;

    private final BoardMapper boardMapper;

    private static final String INVALID_START_POSITION = "INVALID_START_POSITION";

    public Board getBoard() {
        BoardDto response = dataFetcherService.getBoard("BOARD_API");

        return boardMapper.mapBoardDtoToBoard(response);
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

}
