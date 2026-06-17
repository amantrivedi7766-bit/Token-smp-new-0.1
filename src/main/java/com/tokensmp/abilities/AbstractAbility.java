package com.tokensmp.abilities;

import com.tokensmp.TokenSMP;
import com.tokensmp.effects.EffectPlayer;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public abstract class AbstractAbility implements Ability {
    protected final String name;
    protected final String description;
    protected final int baseCooldown;

    public AbstractAbility(String name, String description, int baseCooldown) {
        this.name = name;
        this.description = description;
        this.baseCooldown = baseCooldown;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public int getBaseCooldown() { return baseCooldown; }

    protected int scaleDamage(int base, int level) {
        return base + (level - 1) * 2;
    }

    protected double scaleRadius(double base, int level) {
        return base + (level - 1) * 1.0;
    }

    protected int scaleDuration(int baseTicks, int level) {
        return baseTicks + (level - 1) * 20;
    }

    @Override
    public void playChargeAnimation(Player player) {
        EffectPlayer.playChargeEffect(player);
    }

    @Override
    public void playReleaseAnimation(Player player) {
        EffectPlayer.playReleaseEffect(player);
    }

    @Override
    public void playImpactAnimation(Player player, Location target) {
        EffectPlayer.playImpactEffect(player, target);
    }
}
