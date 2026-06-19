import com.roadmap.fourth.model.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameScoreTest {
    private GameModel game;

    @BeforeEach
    public void prepareGameModel() {
        game = new GameModel();
    }

    @Test
    public void playerShouldWonPoint() {
        game.awardGameScoreTo(0, 1);
        String[] expectedPointScore = {"15", "0"};
        String[] actualPointScore = game.getGamePoints();
        Assertions.assertArrayEquals(expectedPointScore, actualPointScore);
    }

    @Test
    public void playerShouldWonGame() {
        game.awardGameScoreTo(0, 1);
        game.awardGameScoreTo(0, 1);
        game.awardGameScoreTo(0, 1);
        boolean isGameWon = game.awardGameScoreTo(0, 1);
        Assertions.assertTrue(isGameWon);
    }

    @Test
    public void gameShouldContinueWhenDeuce() {
        for (int i = 0; i < 3; i++) {
            game.awardGameScoreTo(0, 1);
            game.awardGameScoreTo(1, 0);
        }

        boolean isGameWon = game.awardGameScoreTo(0, 1);
        Assertions.assertFalse(isGameWon);
    }

    @Test
    public void playerShouldGetAdvantageWhenDeuce() {
        for (int i = 0; i < 3; i++) {
            game.awardGameScoreTo(0, 1);
            game.awardGameScoreTo(1, 0);
        }

        game.awardGameScoreTo(0, 1);
        String[] expectedPointScore = {"AD", "40"};
        String[] actualPointScore = game.getGamePoints();
        Assertions.assertArrayEquals(expectedPointScore, actualPointScore);
    }

    @Test
    public void scoreShouldResetAfterLoseAdvantage() {
        for (int i = 0; i < 3; i++) {
            game.awardGameScoreTo(0, 1);
            game.awardGameScoreTo(1, 0);
        }

        game.awardGameScoreTo(0, 1);
        game.awardGameScoreTo(1, 0);
        String[] expectedPointScore = {"40", "40"};
        String[] actualPointScore = game.getGamePoints();
        Assertions.assertArrayEquals(expectedPointScore, actualPointScore);
    }


    @Test
    public void gameShouldBeWonAfterAdvantage() {
        for (int i = 0; i < 3; i++) {
            game.awardGameScoreTo(0, 1);
            game.awardGameScoreTo(1, 0);
        }

        game.awardGameScoreTo(0, 1);
        boolean isGameWon = game.awardGameScoreTo(0, 1);
        Assertions.assertTrue(isGameWon);
    }
}
