package lv5.game.solo;

import lv5.enums.GameType;
import lv5.game.Game;
import lv5.generator.RandomGenerator;
import lv5.handler.InputHandler;
import lv5.validator.Validation;
import lv5.validator.ValidationResult;

import java.util.Objects;

public class SoloWordGame extends Game {

    @Override
    public void startGame(int level) {
        int count = 0;
        String guess = "";

        String answer = RandomGenerator.generateRandomWord(level);
        System.out.println("\n< 게임을 시작합니다 >");

        while (!Objects.equals(guess, answer)) {
            try {
                guess = InputHandler.getGuessWord("단어를 입력하세요", level);
                ValidationResult result = Validation.checkResult(guess, answer);
                System.out.println(result + "\n");
                count++;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        addGame(GameType.WORD, count);
    }

}