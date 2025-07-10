package org.jobrapido.challenge.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;

import static org.jobrapido.challenge.utils.JsonUtils.GSON;

@Data
@AllArgsConstructor
public class ResultDto {

    private PositionDto position;

    private String status;

    public ResultDto(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

}
