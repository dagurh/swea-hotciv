package hotciv.variants.interfaces;

import hotciv.framework.GameObserver;

public interface AbstractFactory {

    ActionStrategy actionStrategy();

    AgeStrategy ageStrategy();

    AttackStrategy attackStrategy();

    WinnerStrategy winnerStrategy();

    WorldLayoutStrategy worldLayoutStrategy();

    GameObserver observer();
}
