package lv5.game.solo;

import lv5.game.Game;
import lv5.game.Pair;
import lv5.generator.RandomGenerator;
import lv5.enums.GameType;
import lv5.handler.InputHandler;

public class SoloNumberGame extends Game {

    @Override
    protected void addGame(int count) {
        super.games.add(new Pair<>(GameType.NUMBER, count));
    }

    @Override
    protected String generateAnswer(int length) {
        return String.valueOf(RandomGenerator.generateUniqueDigitNumber(length));
    }

    @Override
    protected String getHumanGuess(int length) {
        return String.valueOf(InputHandler.getGuessNum("숫자를 입력하세요", length));
    }
}
