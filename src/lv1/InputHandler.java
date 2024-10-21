package lv1;


import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner = new Scanner(System.in);

    @FunctionalInterface
    public interface InputParser<T> {
        T parse(String input) throws InputMismatchException;
    }

    public <T> T getInput(String prompt, InputParser<T> parser) throws InputMismatchException {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return parser.parse(input);
    }
}