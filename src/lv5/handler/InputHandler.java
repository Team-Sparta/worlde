package lv5.handler;

import lv5.enums.GameType;
import lv5.enums.MenuType;
import lv5.parser.Parser;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner scanner = new Scanner(System.in);

    @FunctionalInterface
    public interface InputParser<T> {
        T parse(String input) throws InputMismatchException;
    }

    public static <T> T getInput(String prompt, InputParser<T> parser) {
        while (true) {
            try {
                System.out.println(prompt);
                String input = scanner.nextLine().trim();
                return parser.parse(input);
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public static MenuType getInitNum(String prompt) {
        return getInput(prompt, Parser::parseInitNum);
    }

    public static GameType getGameType(String prompt) {
        return getInput(prompt, Parser::parseGameType);
    }

    public static int getLevel(String prompt) {
        return getInput(prompt, Parser::parseLevel);
    }

    public static int getGuessNum(String prompt, int level) {
        return getInput(prompt, input -> Parser.parseGuessNum(input, level));
    }

    public static String getGuessWord(String prompt, int level) {
        return getInput(prompt, input -> Parser.parseGuessWord(input, level));
    }

}