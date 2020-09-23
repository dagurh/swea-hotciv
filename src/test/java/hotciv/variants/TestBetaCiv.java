package hotciv.variants;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class TestBetaCiv {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new BetaAging(), new BetaWinner());
    }
}