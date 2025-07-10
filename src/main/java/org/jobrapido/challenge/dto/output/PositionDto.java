package org.jobrapido.challenge.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jobrapido.challenge.enums.DirectionEnum;

@Data
@AllArgsConstructor
public class PositionDto {

    private Integer x;

    private Integer y;

    private DirectionEnum direction;

}
