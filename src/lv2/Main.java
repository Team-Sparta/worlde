package lv2;

import lv2.validator.Validation;
import lv2.validator.ValidationResult;

import java.util.InputMismatchException;

public class Main {
    public static final InputHandler inputHandler = new InputHandler();
    public static final RandomGenerator randomGenerator = new RandomGenerator();

    public static void main(String[] args) {
        boolean isExit = false;

        while (!isExit) {
            try {
                System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
                InitNumType initNum = inputHandler.getInitNum("1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
                switch (initNum) {
                    case START:
                        startGame();
                        break;
                    case EXIT:
                        isExit = true;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void startGame() {
        int guess = -1;
        int answer = randomGenerator.generateUniqueThreeDigitNumber();
        System.out.println("\n< 게임을 시작합니다 >");
        while (guess != answer) {
            try {
                guess = inputHandler.getInput("숫자를 입력하세요", Parser::parseGuessNum);
                ValidationResult result = Validation.validate(guess, answer);
                System.out.println(result + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}