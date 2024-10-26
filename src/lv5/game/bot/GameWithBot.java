package lv5.game.bot;

import lv5.game.Game;
import lv5.validator.Validation;
import lv5.validator.ValidationResult;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public abstract class GameWithBot extends Game {

    private static final int SAMPLE_SIZE_LIMIT = 30_000;
    private static final int PARALLEL_SIZE = 10_000;

    protected abstract String getAiGuess(String previousGuess, int length, List<String> possibleNumbers);

    protected abstract List<String> generateAllPossibleElements(int length);

    @Override
    public void startGame(int level) {
        int count = 0;
        String humanGuess = null;
        String aiGuess = null;

        String answer = generateAnswer(level);
        List<String> possibleElements = generateAllPossibleElements(level);

        while (!Objects.equals(humanGuess, answer) && !Objects.equals(aiGuess, answer)) {
            humanGuess = getHumanGuess(level);
            ValidationResult humanResult = Validation.checkResult(humanGuess, answer);
            System.out.println("Your Result: " + humanResult);

            aiGuess = getAiGuess(aiGuess, level, possibleElements);
            System.out.println("\nAI's Guess: " + aiGuess);
            ValidationResult aiResult = Validation.checkResult(aiGuess, answer);
            System.out.println("AI's Result: " + aiResult + "\n");

            int[] feedback = getFeedback(aiGuess, answer);
            possibleElements = filterPossibleElements(possibleElements, aiGuess, feedback);
            count++;
        }
        addGame(count);
    }


    protected static String getNextSmartGuess(List<String> possibleElements) {
        // Shuffle the list to randomize the selection
        Collections.shuffle(possibleElements);

        // Take a random sample of the possible elements
        List<String> sample = possibleElements.subList(0, Math.min(SAMPLE_SIZE_LIMIT, possibleElements.size()));

        // Use a map to store scores for each guess
        Map<String, Integer> scoreMap = new HashMap<>();
        boolean useParallel = sample.size() >= PARALLEL_SIZE;

        // Decide whether to process in parallel or sequentially based on the sample size
        if (useParallel) {
            ForkJoinPool.commonPool().submit(() -> sample.parallelStream().forEach(guess ->
                    scoreMap.put(guess, calculateScoreForGuess(guess, sample))
            )).join();
        } else {
            sample.stream().forEach(guess -> scoreMap.put(guess, calculateScoreForGuess(guess, sample)));
        }
        return Collections.min(scoreMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


    private static int calculateScoreForGuess(String guess, List<String> sample) {
        Map<String, Integer> feedbackCountMap = new HashMap<>();
        sample.stream()
                .filter(possible -> !guess.equals(possible))
                .forEach(possible -> {
                    int[] feedback = getFeedback(guess, possible);
                    feedbackCountMap.merge(feedback[0] + "-" + feedback[1], 1, Integer::sum);
                });

        return feedbackCountMap.values().stream()
                .mapToInt(count -> (sample.size() - count) * (sample.size() - count))
                .sum();
    }


    protected static int[] getFeedback(String guess, String secret) {
        ValidationResult validationResult = Validation.countStrikesAndBalls(guess, secret);
        return new int[]{validationResult.strikes(), validationResult.balls(), validationResult.getOuts()};
    }

    protected static List<String> filterPossibleElements(List<String> possibleElements, String guess, int[] feedback) {
        return possibleElements.parallelStream()
                .filter(num -> {
                    int[] tempFeedback = getFeedback(guess, num);
                    return tempFeedback[0] == feedback[0] && tempFeedback[1] == feedback[1];
                })
                .collect(Collectors.toList());
    }
}
