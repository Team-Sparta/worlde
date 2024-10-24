package lv5.game;

import lv5.enums.GameType;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    private static final List<Pair<GameType, Integer>> games = new ArrayList<>();

    abstract public void startGame(int level);

    public void addGame(GameType gameType, int count) {
        games.add(new Pair<>(gameType, count));
    }

    public void printRecord() {
        if (games.isEmpty()) {
            System.out.println("No games found\n");
            return;
        }
        System.out.println("< 게임 기록 보기 >");
        for (int i = 0; i < games.size(); i++) {
            System.out.println(i + 1 + "번쨰 [" + games.get(i).first().getName() + "] 게임 : 시도 횟수 - " + games.get(i).second());
        }
        System.out.println();
    }
}
