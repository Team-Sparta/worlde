package lv4.handler;

import lv4.parser.Parser;
import lv4.enums.InitNumType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner = new Scanner(System.in);

    @FunctionalInterface
    public interface InputParser<T> {
        T parse(String input) throws InputMismatchException;
    }

    public <T> T getInput(String prompt, InputParser<T> parser) {
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

    public InitNumType getInitNum(String prompt) {
        return getInput(prompt, Parser::parseInitNum);
    }

    public int getGuess(String prompt, int level) {
        return getInput(prompt, input -> Parser.parseGuessNum(input, level));
    }

    public int getLevel(String prompt) {
        return getInput(prompt, Parser::parseLevel);
    }

}