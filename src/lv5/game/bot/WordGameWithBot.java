package lv5.game.bot;

import lv5.enums.GameType;
import lv5.game.Game;
import lv5.generator.RandomGenerator;
import lv5.handler.InputHandler;
import lv5.validator.Validation;
import lv5.validator.ValidationResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordGameWithBot extends GameWithBot {
    @Override
    public void startGame(int level) {
        int count = 0;
        String humanGuess = "";
        String aiGuess = "";

        String answer = RandomGenerator.generateRandomWord(level);
        List<String> possibleStrings = generateAllPossibleStrings(level);

        while (!humanGuess.equals(answer) && !aiGuess.equals(answer)) {
            humanGuess = InputHandler.getGuessWord("영단어를 입력하세요", level);
            ValidationResult humanResult = Validation.checkResult(humanGuess, answer);
            System.out.println("Your Result: " + humanResult);

            aiGuess = aiGuess.isEmpty() ? RandomGenerator.generateRandomString(level) : getNextSmartGuess(possibleStrings);
            System.out.println("\nAI's Guess: " + aiGuess);
            ValidationResult aiResult = Validation.checkResult(aiGuess, answer);
            System.out.println("AI's Result: " + aiResult + "\n");

            int[] feedback = getFeedback(aiGuess, answer);
            possibleStrings = filterPossibleElements(possibleStrings, aiGuess, feedback);
            count++;
        }
        addGame(GameType.AI, count);
    }

    private static List<String> generateAllPossibleStrings(int length) {
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
