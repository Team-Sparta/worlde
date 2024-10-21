package lv1.validator;

public interface Validator {
    <T> ValidationResult validate(T guess, T answer);
}