package org.jobrapido.challenge.dto.output;

import lombok.AllArgsConstructor;
import org.jobrapido.challenge.enums.DirectionEnum;

@AllArgsConstructor
public class FinalPositionDto {

    private Integer x;

    private Integer y;

    private DirectionEnum direction;

}
