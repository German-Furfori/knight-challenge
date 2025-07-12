package org.jobrapido.challenge.mapper;

import org.jobrapido.challenge.dto.input.CommandsDto;
import org.jobrapido.challenge.model.Command;
import org.jobrapido.challenge.model.Commands;
import org.jobrapido.challenge.model.Position;
import java.util.ArrayList;
import java.util.List;

public class CommandsMapper {

    public Commands mapCommandsDtoToCommands(CommandsDto commandsDto) {
        String[] startingPosition = commandsDto.getCommands().get(0).split(" ")[1].split(",");
        Position position = new Position(
                Integer.parseInt(startingPosition[0]),
                Integer.parseInt(startingPosition[1]),
                startingPosition[2]
        );

        List<Command> commands = new ArrayList<>();
        for (int i = 1; i < commandsDto.getCommands().size(); i++) {
            Command command = new Command(
                    commandsDto.getCommands().get(i).split(" ")[0],
                    commandsDto.getCommands().get(i).split(" ")[1]
            );

            commands.add(command);
        }

        return new Commands(position, commands);
    }

}
