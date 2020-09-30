package hotciv.variants.implementations;

import hotciv.variants.interfaces.AgeStrategy;

public class BetaCivAgingStrategy implements AgeStrategy {
    @Override
    public int advanceAge(int currentAge) {
        if (-4000 <= currentAge && -100 > currentAge) {
            int yearsPerRound = 100;
            return currentAge + yearsPerRound;
        } else if (-100 <= currentAge && -1 > currentAge) {
            int yearsPerRound = 99;
            return currentAge + yearsPerRound;
        } else if (-1 <= currentAge && 1 > currentAge) {
            int yearsPerRound = 2;
            return currentAge + yearsPerRound;
        } else if (1 <= currentAge && 50 > currentAge) {
            int yearsPerRound = 49;
            return currentAge + yearsPerRound;
        } else if (50 <= currentAge && 1750 > currentAge) {
            int yearsPerRound = 50;
            return currentAge + yearsPerRound;
        } else if (1750 <= currentAge && 1900 > currentAge) {
            int yearsPerRound = 25;
            return currentAge + yearsPerRound;
        } else if (1900 <= currentAge && 1970 > currentAge) {
            int yearsPerRound = 5;
            return currentAge + yearsPerRound;
        } else {
            int yearsPerRound = 1;
            return currentAge + yearsPerRound;
        }
    }
}










































 /*if (-4000 <= currentAge && -100 > currentAge) {
         int yearsPerRound = 100;
         return currentAge + yearsPerRound;
         } else if (-100 <= currentAge && -1 > currentAge) {
         int yearsPerRound = 99;
         return currentAge + yearsPerRound;
         } else if (-1 <= currentAge && 1 > currentAge) {
         int yearsPerRound = 2;
         return currentAge + yearsPerRound;
         } else if (1 <= currentAge && 50 > currentAge) {
         int yearsPerRound = 49;
         return currentAge + yearsPerRound;
         } else if (50 <= currentAge && 1750 > currentAge) {
         int yearsPerRound = 50;
         return currentAge + yearsPerRound;
         } else if (1750 <= currentAge && 1900 > currentAge) {
         int yearsPerRound = 25;
         return currentAge + yearsPerRound;
         } else if (1900 <= currentAge && 1970 > currentAge) {
         int yearsPerRound = 5;
         return currentAge + yearsPerRound;
         } else {
         int yearsPerRound = 1;
         return currentAge + yearsPerRound;
         }
         */
