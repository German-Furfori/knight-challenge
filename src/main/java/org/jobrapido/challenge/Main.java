package org.jobrapido.challenge;

import org.jobrapido.challenge.service.BoardService;
import org.jobrapido.challenge.service.CommandsService;
import org.jobrapido.challenge.service.KnightService;

public class Main {

    private static final KnightService knightService = new KnightService(
            new BoardService(), new CommandsService()
    );

    public static void main(String[] args) {
        System.out.println(knightService.moveKnight());
    }
}