package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.Particle;

public class InstantKillAbility extends AbstractAbility {
    public InstantKillAbility() {
        super("Instant Kill", "Kills the targeted mob instantly.", 30);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double radius = scaleRadius(1 + (level-1)*0.5, level);
        player.getWorld().getNearbyEntities(player.getTargetBlock(null, 50).getLocation(), radius, radius, radius,
                e -> e instanceof LivingEntity && e != player)
                .forEach(e -> {
                    ((LivingEntity) e).setHealth(0);
                    e.getWorld().spawnParticle(Particle.PORTAL, e.getLocation(), 30);
                });
    }
}
