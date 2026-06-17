package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VampiricTouchAbility extends AbstractAbility {
    public VampiricTouchAbility() {
        super("Vampiric Touch", "Attacks heal you.", 20);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        int duration = scaleDuration(60, level);
        int amplifier = level - 1;
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amplifier));
        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, duration, amplifier));
    }
}
