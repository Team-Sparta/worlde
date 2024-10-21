package lv1;

import java.util.HashMap;

public class Validator {
    public static void validate(int guess, int answer) {
        String guessStr = String.valueOf(guess);
        String answerStr = String.valueOf(answer);

        int strikes = 0, balls = 0;
        HashMap<Character, Integer> guessCount = new HashMap<>(), answerCount = new HashMap<>();

        // Count strikes and unmatched digits
        for (int i = 0; i < Math.min(guessStr.length(), answerStr.length()); i++) {
            char gChar = guessStr.charAt(i), aChar = answerStr.charAt(i);
            if (gChar == aChar) {
                strikes++;
            } else {
                guessCount.put(gChar, guessCount.getOrDefault(gChar, 0) + 1);
                answerCount.put(aChar, answerCount.getOrDefault(aChar, 0) + 1);
            }
        }

        // Count balls
        for (char digit : guessCount.keySet()) {
            balls += Math.min(guessCount.get(digit), answerCount.getOrDefault(digit, 0));
        }

        // Output the result
        System.out.printf("스트라이크: %d, 볼: %d, 아웃: %d%n", strikes, balls,
                Math.min(guessStr.length(), answerStr.length()) - strikes - balls);

    }
}
