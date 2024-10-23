package lv5.enums;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MenuType {
    LEVEL('0'), START('1'), RECORD('2'), EXIT('3');

    private final char symbol;

    private static final Map<Character, MenuType> MENU_TYPE_MAP = new HashMap<>();


    static {
        for (MenuType type : MenuType.values()) {
            MENU_TYPE_MAP.put(type.symbol, type);
        }
    }

    MenuType(char c) {
        this.symbol = c;
    }

    public static MenuType fromChar(char symbol) throws InputMismatchException {
        MenuType result = MENU_TYPE_MAP.get(symbol);
        if (result == null) {
            throw new InputMismatchException("잘못된 입력입니다. 0~3 사이의 숫자를 입력해주세요.");
        }
        return result;
    }

}
