package lv5;


import lv5.enums.InitNumType;
import lv5.game.GameTracker;
import lv5.handler.InputHandler;

public class Main {
    public static final GameTracker gameTracker = new GameTracker();

    public static void main(String[] args) {
        boolean isExit = false;

        while (!isExit) {
            try {
                System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
                InitNumType initNum = InputHandler.getInitNum("0. 자리수 설정 1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
                switch (initNum) {
                    case LEVEL:
                        gameTracker.setLevel();
                        break;
                    case START:
                        gameTracker.createGame();
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
}