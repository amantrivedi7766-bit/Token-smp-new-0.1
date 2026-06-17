package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GlobalBuffAbility extends AbstractAbility {
    public GlobalBuffAbility() {
        super("Global Buff", "Gives all players speed and strength.", 60);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        int duration = scaleDuration(160, level);
        int amp = level - 1;
        player.getWorld().getPlayers().forEach(p -> {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, 1 + amp));
            p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, duration, 1 + amp)); // ✅ changed from INCREASE_DAMAGE
        });
    }
}
