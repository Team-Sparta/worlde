package lv5.test;

import lv5.enums.GameType;
import lv5.enums.MenuType;
import lv5.parser.Parser;

import java.util.InputMismatchException;
import java.util.Objects;

public class ParserTest {

    @Test
    public void testParseInitNumWithValidInput() {
        MenuType result = Parser.parseInitNum("2");
        assert result == MenuType.RECORD : "Expected MenuType.RECORD but got " + result;
    }

    @Test
    public void testParseInitNumWithInvalidInput() {
        try {
            Parser.parseInitNum("5");
            throw new AssertionError("Expected InputMismatchException for input '5'");
        } catch (InputMismatchException e) {
            assert Objects.equals(e.getMessage(), "잘못된 입력입니다. 0~3 사이의 숫자를 입력해주세요.") :
                    "Expected error message for invalid InitNum but got: " + e.getMessage();
        }
    }

    @Test
    public void testParseGameTypeWithValidInput() {
        GameType result = Parser.parseGameType("N");
        assert result == GameType.NUMBER : "Expected GameType.NUMBER but got " + result;
    }

    @Test
    public void testParseGameTypeWithInvalidInput() {
        try {
            Parser.parseGameType("X");
            throw new AssertionError("Expected InputMismatchException for input 'X'");
        } catch (InputMismatchException e) {
            assert Objects.equals(e.getMessage(), "잘못된 입력입니다.") :
                    "Expected error message for invalid GameType but got: " + e.getMessage();
        }
    }

    @Test
    public void testParseLevelWithValidInput() {
        int result = Parser.parseLevel("4");
        assert result == 4 : "Expected level to be 4 but got " + result;
    }

    @Test
    public void testParseLevelWithInvalidInput() {
        try {
            Parser.parseLevel("6");
            throw new AssertionError("Expected InputMismatchException for input '6'");
        } catch (InputMismatchException e) {
            assert Objects.equals(e.getMessage(), "잘못된 입력입니다. 3~5 사이의 숫자를 입력해주세요.") :
                    "Expected error message for invalid level but got: " + e.getMessage();
        }
    }

    @Test
    public void testParseGuessNumWithValidInput() {
        int result = Parser.parseGuessNum("1234", 4);
        assert result == 1234 : "Expected guess number to be 1234 but got " + result;
    }

    @Test
    public void testParseGuessNumWithInvalidLength() {
        try {
            Parser.parseGuessNum("123", 4);
            throw new AssertionError("Expected InputMismatchException for input '123' with level 4");
        } catch (InputMismatchException e) {
            assert Objects.equals(e.getMessage(), "자릿수가 맞지않습니다. 4자리 수를 입력해주세요.") :
                    "Expected error message for invalid length but got: " + e.getMessage();
        }
    }

    @Test
    public void testParseGuessNumWithNonNumericInput() {
        try {
            Parser.parseGuessNum("12AB", 4);
            throw new AssertionError("Expected InputMismatchException for input '12AB'");
        } catch (InputMismatchException e) {
            assert Objects.equals(e.getMessage(), "잘못된 입력입니다. 숫자를 입력해주세요.") :
                    "Expected error message for non-numeric input but got: " + e.getMessage();
        }
    }

    @Test
    public void testParseGuessNumWithZero() {
        try {
            Parser.parseGuessNum("1024", 4);
            throw new AssertionError("Expected InputMismatchException for input '1024'");
        } catch (InputMismatchException e) {
            assert Objects.equals(e.getMessage(), "0은 사용할수 없습니다.") :
                    "Expected error message for input with zero but got: " + e.getMessage();
        }
    }

    @Test
    public void testParseGuessNumWithRepetitiveDigits() {
        try {
            Parser.parseGuessNum("1224", 4);
            throw new AssertionError("Expected InputMismatchException for input '1224'");
        } catch (InputMismatchException e) {
            assert Objects.equals(e.getMessage(), "동일한 숫자는 사용될수 없습니다.") :
                    "Expected error message for input with repetitive digits but got: " + e.getMessage();
        }
    }

    @Test
    public void testParseGuessWordWithValidInput() {
        String result = Parser.parseGuessWord("test", 4);
        assert result.equals("TEST") : "Expected 'TEST' but got " + result;
    }

    @Test
    public void testParseGuessWordWithInvalidLength() {
        try {
            Parser.parseGuessWord("tes", 4);
            throw new AssertionError("Expected InputMismatchException for input 'tes' with level 4");
        } catch (InputMismatchException e) {
            assert Objects.equals(e.getMessage(), "자릿수가 맞지않습니다. 4자리 수를 입력해주세요.") :
                    "Expected error message for invalid length but got: " + e.getMessage();
        }
    }

    @Test
    public void testParseGuessWordWithNonAlphabeticInput() {
        try {
            Parser.parseGuessWord("test1", 4);
            throw new AssertionError("Expected InputMismatchException for input 'test1'");
        } catch (InputMismatchException e) {
            assert Objects.equals(e.getMessage(), "잘못된 입력입니다. 영단어를 입력해주세요.") :
                    "Expected error message for non-alphabetic input but got: " + e.getMessage();
        }
    }
}