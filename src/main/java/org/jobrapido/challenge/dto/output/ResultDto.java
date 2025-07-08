package org.jobrapido.challenge.dto.output;

import lombok.AllArgsConstructor;
import org.jobrapido.challenge.enums.StatusEnum;

@AllArgsConstructor
public class ResultDto {

    private FinalPositionDto position;

    private StatusEnum status;

}
