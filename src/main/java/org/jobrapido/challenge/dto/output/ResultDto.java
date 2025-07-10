package org.jobrapido.challenge.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jobrapido.challenge.enums.StatusEnum;

import static org.jobrapido.challenge.utils.JsonUtils.GSON;

@Data
@AllArgsConstructor
public class ResultDto {

    private PositionDto position;

    private StatusEnum status;

    public ResultDto(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

}
