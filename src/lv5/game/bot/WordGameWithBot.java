package lv5.game.bot;

import lv5.enums.GameType;
import lv5.game.Pair;
import lv5.generator.RandomGenerator;
import lv5.handler.InputHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordGameWithBot extends GameWithBot {

    @Override
    protected void addGame(int count) {
        super.games.add(new Pair<>(GameType.BI, count));
    }

    @Override
    protected String generateAnswer(int length) {
        return RandomGenerator.generateRandomWord(length);
    }

    @Override
    protected String getHumanGuess(int length) {
        return InputHandler.getGuessWord("영단어를 입력하세요", length);
    }

    @Override
    protected String getAiGuess(String previousGuess, int length, List<String> possibleElements) {
        return previousGuess == null ? RandomGenerator.generateRandomWord(length) : getNextSmartGuess(possibleElements);
    }


    @Override
    protected List<String> generateAllPossibleElements(int length) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        // Define the character set (for example, lowercase letters)
        String characterSet = "abcdefghijklmnopqrstuvwxyz";
        // Initialize the queue with each character
        for (char c : characterSet.toCharArray()) {
            queue.add(String.valueOf(c));
        }
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.length() == length) {
                result.add(current);
            } else {
                // Append each character to the current string if it's not already included
                for (char c : characterSet.toCharArray()) {
                    if (!current.contains(String.valueOf(c))) {
                        queue.add(current + c);
                    }
                }
            }
        }
        return result;
    }

}

