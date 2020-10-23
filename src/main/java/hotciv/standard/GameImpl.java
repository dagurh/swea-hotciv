package hotciv.standard;

import hotciv.framework.*;
import hotciv.utility.Utility;
import hotciv.variants.interfaces.*;


import java.util.HashMap;
import java.util.Map;

/** Skeleton implementation of HotCiv.

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


public class GameImpl implements Game {

  private static Position blueCityPos;
  private static Position redCityPos;
  private int blueAttackWinCounter, redAttackWinCounter;
  private int age = GameConstants.AGE;
  private AgeStrategy ageStrategy;
  private WinnerStrategy winnerStrategy;
  private ActionStrategy actionStrategy;
  private WorldLayoutStrategy worldLayoutStrategy;
  private AttackStrategy attackStrategy;
  private Player playerInTurn = Player.RED; // Variable that determines whose turn it is
  Map<Position, City> cityMap = new HashMap<>(); // Hashmap to store cities and their positions
  Map<Position, Tile> tileMap = new HashMap<>(); // Hashmap to store tiles and their positions
  Map<Position, Unit> unitMap = new HashMap<>(); // Hashmap to store units and their positions

  // A method that calls the method makeAndAddCities
  public GameImpl(AbstractFactory abstractFactory){
    ageStrategy = abstractFactory.ageStrategy();
    winnerStrategy = abstractFactory.winnerStrategy();
    actionStrategy = abstractFactory.actionStrategy();
    worldLayoutStrategy = abstractFactory.worldLayoutStrategy();
    attackStrategy = abstractFactory.attackStrategy();

    makeAndAddCities();
    makeAndAddTiles();
    makeAndAddUnits();
  }

  public Unit getUnitAt( Position p ) { return unitMap.get(p); }
  public City getCityAt( Position p ) { return cityMap.get(p); }
  public String getTileAt( Position p ) { return tileMap.get(p).getTypeString(); }

  public void makeAndAddUnits(){
    Position redArcherPos = new Position(2, 0);
    Unit redArcher = new UnitImpl("archer", Player.RED);
    Position blueLegionPos = new Position(3, 2);
    Unit blueLegion = new UnitImpl("legion", Player.BLUE);
    Position redSettlerPos = new Position(4, 3);
    Unit redSettler = new UnitImpl("settler", Player.RED);
    unitMap.put(redArcherPos, redArcher);
    unitMap.put(blueLegionPos, blueLegion);
    unitMap.put(redSettlerPos, redSettler);
  }

  // creates cities and their positions
  public void makeAndAddCities(){
    redCityPos = new Position(1, 1);
    blueCityPos = new Position(4, 1);
    City redCity = new CityImpl(Player.RED);
    City blueCity = new CityImpl(Player.BLUE);
    cityMap.put(redCityPos, redCity);
    cityMap.put(blueCityPos, blueCity);
  }

  // creates tiles and their position
  public void makeAndAddTiles() {
    worldLayoutStrategy.createWorld(this);
  }

  // determines who's turn it is by using the timeCounter variable
  public Player getPlayerInTurn() {
    return playerInTurn;
    }


  public Player getWinner(){
    return winnerStrategy.determineWinner(this);
  }

  // returns the current century
  public int getAge() {
    return age;
  }

  public boolean moveUnit( Position from, Position to ) {
    if (!moveLegal(from, to)) return false;
    if (isEnemyUnitOnTo(from, to)) {
      if(!resultOfAttack(from, to)) { removeUnit(from); } else { incrementSuccessfulAttacks(from); }
    }
    if (isCityUnderAttack(from, to)) changeOwnershipOfCity(to, getUnitAt(from).getOwner());
    moveUnitToNewPos(from, to);
    return true;
  }

  public void incrementSuccessfulAttacks(Position from){
    if(getUnitAt(from).getOwner() == Player.BLUE) {
      blueAttackWinCounter++;
    } else { redAttackWinCounter++; }
  }

  private boolean isCityUnderAttack(Position from, Position to) {
    if (getCityAt(to) != null) {
      if(!getUnitAt(from).getOwner().equals(getCityAt(to).getOwner())) {
        return true;
      }
    }
    return false;
  }

  private boolean isEnemyUnitOnTo(Position from, Position to) {
    if (getUnitAt(to) != null){
      if(!getUnitAt(from).getOwner().equals(getUnitAt(to).getOwner())){
        return true;
      }
    }
    return false;
  }


  public boolean moveLegal(Position from, Position to){
    int rowDiff = to.getRow()-from.getRow();
    int colDiff = to.getColumn()- from.getColumn();

    boolean isOneTileInAnyDirection = rowDiff >= -1
            && rowDiff <= 1
            && colDiff >= -1
            && colDiff <= 1;
    if(!isOneTileInAnyDirection) return false;

    boolean isPlayerInTurn = getPlayerInTurn().equals(getUnitAt(from).getOwner());
    if(!isPlayerInTurn) return false;

    if(!isLegalTerrain(to)) return false;

    boolean bothUnitsHaveTheSameOwner = getUnitAt(to) == null || getUnitAt(from).getOwner() != getUnitAt(to).getOwner();
    if(!bothUnitsHaveTheSameOwner) return false;

    boolean unitHasMoveCount = getUnitAt(from).getMoveCount() > 0;
    if(!unitHasMoveCount) return false;

    return true;
  }

  private boolean isLegalTerrain(Position to) {
    return !getTileAt(to).equals(GameConstants.OCEANS)
            && !getTileAt(to).equals(GameConstants.MOUNTAINS);
  }

  public boolean resultOfAttack(Position attacker, Position defender){
    return attackStrategy.unitBattle(this, attacker, defender);
  }

  public void moveUnitToNewPos(Position from, Position to){
    if(getUnitAt(from) != null) {
      Unit newUnit = getUnitAt(from);
      unitMap.remove(from);
      unitMap.put(to, newUnit);
    }
    if(getUnitAt(to) != null) {
      changeMoveCountForUnitAt(to);
    }
  }


  // Called when a player has ended their turn, if both players have ended their turn a new century begins
  public void endOfTurn() {
    if(playerInTurn == Player.RED){
      playerInTurn = Player.BLUE; }
     else {
       playerInTurn = Player.RED;
       endOfRound();
    }
    }

  public void endOfRound() {
    CityImpl redCity = (CityImpl) cityMap.get(GameImpl.redCityPos);
    CityImpl blueCity = (CityImpl) cityMap.get(GameImpl.blueCityPos);
    advAge();
    winnerStrategy.incrementRound();
    redCity.addTreasury(6);
    blueCity.addTreasury(6);
    resetMoveCount();
    if(redCity.canProduceUnit()) {
      produceUnit(redCity.getProduction(), redCityPos);
    }
    if (blueCity.canProduceUnit()) {
      produceUnit(blueCity.getProduction(), blueCityPos);
    }
  }

  public void advAge(){
   age = ageStrategy.advanceAge(age);
  }

  public void resetMoveCount(){
    for (Unit u : unitMap.values()){
      UnitImpl unitTemp = (UnitImpl) u;
      unitTemp.setMoveCount();
    }
  }

  public void changeOwnershipOfCity(Position p, Player newOwner){
    CityImpl City = (CityImpl) getCityAt(p);
    City.setOwner(newOwner);
  }

  public void changeMoveCountForUnitAt(Position p){
      UnitImpl Unit = (UnitImpl) getUnitAt(p);
      Unit.decreaseMoveCount();
  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {
    CityImpl City = (CityImpl) getCityAt(p);
    City.changeWorkForce(balance);
  }

  public void changeUnitsDefensiveStrength(Position p){
    UnitImpl Unit = (UnitImpl) getUnitAt(p);
    Unit.setDefensiveStrength();
  }

  public void changeProductionInCityAt( Position p, String unitType ) {
    CityImpl City = (CityImpl) getCityAt(p);
    City.changeProduction(unitType);
  }

  public void produceUnit(String unitType, Position cityPosition){
    if(getUnitAt(cityPosition) == null) {
      UnitImpl newUnit = createUnit(unitType, cityPosition);
      addUnit(cityPosition, newUnit);
    } else {
      for (Position p : Utility.get8neighborhoodOf(cityPosition)) {
        if (getUnitAt(p) == null
                && isLegalTerrain(p)) {
          UnitImpl newUnit = createUnit(unitType, cityPosition);
          addUnit(p, newUnit);
        }
      }
    }
  }

  private UnitImpl createUnit(String unitType, Position cityPosition) {
    return new UnitImpl(unitType, getCityAt(cityPosition).getOwner());
  }

  public void performUnitActionAt( Position p ) { actionStrategy.unitAction(this, p); }

  public void addCity(Position p, CityImpl newCity) {
    cityMap.put(p, newCity);
  }

  public void addTile(Position p, TileImpl newTile) { tileMap.put(p, newTile); }

  public void removeUnit(Position p) {
    unitMap.remove(p);
  }

  public void addUnit(Position p, UnitImpl newUnit) {
    unitMap.put(p, newUnit);
  }

  public int getBlueAttackWinCounter() {
    return blueAttackWinCounter;
  }

  public int getRedAttackWinCounter() {
    return redAttackWinCounter;
  }

  public void addPopulation(Position p, int size){
    CityImpl City = (CityImpl) getCityAt(p);
    City.setSize(size);
  }

}
