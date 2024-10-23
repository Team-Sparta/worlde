package lv5.validator;

public class Validation {

    public static <T> ValidationResult checkResult(T guess, T answer) throws IllegalArgumentException {
        String guessStr = guess.toString();
        String answerStr = answer.toString();

        if (!validateLength(guessStr, answerStr)) {
            throw new IllegalArgumentException("입력값의 길이는 같아야 합니다.");
        }
        ;
        return countStrikesAndBalls(guessStr, answerStr);
    }

    public static boolean validateLength(String guess, String answer) {
        return guess.length() == answer.length();
    }


    public static ValidationResult countStrikesAndBalls(String guess, String answer) {
        int strikes = 0;
        int balls = 0;

        int[] guessCount = new int[256];  // Assuming ASCII character set
        int[] answerCount = new int[256];

        for (int i = 0; i < guess.length(); i++) {
            char gChar = guess.charAt(i);
            char aChar = answer.charAt(i);

            if (gChar == aChar) {
                // Count strike if characters match at the same position
                strikes++;
            } else {
                // Count occurrences of non-matching characters
                guessCount[gChar]++;
                answerCount[aChar]++;
            }
        }

        // Calculate balls by comparing unmatched character counts
        for (int i = 0; i < 256; i++) {
            balls += Math.min(guessCount[i], answerCount[i]);
        }

        return new ValidationResult(guess.length(), strikes, balls);
    }
}
