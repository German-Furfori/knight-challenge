package org.jobrapido.challenge.mapper;

import org.jobrapido.challenge.dto.input.BoardDto;
import org.jobrapido.challenge.dto.input.PointDto;
import org.jobrapido.challenge.model.Board;
import java.util.HashSet;
import java.util.Set;

public class BoardMapper {

    public Board mapBoardDtoToBoard(BoardDto boardDto) {
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
