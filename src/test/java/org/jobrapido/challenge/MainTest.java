package org.jobrapido.challenge;

import org.jobrapido.challenge.dto.input.BoardDto;
import org.jobrapido.challenge.dto.input.CommandsDto;
import org.jobrapido.challenge.dto.input.PointDto;
import java.util.List;

public abstract class MainTest {

    protected BoardDto getBoard(Integer x, Integer y, List<PointDto> obstacles) {
        return new BoardDto(x, y, obstacles);
    }

    protected CommandsDto getCommands(List<String> commands) {
        return new CommandsDto(commands);
    }
}
