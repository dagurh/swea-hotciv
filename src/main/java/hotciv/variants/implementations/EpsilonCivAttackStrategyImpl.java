package hotciv.variants.implementations;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.utility.Utility;
import hotciv.variants.interfaces.AttackStrategy;
import hotciv.utility.Utility2;
import java.util.Random;

public class EpsilonCivAttackStrategyImpl implements AttackStrategy {

    private int attackersTerrainFactor;
    private int defendersTerrainFactor;
    private int attackersSupportFactor;
    private int defendersSupportFactor;
    private int combinedAttackerStrength;
    private int combinedDefenceStrength;
    private Random randomAtt = new Random();
    private Random randomDef = new Random();
    private int eyesAtt;
    private int eyesDef;

    @Override
    public boolean unitBattle(GameImpl game, Position attacker, Position defender) {
        attackersTerrainFactor = Utility2.getTerrainFactor(game, attacker);
        attackersSupportFactor = Utility2.getFriendlySupport(game, attacker, game.getUnitAt(attacker).getOwner());

        defendersTerrainFactor = Utility2.getTerrainFactor(game, defender);
        defendersSupportFactor = Utility2.getFriendlySupport(game, defender, game.getUnitAt(defender).getOwner());

        combinedAttackerStrength = (game.getUnitAt(attacker).getAttackingStrength() + attackersSupportFactor) * attackersTerrainFactor;
        combinedDefenceStrength = (game.getUnitAt(defender).getDefensiveStrength() + defendersSupportFactor) * defendersTerrainFactor;

        eyesAtt = randomAtt.nextInt(6) + 1;
        eyesDef = randomDef.nextInt(6) + 1;

        return combinedAttackerStrength * eyesAtt > combinedDefenceStrength * eyesDef;
    }
}
