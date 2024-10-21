package lv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
    private static final List<Integer> DIGITS = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public int generateUniqueThreeDigitNumber() {
        // Create a copy of the static list to shuffle
        List<Integer> digits = new ArrayList<>(DIGITS);

        // Shuffle the list of digits to get random order
        Collections.shuffle(digits, new Random());

        // Combine the first 3 digits into a number
        return digits.get(0) * 100 + digits.get(1) * 10 + digits.get(2);
    }
}
