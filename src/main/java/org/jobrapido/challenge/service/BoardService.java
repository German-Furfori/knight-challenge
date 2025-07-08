package org.jobrapido.challenge.service;

import org.jobrapido.challenge.dto.input.BoardDto;
import org.jobrapido.challenge.dto.input.PointDto;
import org.jobrapido.challenge.model.Board;
import org.jobrapido.challenge.model.Point;
import org.jobrapido.challenge.utils.DataFetcherUtils;
import java.util.HashSet;
import java.util.Set;

import static org.jobrapido.challenge.utils.JsonUtils.GSON;

public class BoardService {

    public Board getBoard() {
        String response = DataFetcherUtils.getData("https://storage.googleapis.com/jobrapido-backend-test/board.json");
        BoardDto board = GSON.fromJson(response, BoardDto.class);

        return this.mapBoardDtoToBoard(board);
    }

    private Board mapBoardDtoToBoard(BoardDto boardDto) {
        Set<Point> obstacles = new HashSet<>();

        for (PointDto point : boardDto.getObstacles()) {
            obstacles.add(new Point(
                    point.getX(),
                    point.getY()
            ));
        }

        return new Board(
                boardDto.getHeight(),
                boardDto.getWidth(),
                obstacles
        );
    }

}
