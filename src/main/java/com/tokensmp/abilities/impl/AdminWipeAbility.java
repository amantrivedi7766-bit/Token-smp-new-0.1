package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.Particle;

public class AdminWipeAbility extends AbstractAbility {
    public AdminWipeAbility() {
        super("Admin Wipe", "Instantly kills all nearby mobs.", 10);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double radius = scaleRadius(15, level);
        player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius,
                e -> !(e instanceof Player))
                .forEach(e -> {
                    e.getWorld().spawnParticle(Particle.PORTAL, e.getLocation(), 20);
                    e.remove();
                });
    }
}
