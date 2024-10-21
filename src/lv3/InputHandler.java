package lv3;

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
                // Loop again to retry input
            }
        }
    }

    public InitNumType getInitNum(String prompt) {
        return getInput(prompt, Parser::parseInitNum);
    }
}