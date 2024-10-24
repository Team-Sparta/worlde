package lv5.game.bot;

import lv5.enums.GameType;
import lv5.generator.RandomGenerator;
import lv5.handler.InputHandler;
import lv5.validator.Validation;
import lv5.validator.ValidationResult;

import java.util.*;


public class NumberGameWithBot extends GameWithBot {

    @Override
    public void startGame(int level) {
        int count = 0;
        int humanGuess = -1;
        int aiGuess = -1;

        int answer = RandomGenerator.generateUniqueDigitNumber(level);
        List<String> possibleNumbers = generateAllPossibleNumbers(level);
        while (humanGuess != answer && aiGuess != answer) {
            humanGuess = InputHandler.getGuessNum("숫자를 입력하세요", level);
            ValidationResult humanResult = Validation.checkResult(humanGuess, answer);
            System.out.println("Your Result: " + humanResult);

            aiGuess = aiGuess == -1 ? RandomGenerator.generateUniqueDigitNumber(level) : Integer.parseInt(getNextSmartGuess(possibleNumbers));
            System.out.println("\nAI's Guess: " + aiGuess);
            ValidationResult aiResult = Validation.checkResult(aiGuess, answer);
            System.out.println("AI's Result: " + aiResult + "\n");

            int[] feedback = getFeedback(aiGuess + "", String.valueOf(answer));
            possibleNumbers = filterPossibleElements(possibleNumbers, aiGuess + "", feedback);
            count++;
        }
        addGame(GameType.AI, count);
    }


    private static List<String> generateAllPossibleNumbers(int length) {
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
