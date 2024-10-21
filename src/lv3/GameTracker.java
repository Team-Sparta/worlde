package lv3;

import java.util.ArrayList;
import java.util.List;

public class GameTracker {
    private final List<Integer> games = new ArrayList<>();

    public void printRecord() {
        for (int i = 0; i < games.size(); i++) {
            System.out.println(i + 1 + "번쨰 게임: 시도 횟수 - " + games.get(i));
        }
    }

    public void addGame(int game) {
        games.add(game);
    }
}
