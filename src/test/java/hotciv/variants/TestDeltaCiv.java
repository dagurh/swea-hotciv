package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.variants.implementations.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class TestDeltaCiv {

    private Game game;
    private Position pos0_1, pos0_5, pos1_4, pos2_1, pos4_4, pos4_5, pos8_12;

    public void callEndOfTurn(int x){
        for (int i = 0; i < x; i++) {
            game.endOfTurn();
        }
    }

    /**
     * Fixture for alphaciv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlphaCivAgingStrategy(), new AlphaCivWinnerStrategy(), new AlphaCivActionStrategy(), new DeltaCivWorldLayoutStrategy(), new AlphaCivAttackStrategyImpl());
        pos0_1 = new Position(0,1);
        pos0_5 = new Position(0,5);
        pos1_4 = new Position(1,4);
        pos2_1 = new Position(2,1);
        pos4_4 = new Position(4,4);
        pos4_5 = new Position(4, 5);
        pos8_12 = new Position(8, 12);
    }
    @Test
    public void thereShouldBeOceanAt0_1(){
        assertThat(game.getTileAt(pos0_1), is(GameConstants.OCEANS));
    }

    @Test
    public void thereShouldBePlainsAt2_1(){
        assertThat(game.getTileAt(pos2_1), is(GameConstants.PLAINS));
    }

    @Test
    public void thereShouldBeForestAt4_4(){
        assertThat(game.getTileAt(pos4_4), is(GameConstants.FOREST));
    }
    @Test
    public void thereShouldBeMountainAt0_5(){
        assertThat(game.getTileAt(pos0_5), is(GameConstants.MOUNTAINS));
    }
    @Test
    public void thereShouldBeHillsAt1_4(){
        assertThat(game.getTileAt(pos1_4), is(GameConstants.HILLS));
    }
    @Test
    public void thereShouldBeRedCityAt8_12(){
        assertThat(game.getCityAt(pos8_12).getOwner(), is(Player.RED));
    }
    @Test
    public void thereShouldBeBlueCityAt4_5(){
        assertThat(game.getCityAt(pos4_5).getOwner(), is(Player.BLUE));
    }



}