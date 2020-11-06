package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.variants.factories.BetaCivFactory;
import hotciv.variants.factories.FractalMapFactory;
import hotciv.variants.implementations.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class TestAdapterFractalMap {

    private Game game;
    private Position pos0_0;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new FractalMapFactory());
        pos0_0 = new Position(0,0);
    }

    @Test
    void testThatFractalMapGeneratesRandomWorldLayout(){
        HashSet<String> tileSet = new HashSet<>();
        for (int i = 0; i < 25; i++){
            game = new GameImpl(new FractalMapFactory());
            String tile = game.getTileAt(pos0_0);
            tileSet.add(tile);;
        }
        assertThat(tileSet.size(), not(1));
    }

}
