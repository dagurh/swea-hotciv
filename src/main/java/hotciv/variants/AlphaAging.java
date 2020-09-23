package hotciv.variants;

public class AlphaAging implements AgeStrategy{
    @Override
    public int advanceAge(int currentAge) {
        int yearsPerRound = 100;
        return currentAge + yearsPerRound;
    }
}