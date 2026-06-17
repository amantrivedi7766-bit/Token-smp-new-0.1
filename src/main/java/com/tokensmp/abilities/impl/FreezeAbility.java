package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FreezeAbility extends AbstractAbility {
    public FreezeAbility() {
        super("Freeze", "Freezes all mobs in a radius.", 30);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double radius = scaleRadius(7, level);
        int duration = scaleDuration(80, level);
        int amplifier = level - 1;
        player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius,
                e -> e instanceof LivingEntity && e != player)
                .forEach(e -> ((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, duration, 4 + amplifier)));
    }
}
