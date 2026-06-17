package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FloatAbility extends AbstractAbility {
    public FloatAbility() {
        super("Float", "Allows you to float for a duration.", 40);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        int duration = scaleDuration(80, level);
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, duration, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, duration, 0));
    }
}
