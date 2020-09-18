package hotciv.standard;

import hotciv.framework.*;

import org.junit.jupiter.api.*;

import static hotciv.framework.Player.RED;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.*;
import java.util.zip.ZipEntry;

/** Skeleton class for AlphaCiv test cases

   Updated Aug 2020 for JUnit 5 includes
   Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphaCiv {
  private Game game;

  /**
   * Fixture for alphaciv testing.
   */
  @BeforeEach
  public void setUp() {
    game = new GameImpl();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(RED));
  }

  // test: should be a red city at position (1, 1)
  @Test
  public void shouldBeRedCityAt1_1() {
    Position city1_1 = new Position(1, 1);
    assertThat(game.getCityAt(city1_1).getOwner(), is(Player.RED));
  }

  // test: should be a blue city at position (1, 4)
  @Test
  public void shouldBeBlueCityAt1_4() {
    Position city1_4 = new Position(1, 4);
    assertThat(game.getCityAt(city1_4).getOwner(), is(Player.BLUE));
  }

  // test: should be the blue players turn after the red player
  @Test
  public void shouldBeBlueTurnAfterRedTurn() {
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  // test: all cities should have the population set to 1
  @Test
  public void shouldBeCitiesPopulationAlways1() {
    CityImpl city = new CityImpl(Player.RED);
    assertThat(city.getSize(), is(1));
  }

  // test: a city should produce 6 treasuries after each round (not after each turn)
  @Test
  public void shouldProduceSixAfterEndRound() {
    Position redCityPos = new Position(1, 1);
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getCityAt(redCityPos).getTreasury(), is(6));
  }

  // test: a city's production should be cumulative
  @Test
  public void ProductionShouldBeCumulative() {
    Position redCityPos = new Position(1, 1);
    assertThat(game.getCityAt(redCityPos).getTreasury(), is(0));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getCityAt(redCityPos).getTreasury(), is(6));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getCityAt(redCityPos).getTreasury(), is(12));
  }

  @Test
  public void RedWinsInYear3000BC() {
    // Calls endOfTurn() 20 times since there are two players, so 10 rounds pass and the year is now 3000 BC
    for (int i = 0; i < 20; i++) {
      game.endOfTurn();
    }
    assertThat(game.getWinner(), is(Player.RED));
  }

  // test: Nobody is the winner if the year is not 3000 BC
  @Test
  public void NobodyWinsIfYearIsNot3000BC() {
    // Calls endOfTurn() 18 times since there are two players, so 9 rounds pass and the year is now 3100 BC
    for (int i = 0; i < 18; i++) {
      game.endOfTurn();
    }
    assertNull(game.getWinner());
  }

  @Test
  public void ShouldBeMountainsAt2_2() {
    Position mountain2_2 = new Position(2, 2);
    assertThat(game.getTileAt(mountain2_2), is(GameConstants.MOUNTAINS));
  }

  @Test
  public void ShouldBeHillsAt0_1() {
    Position hills0_1 = new Position(0, 1);
    assertThat(game.getTileAt(hills0_1), is(GameConstants.HILLS));
  }

  @Test
  public void ShouldBeOceanAt1_0() {
    Position ocean1_0 = new Position(1, 0);
    assertThat(game.getTileAt(ocean1_0), is(GameConstants.OCEANS));
  }

  @Test
  public void RedHasArcherAt2_0() {
    Position RedArcher2_0 = new Position(2, 0);
    assertThat(game.getUnitAt(RedArcher2_0).getOwner(), is(Player.RED));
  }

  @Test
  public void BlueHasLegionAt3_2() {
    Position BlueLegion3_2 = new Position(3, 2);
    assertThat(game.getUnitAt(BlueLegion3_2).getOwner(), is(Player.BLUE));
  }

  @Test
  public void RedHasSettlerAt4_3() {
    Position RedSettler4_3 = new Position(4, 3);
    assertThat(game.getUnitAt(RedSettler4_3).getOwner(), is(Player.RED));
  }

  @Test
  public void unitCanMove() {
    Position pos4_4 = new Position(4, 4);
    Position pos4_3 = new Position(4, 3);
    game.moveUnit(pos4_3, pos4_4);
    assertThat(game.getUnitAt(pos4_4).getOwner(), is(Player.RED));
  }

  @Test
  public void UnitCannotMoveMoreThanOneTileAnyDirection() {
    Position pos3_2 = new Position(3, 2);
    Position pos2_0 = new Position(2, 0);
    assertFalse(game.moveUnit(pos3_2, pos2_0));
  }

  @Test
  public void unitCannotMoveMoreThan1TileInAnyDirection() {
    Position pos3_2 = new Position(3, 2);
    Position pos2_0 = new Position(2, 0);
    assertFalse(game.moveUnit(pos3_2, pos2_0));
  }

  @Test
  public void UnitCannotMoveOverMountains() {
    Position pos3_2 = new Position(3, 2);
    Position pos2_2 = new Position(2, 2);
    assertFalse(game.moveUnit(pos3_2, pos2_2));
  }

  @Test
  public void RedCannotMoveBluesUnit() {
    Position pos3_2 = new Position(3, 2);
    Position pos2_2 = new Position(2, 2);
    assertFalse(game.moveUnit(pos3_2, pos2_2));
  }

  @Test
  public void BlueCannotMoveRedsUnit() {
    Position pos4_3 = new Position(4, 3);
    Position pos4_4 = new Position(4, 4);
    game.endOfTurn();
    assertFalse(game.moveUnit(pos4_3, pos4_4));
  }

  @Test
  public void AfterEachTurnPlayerGainsSixProduction() {
    Position redCityPos = new Position(1, 1);
    Position blueCityPos = new Position(1, 4);
    assertThat(game.getCityAt(redCityPos).getTreasury(), is(0));
    assertThat(game.getCityAt(blueCityPos).getTreasury(), is(0));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getCityAt(redCityPos).getTreasury(), is(6));
    assertThat(game.getCityAt(blueCityPos).getTreasury(), is(6));
  }

  @Test
  public void TwoUnitsCannotStandOnTheSameTile() {
    Position pos20 = new Position(2, 0); //Position that contains a red archer
    Position pos31 = new Position(3, 1);
    Position pos42 = new Position(4, 2);
    Position pos43 = new Position(4, 3); //Position that contains a red settler
    game.moveUnit(pos20, pos31);
    game.moveUnit(pos31, pos42);
    assertFalse(game.moveUnit(pos42, pos43));
  }

  @Test
  public void RedsUnitAttacksAndDestroysBluesUnit() {
    Position pos3_2 = new Position(3, 2); //Position that contains a blue legion
    Position pos4_3 = new Position(4, 3); //Position that contains a red settler
    game.moveUnit(pos4_3, pos3_2);
    assertThat(game.getUnitAt(pos3_2).getOwner(), is(Player.RED));
  }

  @Test
  public void BluesUnitAttacksAndDestroysRedsUnit() {
    Position pos3_2 = new Position(3, 2); //Position that contains a blue legion
    Position pos4_3 = new Position(4, 3); //Position that contains a red settler
    game.endOfTurn();
    game.moveUnit(pos3_2, pos4_3);
    assertThat(game.getUnitAt(pos4_3).getOwner(), is(Player.BLUE));
  }

  @Test
  public void redCityCanChangeTypeOfUnitProduction() {
    Position redCityPos = new Position(1, 1);
    game.changeProductionInCityAt(redCityPos, "legion");
    assertThat(game.getCityAt(redCityPos).getProduction(), is("legion"));
  }

  @Test
  public void redCityCanChangeWorkForceFocus() {
    Position redCityPos = new Position(1, 1);
    game.changeWorkForceFocusInCityAt(redCityPos, "hammer");
    assertThat(game.getCityAt(redCityPos).getWorkforceFocus(), is("hammer"));
  }

  @Test
  public void blueCityCanChangeTypeOfUnitProduction() {
    Position blueCityPos = new Position(1, 4);
    game.changeProductionInCityAt(blueCityPos, "legion");
    assertThat(game.getCityAt(blueCityPos).getProduction(), is("legion"));
  }

  @Test
  public void blueCityCanChangeWorkForceFocus() {
    Position blueCityPos = new Position(1, 4);
    game.changeWorkForceFocusInCityAt(blueCityPos, "hammer");
    assertThat(game.getCityAt(blueCityPos).getWorkforceFocus(), is("hammer"));
  }

  @Test
  public void ArcherHas3defence(){
    Position pos2_0 = new Position(2, 0); //position of an archer unit
    assertThat(game.getUnitAt(pos2_0).getDefensiveStrength(), is(3));
  }

  @Test
  public void SettlerHas3defence(){
    Position pos4_3 = new Position(4, 3); //position of a settler unit
    assertThat(game.getUnitAt(pos4_3).getDefensiveStrength(), is(3));
  }

  @Test
  public void LegionHas2defence(){
    Position pos3_2 = new Position(3, 2); //position of a legion unit
    assertThat(game.getUnitAt(pos3_2).getDefensiveStrength(), is(2));
  }

  @Test
  public void ArcherHas2attack(){
    Position pos2_0 = new Position(2, 0); //position of an archer unit
    assertThat(game.getUnitAt(pos2_0).getAttackingStrength(), is(2));
  }

  @Test
  public void SettlerHas0attack(){
    Position pos4_3 = new Position(4, 3); //position of a settler unit
    assertThat(game.getUnitAt(pos4_3).getAttackingStrength(), is(0));
  }

  @Test
  public void LegionHas4attack(){
    Position pos3_2 = new Position(3, 2); //position of a legion unit
    assertThat(game.getUnitAt(pos3_2).getAttackingStrength(), is(4));
  }

  @Test
  public void ArcherCosts10(){
    Position pos2_0 = new Position(2, 0); //position of an archer unit
    UnitImpl redArcher = (UnitImpl) game.getUnitAt(pos2_0);
    assertThat(redArcher.getCost(), is(10));
  }

  @Test
  public void LegionCosts15(){
    Position pos3_2 = new Position(3, 2); //position of an archer unit
    UnitImpl blueLegion = (UnitImpl) game.getUnitAt(pos3_2);
    assertThat(blueLegion.getCost(), is(15));
  }

  @Test
  public void SettlerCosts30(){
    Position pos4_3 = new Position(4, 3); //position of an archer unit
    UnitImpl redSettler = (UnitImpl) game.getUnitAt(pos4_3);
    assertThat(redSettler.getCost(), is(30));
  }

  @Test
  public void RedCityCanGenerateAUnit(){
    Position pos0_1 = new Position(0, 1);
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getUnitAt(pos0_1).getOwner(), is(Player.RED));
  }

}