package hotciv.variants.interfaces;

public interface AbstractFactory {

    ActionStrategy actionStrategy();

    AgeStrategy ageStrategy();

    AttackStrategy attackStrategy();

    WinnerStrategy winnerStrategy();

    WorldLayoutStrategy worldLayoutStrategy();

}
