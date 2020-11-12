package hotciv.variants.implementations;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class ObserverSpy implements GameObserver {

   private Position currentPosition;
   private Player playerInTurn;
   private int currentAge;

    @Override
    public void worldChangedAt(Position pos) {
        currentPosition = pos;
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        playerInTurn = nextPlayer;
        currentAge = age;
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        currentPosition = position;
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public void setPlayerInTurn(Player playerInTurn) {
        this.playerInTurn = playerInTurn;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
}
