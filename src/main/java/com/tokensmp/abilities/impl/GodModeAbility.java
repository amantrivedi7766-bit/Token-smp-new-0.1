package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GodModeAbility extends AbstractAbility {
    public GodModeAbility() {
        super("God Mode", "Grants invulnerability for a duration.", 120);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        int duration = scaleDuration(160, level);
        int resistanceAmp = 3 + (level - 1);
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, duration, resistanceAmp));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, level-1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, duration, 0));
    }
}
