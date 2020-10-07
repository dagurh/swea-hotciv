package hotciv.variants.implementations;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.utility.Utility;
import hotciv.variants.interfaces.AttackStrategy;
import hotciv.utility.Utility2;
import hotciv.variants.interfaces.DieStrategy;

import java.util.Random;

public class EpsilonCivAttackStrategyImpl implements AttackStrategy {

    private int attackersTerrainFactor;
    private int defendersTerrainFactor;
    private int attackersSupportFactor;
    private int defendersSupportFactor;
    private int combinedAttackerStrength;
    private int combinedDefenceStrength;
    private int eyesAtt;
    private int eyesDef;
    private DieStrategy dieStrategy;

    public EpsilonCivAttackStrategyImpl(DieStrategy dieStrategy){
        this.dieStrategy = dieStrategy;
    }

    @Override
    public boolean unitBattle(GameImpl game, Position attacker, Position defender) {
        attackersTerrainFactor = Utility2.getTerrainFactor(game, attacker);
        attackersSupportFactor = Utility2.getFriendlySupport(game, attacker, game.getUnitAt(attacker).getOwner());

        defendersTerrainFactor = Utility2.getTerrainFactor(game, defender);
        defendersSupportFactor = Utility2.getFriendlySupport(game, defender, game.getUnitAt(defender).getOwner());

        combinedAttackerStrength = (game.getUnitAt(attacker).getAttackingStrength() + attackersSupportFactor) * attackersTerrainFactor;
        combinedDefenceStrength = (game.getUnitAt(defender).getDefensiveStrength() + defendersSupportFactor) * defendersTerrainFactor;

        eyesAtt = dieStrategy.determineDie(game);
        eyesDef = dieStrategy.determineDie(game);

        return combinedAttackerStrength * eyesAtt > combinedDefenceStrength * eyesDef;
    }
}
