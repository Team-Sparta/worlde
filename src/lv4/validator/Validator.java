package lv4.validator;

public interface Validator {
    <T> ValidationResult validate(T guess, T answer) throws IllegalArgumentException;
}