package org.jobrapido.challenge.service;

import org.jobrapido.challenge.model.Commands;
import org.jobrapido.challenge.utils.DataFetcherUtils;

import static org.jobrapido.challenge.utils.JsonUtils.GSON;

public class CommandsService {

    public Commands getCommands() {
        String response = DataFetcherUtils.getData("COMMANDS_API");
        return GSON.fromJson(response, Commands.class);
    }

}
