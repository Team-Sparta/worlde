package lv5.enums;

import lv5.game.Game;
import lv5.game.NumberGame;
import lv5.game.WordGame;

import java.util.Arrays;
import java.util.InputMismatchException;

public enum GameType {
    NUMBER('N', new NumberGame()), WORD('W', new WordGame());

    private final char symbol;
    private final Game game;

    GameType(char c, Game game) {
        this.symbol = c;
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }


    public static GameType fromChar(char symbol) throws InputMismatchException {
        return Arrays.stream(values())
                .filter(e -> e.symbol == Character.toUpperCase(symbol))
                .findFirst()
                .orElseThrow(() -> new InputMismatchException("잘못된 입력입니다."));
    }
}
