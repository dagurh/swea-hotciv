package hotciv.variants.implementations;

import hotciv.standard.GameImpl;
import hotciv.variants.interfaces.DieStrategy;
import java.util.Random;

public class RandomDieStrategy implements DieStrategy {

    private Random random = new Random();

    @Override
    public int determineDie(GameImpl game) {
        return random.nextInt(6) + 1;
    }
}
