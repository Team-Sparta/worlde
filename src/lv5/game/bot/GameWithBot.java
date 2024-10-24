package lv5.game.bot;

import lv5.game.Game;
import lv5.validator.Validation;
import lv5.validator.ValidationResult;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public abstract class GameWithBot extends Game {

    public static String getNextSmartGuess(List<String> possibleElements) {
        // Shuffle the list to randomize the selection
        Collections.shuffle(possibleElements);

        // Take a random sample of the possible elements
        List<String> sample = possibleElements.subList(0, Math.min(30_000, possibleElements.size()));

        // Use a concurrent map to store scores for each guess
        Map<String, Integer> scoreMap = new ConcurrentHashMap<>();

        // Process the sample concurrently
        sample.parallelStream().forEach(guess -> {
            // A map to store feedback counts (for strikes and balls)
            Map<String, Integer> feedbackCountMap = new HashMap<>();

            // For each guess, calculate feedback against all other possible elements in the sample
            for (String possible : sample) {
                if (!guess.equals(possible)) {
                    int[] feedback = getFeedback(guess, possible);
                    String feedbackKey = feedback[0] + "-" + feedback[1];  // Strikes and Balls as key
                    feedbackCountMap.merge(feedbackKey, 1, Integer::sum);  // Increment feedback count
                }
            }

            // Calculate the score based on feedback distribution
            int score = feedbackCountMap.values().stream()
                    .mapToInt(count -> (sample.size() - count) * (sample.size() - count))  // Focus on larger eliminations
                    .sum();

            // Store the score for this guess
            scoreMap.put(guess, score);
        });

        // Early exit: Select the guess with the lowest score (best guess)
        return Collections.min(scoreMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static int[] getFeedback(String guess, String secret) {
        ValidationResult validationResult = Validation.countStrikesAndBalls(guess, secret);
        return new int[]{validationResult.strikes(), validationResult.balls(), validationResult.getOuts()};
    }

    public static List<String> filterPossibleElements(List<String> possibleElements, String guess, int[] feedback) {
        return possibleElements.parallelStream()
                .filter(num -> {
                    int[] tempFeedback = getFeedback(guess, num);
                    return tempFeedback[0] == feedback[0] && tempFeedback[1] == feedback[1];
                })
                .collect(Collectors.toList());
    }

}
