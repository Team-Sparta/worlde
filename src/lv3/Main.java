package lv3;

import lv3.validator.Validation;
import lv3.validator.ValidationResult;

public class Main {
    public static final InputHandler inputHandler = new InputHandler();
    public static final GameTracker gameTracker = new GameTracker();

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
                    case RECORD:
                        gameTracker.printRecord();
                        break;
                    case EXIT:
                        isExit = true;
                        System.out.println("< 숫자 야구 게임을 종료합니다 >\n");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void startGame() {
        int count = 0;
        int guess = -1;
        int answer = RandomGenerator.generateUniqueThreeDigitNumber();
        System.out.println("\n< 게임을 시작합니다 >");
        while (guess != answer) {
            try {
                guess = inputHandler.getInput("숫자를 입력하세요", Parser::parseGuessNum);
                ValidationResult result = Validation.validate(guess, answer);
                System.out.println(result + "\n");
                count++;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        gameTracker.addGame(count);
    }
}