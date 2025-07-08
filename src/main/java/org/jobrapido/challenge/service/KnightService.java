package org.jobrapido.challenge.service;

import org.jobrapido.challenge.dto.output.FinalPositionDto;
import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.enums.DirectionEnum;
import org.jobrapido.challenge.enums.StatusEnum;
import org.jobrapido.challenge.model.Board;

import static org.jobrapido.challenge.utils.JsonUtils.GSON;

public class KnightService {

    private final BoardService boardService = new BoardService();

    public String execute() {
        Board board = boardService.getBoard();

        FinalPositionDto finalPosition = new FinalPositionDto(
                1,
                1,
                DirectionEnum.SOUTH
        );

        ResultDto result = new ResultDto(
                finalPosition,
                StatusEnum.SUCCESS
        );

        return GSON.toJson(result);
    }

}
