package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.battle.BattleAbility;

public abstract class BattleAbilityEffect extends BattleAbility {
    public abstract void decorate(BattleAbility battleAbility);
}