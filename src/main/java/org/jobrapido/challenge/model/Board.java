package org.jobrapido.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Board {

    private Integer width;

    private Integer height;

    private Set<Point> obstacles;

}
