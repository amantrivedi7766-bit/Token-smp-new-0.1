package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class LifeDrainAbility extends AbstractAbility {
    public LifeDrainAbility() {
        super("Life Drain", "Drains health from nearby mobs.", 25);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double damage = scaleDamage(4, level);
        double heal = 2 + (level - 1) * 1.5;
        double radius = scaleRadius(4, level);
        player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius,
                e -> e instanceof LivingEntity && e != player)
                .forEach(e -> {
                    ((LivingEntity) e).damage(damage, player);
                    player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + heal));
                });
    }
}
