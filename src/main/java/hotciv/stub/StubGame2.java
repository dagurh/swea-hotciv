package hotciv.stub;

import hotciv.framework.*;
import hotciv.standard.CityImpl;

import java.util.*;

/** Test stub for game for visual testing of
 * minidraw based graphics.
 *
 * SWEA support code.
 *
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

public class StubGame2 implements Game {

  // === Unit handling ===
  private Position pos_archer_red;
  private Position pos_legion_blue;
  private Position pos_settler_red;
  private Position pos_thetaciv_unit;

  private Unit red_archer;
  private int currentAge = GameConstants.AGE;
  private int endCount;

  public Unit getUnitAt(Position p) {
    if ( p.equals(pos_archer_red) ) {
      return red_archer;
    }
    if ( p.equals(pos_settler_red) ) {
      return new StubUnit( GameConstants.SETTLER, Player.RED );
    }
    if ( p.equals(pos_legion_blue) ) {
      return new StubUnit( GameConstants.LEGION, Player.BLUE );
    }
    if ( p.equals(pos_thetaciv_unit) ) {
      return new StubUnit( ThetaConstants.CARAVAN, Player.RED );
    }
    return null;
  }

  // Stub only allows moving red archer
  public boolean moveUnit( Position from, Position to ) { 
    System.out.println( "-- StubGame2 / moveUnit called: "+from+"->"+to );
    if ( from.equals(pos_archer_red) ) {
      pos_archer_red = to;
    }
    // notify our observer(s) about the changes on the tiles
    gameObserver.worldChangedAt(from);
    gameObserver.worldChangedAt(to);
    return true; 
  }

  // === Turn handling ===
  private Player inTurn;
  public void endOfTurn() {
    System.out.println( "-- StubGame2 / endOfTurn called." );
    inTurn = (getPlayerInTurn() == Player.RED ?
              Player.BLUE : 
              Player.RED );
    endCount += 1;
    if (endCount == 2){
      currentAge += 100;
      endCount = 0;
    }
    gameObserver.turnEnds(inTurn, getAge());
  }
  public Player getPlayerInTurn() { return inTurn; }



  // === Observer handling ===
  protected GameObserver gameObserver;
  // observer list is only a single one...
  public void addObserver(GameObserver observer) {
    gameObserver = observer;
  } 

  public StubGame2() { 
    defineWorld(1);
    defineCitiesStub2();
    // AlphaCiv configuration
    pos_archer_red = new Position( 2, 0);
    pos_legion_blue = new Position( 3, 2);
    pos_settler_red = new Position( 4, 3);
    pos_thetaciv_unit = new Position( 6, 4);

    // the only one I need to store for this stub
    red_archer = new StubUnit( GameConstants.ARCHER, Player.RED );   

    inTurn = Player.RED;
  }

  // A simple implementation to draw the map of DeltaCiv
  protected Map<Position,Tile> world; 
  public String getTileAt( Position p ) { return world.get(p).getTypeString(); }

  protected Map<Position, City> citiesStub2;
  public City getCityAt( Position p ) { return citiesStub2.get(p); }

  /** define the world.
   * @param worldType 1 gives one layout while all other
   * values provide a second world layout.
   */
  protected void defineWorld(int worldType) {
    world = new HashMap<>();
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        Position p = new Position(r,c);
        world.put( p, new StubTile(GameConstants.PLAINS));
      }
    }
    // Create a little area around the theta unit of special terrain
    world.put(new Position(6,4), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(6,5), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(7,4), new StubTile(ThetaConstants.DESERT));
  }

  protected void defineCitiesStub2() {
    citiesStub2 = new HashMap<>();
    citiesStub2.put(new Position(5, 5), new StubCity(Player.RED));
    citiesStub2.put(new Position(2, 1), new StubCity(Player.BLUE));
  }


  // TODO: Add more stub behaviour to test MiniDraw updating
  public Player getWinner() {
    if (getAge() == -3500) {
      return Player.RED;
    }
    return null;
  }

  public int getAge() { return currentAge; }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}  

  public void setTileFocus(Position position) {
    gameObserver.tileFocusChangedAt(position);
  }

}

class StubUnit implements  Unit {
  private String type;
  private Player owner;
  public StubUnit(String type, Player owner) {
    this.type = type;
    this.owner = owner;
  }
  public String getTypeString() { return type; }
  public Player getOwner() { return owner; }
  public int getMoveCount() { return 1; }
  public int getDefensiveStrength() { return 0; }
  public int getAttackingStrength() { return 0; }
}

class StubCity implements City {

  private Player owner;
  int money = 0;
  private String production;
  private String workforce;
  private int population = 1;

  public StubCity(Player owner){
    this.owner = owner;
  }

  @Override
  public Player getOwner() {
    return owner;
  }

  @Override
  public int getSize() {
    return population;
  }

  @Override
  public int getTreasury() {
    return money;
  }

  @Override
  public String getProduction() {
    return GameConstants.ARCHER;
  }

  @Override
  public String getWorkforceFocus() {
    return GameConstants.foodFocus;
  }
}
