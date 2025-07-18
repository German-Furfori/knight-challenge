package org.jobrapido.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Commands {

    private Position startingPosition;

    private List<Command> commands;

}
