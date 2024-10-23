package lv5.game;

import lv5.enums.GameType;
import lv5.handler.InputHandler;

public class GameTracker {

    private GameType gameType;

    public void setLevel() {
        int level = InputHandler.getLevel("설정하고자 하는 자리수를 입력하세요.");
        System.out.println(level + "자리수 난이도로 설정되었습니다");
    }

    public void createGame(int level) {
        this.gameType = InputHandler.getGameType("W: Word, N: Number");
        this.gameType.getGame().startGame(level);
    }

    public void printRecord() {
        this.gameType.getGame().printRecord();
    }
}