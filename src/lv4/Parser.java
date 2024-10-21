package lv4;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class Parser {
    private static final String INIT_NUM__REGEX = "^[0-3]$";
    private static final String NUMBER_REGEX = "^-?\\d+$";
    private static final String ZERO_REGEX = ".*0.*"; // This matches any string containing at least one '0'
    private static final String REPETITIVE_DIGIT_REGEX = ".*(\\d).*(\\1).*"; // This matches any digit followed later by itself
    private static final String LEVEL_REGEX = "^[3-5]$";


    public static InitNumType parseInitNum(String input) throws InputMismatchException {
        if (!Pattern.matches(INIT_NUM__REGEX, input)) {
            throw new InputMismatchException("잘못된 입력입니다. 0~3 사이의 숫자를 입력해주세요.");
        }
        return InitNumType.fromChar(input.charAt(0));
    }

    public static int parseGuessNum(String input, int level) throws InputMismatchException {
        if (level != input.length()) {
            throw new InputMismatchException("자릿수가 맞지않습니다. " + level + "자리 수를 입력해주세요");
        }
        if (!Pattern.matches(NUMBER_REGEX, input)) {
            throw new InputMismatchException("잘못된 입력입니다. 숫자를 입력해주세요.");
        }
        if (Pattern.matches(ZERO_REGEX, input)) {
            throw new InputMismatchException("0은 사용할수 없습니다.");
        }
        if (Pattern.matches(REPETITIVE_DIGIT_REGEX, input)) {
            throw new InputMismatchException("동일한 숫자는 사용될수 없습니다.");
        }
        return Integer.parseInt(input);
    }

    public static int parseLevel(String input) {
        if (!Pattern.matches(LEVEL_REGEX, input)) {
            throw new InputMismatchException("잘못된 입력입니다. 3~5 사이의 숫자를 입력해주세요.");
        }
        return Integer.parseInt(input);
    }


}
