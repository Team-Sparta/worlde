package lv5.enums;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MenuType {
    LEVEL('0'), START('1'), RECORD('2'), EXIT('3');

    private final char symbol;

    private static final Map<Character, MenuType> MENU_TYPE_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(MenuType::getSymbol, Function.identity())));

    MenuType(char c) {
        this.symbol = c;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public static MenuType fromChar(char symbol) throws InputMismatchException {
        MenuType menuType = MENU_TYPE_MAP.get(symbol);
        if (menuType == null) {
            throw new InputMismatchException("잘못된 입력입니다. 0~3 사이의 숫자를 입력해주세요.");
        }
        return menuType;
    }

}
