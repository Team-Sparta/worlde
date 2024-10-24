package lv5.enums;

import lv5.game.Game;
import lv5.game.NumberGameWithBot;
import lv5.game.SoloNumberGame;
import lv5.game.SoloWordGame;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GameType {
    NUMBER('N', new SoloNumberGame()), WORD('W', new SoloWordGame()),
    AI('A', new NumberGameWithBot());

    private final char symbol;
    private final Game game;

    private static final Map<Character, GameType> MENU_TYPE_MAP =
            Collections.unmodifiableMap(Stream.of(values()).sequential()
                    .collect(Collectors.toMap(GameType::getSymbol, Function.identity())));

    GameType(char c, Game game) {
        this.symbol = c;
        this.game = game;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public Game getGame() {
        return this.game;
    }

    public static GameType fromChar(char symbol) throws InputMismatchException {
        GameType gameType = MENU_TYPE_MAP.get(symbol);
        if (gameType == null) {
            throw new InputMismatchException("Invalid symbol: " + symbol);
        }
        return gameType;
    }
}
