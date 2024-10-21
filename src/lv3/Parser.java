package lv3;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class Parser {
    private static final String INIT_NUM__REGEX = "^[1-3]$";
    private static final String NUMBER_REGEX = "^-?\\d+$";
    private static final String ZERO_REGEX = ".*0.*"; // This matches any string containing at least one '0'
    private static final String REPETITIVE_DIGIT_REGEX = ".*(\\d).*(\\1).*"; // This matches any digit followed later by itself


    public static InitNumType parseInitNum(String numInput) throws InputMismatchException {
        if (!Pattern.matches(INIT_NUM__REGEX, numInput)) {
            throw new InputMismatchException("잘못된 입력입니다. 0~3 사이의 숫자를 입력해주세요.");
        }
        return InitNumType.fromChar(numInput.charAt(0));
    }

    public static int parseGuessNum(String numInput) throws InputMismatchException {
        if (numInput.length() != 3) {
            throw new InputMismatchException("자릿수가 맞지않습니다. 3자리 수를 입력해주세요");
        }
        if (!Pattern.matches(NUMBER_REGEX, numInput)) {
            throw new InputMismatchException("잘못된 입력입니다. 숫자를 입력해주세요.");
        }
        if (Pattern.matches(ZERO_REGEX, numInput)) {
            throw new InputMismatchException("0은 사용할수 없습니다.");
        }
        if (Pattern.matches(REPETITIVE_DIGIT_REGEX, numInput)) {
            throw new InputMismatchException("동일한 숫자는 사용될수 없습니다.");
        }
        return Integer.parseInt(numInput);
    }
}
