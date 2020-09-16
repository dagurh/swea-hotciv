package hotciv.standard;

import hotciv.framework.*;
import java.util.*;
import java.util.HashMap;

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

  private static Position blueCityPos, redCityPos, mountainsPos, hillsPos, oceanPos, redArcherPos, blueLegionPos, redSettlerPos;
  private static City redCity, blueCity;
  private static Unit redArcher, blueLegion, redSettler;
  private int timePassed;
  private int age = -4000;
  private TileImpl mountainsTile, hillsTile, oceanTile;
  int turnCounter = 0; // Variable that determines whose turn it is
  Map<Position, City> cityMap = new HashMap<>(); // Hashmap to store cities and their positions
  Map<Position, Tile> tileMap = new HashMap<>(); // Hashmap to store tiles and their positions
  Map<Position, Unit> unitMap = new HashMap<>(); // Hashmap to store units and their positions

  // A method that calls the method makeAndAddCities
  public GameImpl(){
    makeAndAddCities();
    makeAndAddTiles();
    makeAndAddUnits();
  }

  public Unit getUnitAt( Position p ) { return unitMap.get(p); }
  public City getCityAt( Position p ) { return cityMap.get(p); }
  public String getTileAt( Position p ) { return tileMap.get(p).getTypeString(); }

  public void makeAndAddUnits(){
    redArcherPos = new Position(2, 0);
    redArcher = new UnitImpl("archer", Player.RED);
    blueLegionPos = new Position(3, 2);
    blueLegion = new UnitImpl("legion", Player.BLUE);
    redSettlerPos = new Position(4, 3);
    redSettler = new UnitImpl("settler", Player.RED);
    unitMap.put(redArcherPos, redArcher);
    unitMap.put(blueLegionPos, blueLegion);
    unitMap.put(redSettlerPos, redSettler);
  }

  // creates cities and their positions
  public void makeAndAddCities(){
    redCityPos = new Position(1, 1);
    blueCityPos = new Position(1, 4);
    redCity = new CityImpl(Player.RED);
    blueCity = new CityImpl(Player.BLUE);
    cityMap.put(redCityPos, redCity);
    cityMap.put(blueCityPos, blueCity);
  }

  // determines who's turn it is by using the timeCounter variable
  public Player getPlayerInTurn() {
    if (turnCounter == 0) {
      return Player.RED;
    } else {
      return Player.BLUE;
    }
  }

  public Player getWinner(){
    if(getAge() == -3000){
      return Player.RED;
    }
    return null;
  }

  // returns the current century
  public int getAge() {
    return age+timePassed;
  }

  public boolean moveUnit( Position from, Position to ) {
    int rowDiff = to.getRow()-from.getRow();
    int colDiff = to.getColumn()- from.getColumn();
    if (rowDiff >= -1
            && rowDiff <= 1
            && colDiff >= -1
            && colDiff <= 1
            && !getTileAt(to).equals(GameConstants.OCEANS)
            && !getTileAt(to).equals(GameConstants.MOUNTAINS)){
      Unit newUnit = getUnitAt(from);
      unitMap.remove(from);
      unitMap.put(to, newUnit);
      return true;
    }
    return false;
  }

  // Called when a player has ended their turn, if both players have ended their turn a new century begins
  public void endOfTurn() {
    turnCounter++;
    if(turnCounter == 2){
      turnCounter = 0;
      timePassed += 100;
    }
  }

  public void makeAndAddTiles() {
    for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
      for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
        tileMap.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
      }
      mountainsPos = new Position(2, 2);
      mountainsTile = new TileImpl(GameConstants.MOUNTAINS);
      tileMap.put(mountainsPos, mountainsTile);

      hillsPos = new Position(0, 1);
      hillsTile = new TileImpl(GameConstants.HILLS);
      tileMap.put(hillsPos, hillsTile);

      oceanPos = new Position(1, 0);
      oceanTile = new TileImpl(GameConstants.OCEANS);
      tileMap.put(oceanPos, oceanTile);
    }
  }


  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}

}


