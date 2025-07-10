package org.jobrapido.challenge.service;

import org.jobrapido.challenge.model.Commands;
import org.jobrapido.challenge.utils.DataFetcherUtils;

import static org.jobrapido.challenge.utils.JsonUtils.GSON;

public class CommandsService {

    public Commands getCommands() {
        String response = DataFetcherUtils.getData("https://storage.googleapis.com/jobrapido-backend-test/commands.json");
        return GSON.fromJson(response, Commands.class);
    }

}
