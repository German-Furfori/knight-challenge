package org.jobrapido.challenge;

import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.mapper.BoardMapper;
import org.jobrapido.challenge.mapper.CommandsMapper;
import org.jobrapido.challenge.service.BoardService;
import org.jobrapido.challenge.service.CommandsService;
import org.jobrapido.challenge.service.DataFetcherService;
import org.jobrapido.challenge.service.KnightService;

public class Main {

    private static final DataFetcherService dataFetcherService = new DataFetcherService();

    private static final BoardMapper boardMapper = new BoardMapper();

    private static final CommandsMapper commandsMapper = new CommandsMapper();

    private static final BoardService boardService = new BoardService(dataFetcherService, boardMapper);

    private static final CommandsService commandsService = new CommandsService(dataFetcherService, boardService, commandsMapper);

    private static final KnightService knightService = new KnightService(boardService, commandsService);

    public static void main(String[] args) {
        ResultDto result;
        try {
            result = knightService.moveKnight();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(new ResultDto(e.getMessage()));
        }
    }
}