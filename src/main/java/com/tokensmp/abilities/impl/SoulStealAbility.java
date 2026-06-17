package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SoulStealAbility extends AbstractAbility {
    public SoulStealAbility() {
        super("Soul Steal", "Steals life and gives absorption.", 40);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double damage = scaleDamage(6, level);
        int duration = scaleDuration(80, level);
        double radius = scaleRadius(5, level);
        player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius,
                e -> e instanceof LivingEntity && e != player)
                .forEach(e -> {
                    ((LivingEntity) e).damage(damage, player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, duration, level-1));
                });
    }
}
