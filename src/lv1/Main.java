package lv1;

import lv1.validator.Validation;
import lv1.validator.ValidationResult;

import java.util.*;

public class Main {
    // Static list of digits from 0 to 9
    public static final InputHandler inputHandler = new InputHandler();
    public static final RandomGenerator randomGenerator = new RandomGenerator();

    public static void main(String[] args) {
        int guess = -1;
        int answer = randomGenerator.generateUniqueThreeDigitNumber();
        System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
        System.out.println("< 게임을 시작합니다 >");

        while (guess != answer) {
            try {
                guess = inputHandler.getInput("숫자를 입력하세요", Parser::parseNum);

                ValidationResult result = Validation.validate(guess, answer);
                System.out.println(result);
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}