package lv5.enums;

import lv5.game.Game;
import lv5.game.bot.NumberGameWithBot;
import lv5.game.bot.WordGameWithBot;
import lv5.game.solo.SoloNumberGame;
import lv5.game.solo.SoloWordGame;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GameType {
    NUMBER('N', new SoloNumberGame(), "숫자"), WORD('W', new SoloWordGame(), "영단어"),
    AI('A', new NumberGameWithBot(), "AI와 대결 하는 숫자"), BI('B', new WordGameWithBot(), "AI와 대결 하는 영단어");

    private final char symbol;
    private final Game game;
    private final String name;

    private static final Map<Character, GameType> MENU_TYPE_MAP =
            Collections.unmodifiableMap(Stream.of(values()).sequential()
                    .collect(Collectors.toMap(GameType::getSymbol, Function.identity())));

    GameType(char c, Game game, String name) {
        this.symbol = c;
        this.game = game;
        this.name = name;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public Game getGame() {
        return this.game;
    }

    public String getName() {
        return this.name;
    }

    public static GameType fromChar(char symbol) throws InputMismatchException {
        GameType gameType = MENU_TYPE_MAP.get(symbol);
        if (gameType == null) {
            throw new InputMismatchException("Invalid symbol: " + symbol);
        }
        return gameType;
    }
}
