package lv5.game;

import lv5.generator.RandomGenerator;
import lv5.handler.InputHandler;
import lv5.validator.Validation;
import lv5.validator.ValidationResult;

import java.util.*;
import java.util.stream.Collectors;


public class NumberGameWithBot extends Game {

    @Override
    public void startGame(int level) {
        int count = 0;
        int humanGuess = -1;
        int aiGuess = -1;

        int answer = RandomGenerator.generateUniqueDigitNumber(level);
        List<String> possibleNumbers = generateAllPossibleNumbers(level);
        System.out.println(possibleNumbers);
        while (humanGuess != answer && aiGuess != answer) {
            humanGuess = InputHandler.getGuessNum("숫자를 입력하세요", level);
            ValidationResult humanResult = Validation.checkResult(humanGuess, answer);
            System.out.println("Your Result: " + humanResult);

            aiGuess = aiGuess == -1 ? RandomGenerator.generateUniqueDigitNumber(level) : getNextSmartGuess(possibleNumbers);
            System.out.println("\nAI's Guess: " + aiGuess);
            ValidationResult aiResult = Validation.checkResult(aiGuess, answer);
            System.out.println("AI's Result: " + aiResult + "\n");

            int[] feedback = getFeedback(aiGuess + "", String.valueOf(answer));
            possibleNumbers = filterPossibleNumbers(possibleNumbers, aiGuess + "", feedback);
            count++;
        }
        addGame(count);
    }


    private static Integer getNextSmartGuess(List<String> possibleNumbers) {
        Map<String, Integer> scoreMap = new HashMap<>();

        // Score each possible guess based on the expected information gain
        for (String guess : possibleNumbers) {
            int score = 0;

            for (String possible : possibleNumbers) {
                int[] feedback = getFeedback(guess, possible);
                score += (feedback[0] * 100 + feedback[1] * 10 + feedback[2]); // Weighted scoring
            }

            scoreMap.put(guess, score);
        }

        // Select the guess with the highest score
        return Integer.parseInt(Collections.max(scoreMap.entrySet(), Map.Entry.comparingByValue()).getKey());
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

    private static int[] getFeedback(String guess, String secret) {
        ValidationResult validationResult = Validation.countStrikesAndBalls(guess, secret);
        return new int[]{validationResult.strikes(), validationResult.balls(), validationResult.getOuts()};
    }

    // Filter the possible numbers based on feedback
    private static List<String> filterPossibleNumbers(List<String> possibleNumbers, String guess, int[] feedback) {
        return possibleNumbers.stream()
                .filter(num -> {
                    int[] tempFeedback = getFeedback(guess, num);
                    return tempFeedback[0] == feedback[0] && tempFeedback[1] == feedback[1];
                })
                .collect(Collectors.toList());
    }


}
