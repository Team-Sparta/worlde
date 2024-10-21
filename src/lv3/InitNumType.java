package lv3;

import java.util.Arrays;
import java.util.InputMismatchException;

public enum InitNumType {
    START('1'), RECORD('2'), EXIT('3');

    private final char initNum;

    InitNumType(char c) {
        this.initNum = c;
    }

    public int getInitNum() {
        return initNum;
    }

    public static InitNumType fromChar(char initNum) throws InputMismatchException {
        return Arrays.stream(values())
                .filter(e -> e.initNum == initNum)
                .findFirst()
                .orElseThrow(() -> new InputMismatchException("잘못된 입력입니다. 0~3 사이의 숫자를 입력해주세요."));
    }

}
