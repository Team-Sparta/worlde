package lv4;


import lv4.validator.ValidationResult;
import lv4.validator.Validation;

public class Main {
    public static final InputHandler inputHandler = new InputHandler();
    public static final Validation validation = new Validation();
    public static final GameTracker gameTracker = new GameTracker();

    public static void main(String[] args) {
        boolean isExit = false;

        while (!isExit) {
            try {
                System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
                InitNumType initNum = inputHandler.getInitNum("0. 자리수 설정 1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
                switch (initNum) {
                    case LEVEL:
                        setLevel();
                        break;
                    case START:
                        startGame(3);
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

    private static void setLevel() {
        int level = inputHandler.getLevel("설정하고자 하는 자리수를 입력하세요.");
        System.out.println(level + "자리수 난이도로 설정되었습니다");
        startGame(level);
    }

    private static void startGame(int level) {
        int count = 0;
        int guess = -1;
        int answer = RandomGenerator.generateUniqueDigitNumber(level);
        System.out.println("\n< 게임을 시작합니다 >");
        while (guess != answer) {
            try {
                guess = inputHandler.getGuess("숫자를 입력하세요", level);
                ValidationResult result = validation.validate(guess, answer);
                System.out.println(result + "\n");
                count++;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        gameTracker.addGame(count);
    }
}