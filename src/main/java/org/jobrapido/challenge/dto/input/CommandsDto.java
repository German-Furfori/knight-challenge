package org.jobrapido.challenge.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CommandsDto {

    private List<String> commands;

}
