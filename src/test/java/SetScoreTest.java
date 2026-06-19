import com.roadmap.fourth.model.SetModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetScoreTest {
    private SetModel set;

    @BeforeEach
    public void prepareSetModel() {
        set = new SetModel();
    }

    @Test
    public void playerShouldWonGameScore() {
        for (int i = 0; i < 4; i++) {
            set.awardGamePointTo(0,1);
        }

        int[] expectedGameScore = {1, 0};
        int[] actualGameScore = set.getCurrentGameScore();
        Assertions.assertArrayEquals(expectedGameScore, actualGameScore);
    }

    @Test
    public void playerShouldWonSet() {
        for (int i = 0; i < 27; i++) {
            set.awardGamePointTo(0,1);
        }

        boolean isSetWon = set.awardGamePointTo(0, 1);
        Assertions.assertTrue(isSetWon);
    }

    @Test
    public void tieBreakShouldBeStarted() {
        for (int i = 0; i < 24; i++) {
            set.awardGamePointTo(0,1);
        }
        for (int i = 0; i < 24; i++) {
            set.awardGamePointTo(1,0);
        }

        set.awardGamePointTo(1, 0);
        String[] expectedPointScore = {"0", "1"};
        String[] actualPointScore = set.getCurrentGamePoints();
        Assertions.assertArrayEquals(expectedPointScore, actualPointScore);
    }

    @Test
    public void playerShouldWonSetWhenTiebreakEnd() {
        for (int i = 0; i < 24; i++) {
            set.awardGamePointTo(0, 1);
        }
        for (int i = 0; i < 24; i++) {
            set.awardGamePointTo(1, 0);
        }
        for (int i = 0; i < 6; i++) {
            set.awardGamePointTo(1, 0);
        }

        boolean isSetWon = set.awardGamePointTo(1, 0);
        Assertions.assertTrue(isSetWon);
    }


}
