package org.jobrapido.challenge.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class BoardDto {

    private Integer width;

    private Integer height;

    private List<PointDto> obstacles;

}
