package org.jobrapido.challenge.service;

import com.google.gson.Gson;
import org.jobrapido.challenge.dto.output.FinalPositionDto;
import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.enums.DirectionEnum;
import org.jobrapido.challenge.enums.StatusEnum;

public class KnightService {

    private static final Gson gson = new Gson();

    public static String execute() {
        FinalPositionDto finalPosition = new FinalPositionDto(
                1,
                1,
                DirectionEnum.SOUTH
        );

        ResultDto result = new ResultDto(
                finalPosition,
                StatusEnum.SUCCESS
        );

        return gson.toJson(result);
    }

}
