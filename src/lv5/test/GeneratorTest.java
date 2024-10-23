package lv5.test;

import lv5.generator.RandomGenerator;

import java.util.HashSet;
import java.util.Set;

public class GeneratorTest {
    @Test
    public void testRandomNumberLength() {
        for (int i = 3; i <= 5; i++) {
            int randomNumber = RandomGenerator.generateUniqueDigitNumber(i);
            assert Integer.toString(randomNumber).length() == i : "Expected " + i + " but got " + Integer.toString(randomNumber).length();
        }
    }

    @Test
    public void testRandomNumberNonRepeated() {
        Set<Character> digitSet = new HashSet<>();

        for (int i = 3; i <= 5; i++) {
            int randomNumber = RandomGenerator.generateUniqueDigitNumber(i);
            for (char c : String.valueOf(randomNumber).toCharArray()) {
                assert digitSet.add(c) : "Duplicate digit found: " + c;
            }
            digitSet.clear();
        }
    }

    @Test
    public void testRandomNumberNonContainedZero() {
        for (int i = 3; i <= 5; i++) {
            int randomNumber = RandomGenerator.generateUniqueDigitNumber(i);
            while (randomNumber > 0) {
                int digit = randomNumber % 10;
                assert digit != 0 : "Zero can not be in the Number: " + digit;
                randomNumber /= 10;
            }
        }
    }

    @Test
    public void testRandomWordLength() {
        for (int i = 3; i <= 5; i++) {
            String randomWord = RandomGenerator.generateRandomWord(i);
            assert randomWord.length() == i : "Expected Length: " + i + " but got " + randomWord.length();
        }
    }

    @Test
    public void testRandomStringLength() {
        for (int i = 3; i <= 5; i++) {
            String randomString = RandomGenerator.generateRandomString(i);
            assert randomString.length() == i : "Expected Length: " + i + " but got " + randomString.length();
        }
    }


}
