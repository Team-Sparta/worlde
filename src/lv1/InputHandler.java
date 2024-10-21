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

    public double getDoubleInput(String prompt) throws InputMismatchException {
        return getInput(prompt, Parser::parseNum);
    }

    public String getExpression() {
        System.out.print("수식을 한줄에 입력해주세요: ");
        return scanner.nextLine();
    }

    public boolean shouldExit() {
        System.out.print("더 계산하시겠습니까? [Y/n] ");
        return "n".equalsIgnoreCase(scanner.nextLine());
    }
}