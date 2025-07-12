package org.jobrapido.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {

    private Integer x;

    private Integer y;

    private String direction;

}
