package org.jobrapido.challenge;

import org.jobrapido.challenge.dto.output.ResultDto;
import org.jobrapido.challenge.service.BoardService;
import org.jobrapido.challenge.service.CommandsService;
import org.jobrapido.challenge.service.KnightService;

public class Main {

    private static final KnightService knightService = new KnightService(
            new BoardService(), new CommandsService()
    );

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