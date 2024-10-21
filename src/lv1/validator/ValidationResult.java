package lv1.validator;

public record ValidationResult(int totalDigits, int strikes, int balls) {

    public int getOuts(int totalDigits) {
        return totalDigits - strikes - balls;
    }

    @Override
    public String toString() {
        return totalDigits == strikes ? "정답입니다!"
                : String.format("스트라이크: %d, 볼: %d, 아웃: %d", strikes, balls, getOuts(strikes + balls));
    }
}