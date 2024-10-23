package lv5.game;

import lv5.generator.RandomGenerator;
import lv5.enums.GameType;
import lv5.handler.InputHandler;
import lv5.validator.Validation;
import lv5.validator.ValidationResult;

import java.util.ArrayList;
import java.util.List;

public class NumberGame implements Game {
    private static final List<Integer> games = new ArrayList<>();

    @Override
    public void startGame(int level) {
        int count = 0;
        int guess = -1;
        int answer = RandomGenerator.generateUniqueDigitNumber(level);
        System.out.println("\n< 게임을 시작합니다 >");
        GameType gameType = InputHandler.getGameType("W: Word, N: Number");

        while (guess != answer) {
            try {
                guess = InputHandler.getGuessNum("숫자를 입력하세요", level);
                ValidationResult result = Validation.checkResult(guess, answer);
                System.out.println(result + "\n");
                count++;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        addGame(count);
    }

    @Override
    public void addGame(int count) {
        games.add(count);
    }

    @Override
    public void printRecord() {
        if (games.isEmpty()) {
            System.out.println("No games found\n");
            return;
        }
        System.out.println("< 게임 기록 보기 >");
        for (int i = 0; i < games.size(); i++) {
            System.out.println(i + 1 + "번쨰 게임: 시도 횟수 - " + games.get(i));
        }
        System.out.println();
    }
}
