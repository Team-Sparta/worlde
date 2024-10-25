package lv5.game.bot;

import lv5.enums.GameType;
import lv5.game.Pair;
import lv5.generator.RandomGenerator;
import lv5.handler.InputHandler;

import java.util.*;

public class NumberGameWithBot extends GameWithBot {

    @Override
    protected void addGame(int count) {
        super.games.add(new Pair<>(GameType.AI, count));
    }

    @Override
    protected String generateAnswer(int length) {
        return String.valueOf(RandomGenerator.generateUniqueDigitNumber(length));
    }

    @Override
    protected String getHumanGuess(int length) {
        return String.valueOf(InputHandler.getGuessNum("숫자를 입력하세요", length));
    }

    @Override
    protected String getAiGuess(String previousGuess, int length, List<String> possibleElements) {
        return previousGuess == null ? String.valueOf(RandomGenerator.generateUniqueDigitNumber(length)) : getNextSmartGuess(possibleElements);
    }


    @Override
    protected List<String> generateAllPossibleElements(int length) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        for (int i = 1; i <= 9; i++) {
            queue.add(String.valueOf(i));
        }
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.length() == length) {
                result.add(current);
            } else {
                for (int i = 1; i <= 9; i++) {
                    if (!current.contains(String.valueOf(i))) {
                        queue.add(current + i);
                    }
                }
            }
        }
        return result;
    }
}
