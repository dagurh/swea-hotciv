package hotciv.variants.implementations;

import hotciv.variants.interfaces.AgeStrategy;

public class AlphaCivAgingStrategy implements AgeStrategy {
    @Override
    public int advanceAge(int currentAge) {
        int yearsPerRound = 100;
        return currentAge + yearsPerRound;
    }
}