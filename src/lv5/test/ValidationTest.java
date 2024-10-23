package lv5.test;

import lv5.annotation.Test;
import lv5.validator.Validation;
import lv5.validator.ValidationResult;

import java.util.Objects;

public class ValidationTest {

    @Test
    public void testCheckResultWithEqualLength() {
        ValidationResult result = Validation.checkResult("1234", "5678");
        assert result.totalDigits() == 4 : "Expected total digits to be 4, but got " + result.totalDigits();
        assert result.strikes() == 0 : "Expected strikes to be 0, but got " + result.strikes();
        assert result.balls() == 0 : "Expected balls to be 0, but got " + result.balls();
    }

    @Test
    public void testCheckResultWithInvalidLength() {
        try {
            Validation.checkResult("123", "4567");
            throw new AssertionError("Expected IllegalArgumentException for unequal lengths");
        } catch (IllegalArgumentException e) {
            assert Objects.equals(e.getMessage(), "입력값의 길이는 같아야 합니다.") :
                    "Expected IllegalArgumentException message to be '입력값의 길이는 같아야 합니다.', but got: " + e.getMessage();
        }
    }

    @Test
    public void testCheckResultWithStrikes() {
        ValidationResult result = Validation.checkResult("1234", "1256");
        assert result.totalDigits() == 4 : "Expected total digits to be 4, but got " + result.totalDigits();
        assert result.strikes() == 2 : "Expected strikes to be 2, but got " + result.strikes();
        assert result.balls() == 0 : "Expected balls to be 0, but got " + result.balls();
    }

    @Test
    public void testCheckResultWithBalls() {
        ValidationResult result = Validation.checkResult("1234", "3412");
        assert result.totalDigits() == 4 : "Expected total digits to be 4, but got " + result.totalDigits();
        assert result.strikes() == 0 : "Expected strikes to be 0, but got " + result.strikes();
        assert result.balls() == 4 : "Expected balls to be 4, but got " + result.balls();
    }
}