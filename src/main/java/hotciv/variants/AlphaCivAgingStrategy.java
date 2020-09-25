package hotciv.variants;

public class AlphaCivAgingStrategy implements AgeStrategy{
    @Override
    public int advanceAge(int currentAge) {
        int yearsPerRound = 100;
        return currentAge + yearsPerRound;
    }
}