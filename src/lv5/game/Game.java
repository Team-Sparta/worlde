package lv5.game;

import lv5.enums.GameType;
import lv5.validator.Validation;
import lv5.validator.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Game {
    protected final List<Pair<GameType, Integer>> games = new ArrayList<>();

    protected void startGame(int level) {
        int count = 0;
        String guess = null;

        String answer = generateAnswer(level);
        System.out.println("\n< 게임을 시작합니다 >");

        while (!Objects.equals(guess, answer)) {
            try {
                guess = getHumanGuess(level);
                ValidationResult result = Validation.checkResult(guess, answer);
                System.out.println(result + "\n");
                count++;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        addGame(count);
    }


    protected void printRecord() {
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

    protected abstract void addGame(int count);

    protected abstract String generateAnswer(int length);

    protected abstract String getHumanGuess(int length);

}
