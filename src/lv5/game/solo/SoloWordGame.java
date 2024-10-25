package lv5.game.solo;

import lv5.enums.GameType;
import lv5.game.Game;
import lv5.game.Pair;
import lv5.generator.RandomGenerator;
import lv5.handler.InputHandler;

public class SoloWordGame extends Game {

    @Override
    protected void addGame(int count) {
        super.games.add(new Pair<>(GameType.WORD, count));
    }

    @Override
    protected String generateAnswer(int length) {
        return RandomGenerator.generateRandomWord(length);
    }

    @Override
    protected String getHumanGuess(int length) {
        return InputHandler.getGuessWord("단어를 입력하세요", length);
    }

}