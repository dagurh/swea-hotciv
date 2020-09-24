package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.AlphaAging;
import hotciv.variants.AlphaWinner;
import org.junit.jupiter.api.*;

import static hotciv.framework.Player.RED;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
  private Position pos0_1, pos1_0, pos1_1, pos4_1, pos2_0, pos2_1, pos2_2, pos3_1, pos3_2, pos4_2, pos4_3, pos4_4;
  private int x;

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
    game = new GameImpl(new AlphaAging(), new AlphaWinner());
    pos0_1 = new Position(0,1);
    pos1_0 = new Position(1,0);
    pos1_1 = new Position(1,1);
    pos2_0 = new Position(2,0);
    pos2_1 = new Position(2,1);
    pos2_2 = new Position(2,2);
    pos3_1 = new Position(3,1);
    pos3_2 = new Position(3,2);
    pos4_1 = new Position(4,1);
    pos4_2 = new Position(4,2);
    pos4_3 = new Position(4,3);
    pos4_4 = new Position(4,4);
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(RED));
  }

  // test: should be a red city at position (1, 1)
  @Test
  public void shouldBeRedCityAt1_1() {
    assertThat(game.getCityAt(pos1_1).getOwner(), is(Player.RED));
  }

  // test: should be a blue city at position (4, 1)
  @Test
  public void shouldBeBlueCityAt4_1() {
    assertThat(game.getCityAt(pos4_1).getOwner(), is(Player.BLUE));
  }

  // test: should be the blue players turn after the red player
  @Test
  public void shouldBeBlueTurnAfterRedTurn() {
    callEndOfTurn(1);
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
    callEndOfTurn(2);
    assertThat(game.getCityAt(pos1_1).getTreasury(), is(6));
  }

  // test: a city's production should be cumulative
  @Test
  public void ProductionShouldBeCumulative() {
    game.changeProductionInCityAt(pos1_1, "settler"); // Changed to settler so city doesnt produce archer
    assertThat(game.getCityAt(pos1_1).getTreasury(), is(0));
    callEndOfTurn(2);
    assertThat(game.getCityAt(pos1_1).getTreasury(), is(6));
    callEndOfTurn(2);
    assertThat(game.getCityAt(pos1_1).getTreasury(), is(12));
  }

  @Test
  public void RedWinsInYear3000BC() {
    // Calls endOfTurn() 20 times since there are two players, so 10 rounds pass and the year is now 3000 BC
    callEndOfTurn(20);
    assertThat(game.getWinner(), is(Player.RED));
  }

  // test: Nobody is the winner if the year is not 3000 BC
  @Test
  public void NobodyWinsIfYearIsNot3000BC() {
    // Calls endOfTurn() 18 times since there are two players, so 9 rounds pass and the year is now 3100 BC
    callEndOfTurn(18);
    assertNull(game.getWinner());
  }

  @Test
  public void ShouldBeMountainsAt2_2() {
    assertThat(game.getTileAt(pos2_2), is(GameConstants.MOUNTAINS));
  }

  @Test
  public void ShouldBeHillsAt0_1() {
    assertThat(game.getTileAt(pos0_1), is(GameConstants.HILLS));
  }

  @Test
  public void ShouldBeOceanAt1_0() {
    assertThat(game.getTileAt(pos1_0), is(GameConstants.OCEANS));
  }

  @Test
  public void RedHasArcherAt2_0() {
    assertThat(game.getUnitAt(pos2_0).getOwner(), is(Player.RED));
  }

  @Test
  public void BlueHasLegionAt3_2() {
    assertThat(game.getUnitAt(pos3_2).getOwner(), is(Player.BLUE));
  }

  @Test
  public void RedHasSettlerAt4_3() {
    assertThat(game.getUnitAt(pos4_3).getOwner(), is(Player.RED));
  }

  @Test
  public void unitCanMove() {
    game.moveUnit(pos4_3, pos4_4);
    assertThat(game.getUnitAt(pos4_4).getOwner(), is(Player.RED));
  }

  @Test
  public void UnitCannotMoveMoreThanOneTileAnyDirection() {
    assertFalse(game.moveUnit(pos3_2, pos2_0));
  }

  @Test
  public void unitCannotMoveMoreThan1TileInAnyDirection() {
    assertFalse(game.moveUnit(pos3_2, pos2_0));
  }

  @Test
  public void UnitCannotMoveOverMountains() {
    assertFalse(game.moveUnit(pos3_2, pos2_2));
  }

  @Test
  public void UnitCannotMoveOverOceans() {
    assertFalse(game.moveUnit(pos2_0, pos1_0));
  }

  @Test
  public void RedCannotMoveBluesUnit() {
    assertFalse(game.moveUnit(pos3_2, pos2_2));
  }

  @Test
  public void BlueCannotMoveRedsUnit() {
    callEndOfTurn(1);
    assertFalse(game.moveUnit(pos4_3, pos4_4));
  }

  @Test
  public void AfterEachTurnPlayerGainsSixProduction() {
    assertThat(game.getCityAt(pos1_1).getTreasury(), is(0));
    assertThat(game.getCityAt(pos4_1).getTreasury(), is(0));
    callEndOfTurn(2);
    assertThat(game.getCityAt(pos1_1).getTreasury(), is(6));
    assertThat(game.getCityAt(pos4_1).getTreasury(), is(6));
  }

  @Test
  public void TwoUnitsCannotStandOnTheSameTile() {
    game.moveUnit(pos2_0, pos3_1);
    callEndOfTurn(2);
    game.moveUnit(pos3_1, pos4_2);
    callEndOfTurn(2);
    assertFalse(game.moveUnit(pos4_2, pos4_3));
  }

  @Test
  public void RedsUnitAttacksAndDestroysBluesUnit() {
    game.moveUnit(pos4_3, pos3_2);
    assertThat(game.getUnitAt(pos3_2).getOwner(), is(Player.RED));
  }

  @Test
  public void BluesUnitAttacksAndDestroysRedsUnit() {
    callEndOfTurn(1);
    game.moveUnit(pos3_2, pos4_3);
    assertThat(game.getUnitAt(pos4_3).getOwner(), is(Player.BLUE));
  }

  @Test
  public void redCityCanChangeTypeOfUnitProduction() {
    game.changeProductionInCityAt(pos1_1, "legion");
    assertThat(game.getCityAt(pos1_1).getProduction(), is("legion"));
  }

  @Test
  public void redCityCanChangeWorkForceFocus() {
    game.changeWorkForceFocusInCityAt(pos1_1, "hammer");
    assertThat(game.getCityAt(pos1_1).getWorkforceFocus(), is("hammer"));
  }

  @Test
  public void blueCityCanChangeTypeOfUnitProduction() {
    game.changeProductionInCityAt(pos4_1, "legion");
    assertThat(game.getCityAt(pos4_1).getProduction(), is("legion"));
  }

  @Test
  public void blueCityCanChangeWorkForceFocus() {
    game.changeWorkForceFocusInCityAt(pos4_1, "hammer");
    assertThat(game.getCityAt(pos4_1).getWorkforceFocus(), is("hammer"));
  }

  @Test
  public void ArcherHas3defence(){
    assertThat(game.getUnitAt(pos2_0).getDefensiveStrength(), is(3));
  }

  @Test
  public void SettlerHas3defence(){
    assertThat(game.getUnitAt(pos4_3).getDefensiveStrength(), is(3));
  }

  @Test
  public void LegionHas2defence(){
    assertThat(game.getUnitAt(pos3_2).getDefensiveStrength(), is(2));
  }

  @Test
  public void ArcherHas2attack(){
    assertThat(game.getUnitAt(pos2_0).getAttackingStrength(), is(2));
  }

  @Test
  public void SettlerHas0attack(){
    assertThat(game.getUnitAt(pos4_3).getAttackingStrength(), is(0));
  }

  @Test
  public void LegionHas4attack(){
    assertThat(game.getUnitAt(pos3_2).getAttackingStrength(), is(4));
  }

  @Test
  public void ArcherCosts10(){
    UnitImpl redArcher = (UnitImpl) game.getUnitAt(pos2_0);
    assertThat(redArcher.getCost(), is(10));
  }

  @Test
  public void LegionCosts15(){
    UnitImpl blueLegion = (UnitImpl) game.getUnitAt(pos3_2);
    assertThat(blueLegion.getCost(), is(15));
  }

  @Test
  public void SettlerCosts30(){
    UnitImpl redSettler = (UnitImpl) game.getUnitAt(pos4_3);
    assertThat(redSettler.getCost(), is(30));
  }

  @Test
  public void CityCanGenerateAnArcher(){
    assertThat(game.getCityAt(pos1_1).getProduction(), is("archer"));
    callEndOfTurn(4);
    assertThat(game.getUnitAt(pos1_1).getTypeString(), is("archer"));
    assertThat(game.getCityAt(pos1_1).getTreasury(),is(2));
  }

  @Test
  public void CityCanGenerateALegion(){
    game.changeProductionInCityAt(pos1_1, "legion");
    callEndOfTurn(6);
    assertThat(game.getUnitAt(pos1_1).getTypeString(), is("legion"));
    assertThat(game.getCityAt(pos1_1).getTreasury(),is(3));
  }

  @Test
  public void CityCanGenerateASettler(){
    game.changeProductionInCityAt(pos1_1, "settler");
    callEndOfTurn(10);
    assertThat(game.getUnitAt(pos1_1).getTypeString(), is("settler"));
    assertThat(game.getCityAt(pos1_1).getTreasury(),is(0));
  }

  @Test
  public void CityCanChangeProduction() {
    game.changeProductionInCityAt(pos1_1, "archer");
    assertThat(game.getCityAt(pos1_1).getProduction(), is("archer"));
  }

  @Test
  public void UnitsCannotMoveTwoTilesPerRound(){
    game.moveUnit(pos2_0, pos3_1);
    assertFalse(game.moveUnit(pos3_1, pos2_0));
  }

  //if a unit moves into an enemy city and there is no enemy unit standing on the city, the ownership changes
  @Test
  public void UnitsCanConquerCities(){
    callEndOfTurn(1);
    game.moveUnit(pos3_2, pos2_1);
    callEndOfTurn(2);
    game.moveUnit(pos2_1, pos1_1);
    assertThat(game.getCityAt(pos1_1).getOwner(), is(Player.BLUE));
  }

}