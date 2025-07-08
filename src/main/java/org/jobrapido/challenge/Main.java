package org.jobrapido.challenge;

import org.jobrapido.challenge.service.KnightService;

public class Main {

    private static final KnightService knightService = new KnightService();

    public static void main(String[] args) {
        System.out.println(knightService.execute());
    }
}