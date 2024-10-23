package lv5.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGenerator {

    private final static Random random = new Random();

    public static int generateUniqueDigitNumber(int level) {
        // Create a list of digits (1-9) to ensure no zeros are included
        List<Integer> digits = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

        // Shuffle the list to randomize the digit order
        Collections.shuffle(digits, random);

        // Initialize the result with the first digit
        int number = 0;

        // Append 'level' unique digits from the shuffled list
        for (int i = 0; i < level; i++) {
            number = number * 10 + digits.get(i);
        }

        return number;
    }

    public static String generateRandomWord(int length) {
        try {
            System.out.println("API 요청중...");
            return WordService.fetchRandomWord(length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return generateRandomString(length);
        }
    }


    public static String generateRandomString(int length) {

        List<String> alphabet = new ArrayList<>(List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

        Collections.shuffle(alphabet, random);

        StringBuilder word = new StringBuilder(length);

        // Loop to append random characters from ALPHABET
        for (int i = 0; i < length; i++) {
            word.append(alphabet.get(i));
        }

        return word.toString();
    }
}
