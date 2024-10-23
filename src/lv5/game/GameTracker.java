package lv5.game;

import lv5.enums.GameType;
import lv5.handler.InputHandler;

public class GameTracker {

    private GameType gameType = GameType.NUMBER;
    private int level = 3;

    public void setLevel() {
        this.level = InputHandler.getLevel("설정하고자 하는 자리수를 입력하세요.");
        System.out.println(level + "자리수 난이도로 설정되었습니다");
    }

    public void createGame() {
        this.gameType = InputHandler.getGameType("W: Word, N: Number, A: AI Mode(Number)");
        this.gameType.getGame().startGame(this.level);
    }

    public void printRecord() {
        this.gameType.getGame().printRecord();
    }
}