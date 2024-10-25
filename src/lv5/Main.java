package lv5;


import lv5.enums.MenuType;
import lv5.game.GameTracker;
import lv5.handler.InputHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger("lv5");
    private static final GameTracker gameTracker = new GameTracker();

    public static void main(String[] args) {
        boolean isExit = false;

        while (!isExit) {
            try {
                System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
                MenuType initNum = InputHandler.getInitNum("0. 자리수 설정 1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
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
                logger.log(Level.SEVERE, "An exception occurred: {}", e.getMessage());
                logger.log(Level.SEVERE, "Exception stack trace", e.getMessage());
            }
        }
    }
}