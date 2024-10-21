package lv4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGenerator {

    public static int generateUniqueDigitNumber(int level) {
        // Create a list of digits (1-9) to ensure no zeros are included
        List<Integer> digits = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

        // Shuffle the list to randomize the digit order
        Collections.shuffle(digits, new Random());

        // Initialize the result with the first digit
        int number = 0;

        // Append 'level' unique digits from the shuffled list
        for (int i = 0; i < level; i++) {
            number = number * 10 + digits.get(i);
        }

        return number;
    }
}
