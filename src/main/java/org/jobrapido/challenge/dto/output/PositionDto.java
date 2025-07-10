package org.jobrapido.challenge.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PositionDto {

    private Integer x;

    private Integer y;

    private String direction;

}
