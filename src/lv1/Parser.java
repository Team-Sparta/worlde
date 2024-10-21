package lv1;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class Parser {
    private static final String NUMBER_REGEX = "^-?\\d+$";
    private static final String ZERO_REGEX = ".*0.*"; // This matches any string containing at least one '0'
    private static final String REPETITIVE_DIGIT_REGEX = ".*(\\d).*(\\1).*"; // This matches any digit followed later by itself

    public static int parseNum(String numInput) throws InputMismatchException {
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
