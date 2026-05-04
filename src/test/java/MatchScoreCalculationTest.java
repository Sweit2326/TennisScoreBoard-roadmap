import com.roadmap.fourth.model.MatchScore;
import com.roadmap.fourth.model.Player;
import com.roadmap.fourth.service.MatchScoreCalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchScoreCalculationTest {
    private final MatchScoreCalculationService MATCH_SCORE_CALCULATION_SERVICE = new MatchScoreCalculationService();

    private Player firstPlayer;
    private Player secondPlayer;
    private MatchScore match;

    @BeforeEach
    public void prepareTestMatch() {
        firstPlayer = new Player(0, "John");
        secondPlayer = new Player(0, "Jane");
        match = new MatchScore(firstPlayer, secondPlayer);
    }

    @Test
    public void gameShouldBeWon() {
        match.setPoints(0, 40);
        MATCH_SCORE_CALCULATION_SERVICE.pointWonBy(match, 0);

        Assertions.assertEquals(1, match.getGames(0));
    }
    @Test
    public void setShouldBeWon() {
        match.setPoints(0, 40);
        match.setGames(0, 6);
        MATCH_SCORE_CALCULATION_SERVICE.pointWonBy(match, 0);

        Assertions.assertEquals(1, match.getSets(0));
    }
    @Test
    public void gameWontEndWhenDeuce() {
        match.setPoints(0, 40);
        match.setPoints(1, 40);
        MATCH_SCORE_CALCULATION_SERVICE.pointWonBy(match, 0);

        Assertions.assertEquals(0, match.getGames(0));
        Assertions.assertEquals(0, match.getGames(1));
    }
    @Test
    public void tiebreakShouldBeStarted() {
        match.setPoints(0, 40);
        match.setGames(0, 5);
        match.setGames(1, 6);
        MATCH_SCORE_CALCULATION_SERVICE.pointWonBy(match, 0);

        Assertions.assertTrue(match.isTieBreak());
    }
    @Test
    public void tiebreakShouldBeStartedAfterDeuce() {
        match.setPoints(0, 41);
        match.setPoints(1, 40);
        match.setGames(0, 5);
        match.setGames(1, 6);
        MATCH_SCORE_CALCULATION_SERVICE.pointWonBy(match, 0);

        Assertions.assertTrue(match.isTieBreak());
    }
}
