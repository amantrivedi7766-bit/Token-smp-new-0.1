package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class VoidAnnihilationAbility extends AbstractAbility {
    public VoidAnnihilationAbility() {
        super("Void Annihilation", "Erases all mobs in a large area.", 120);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double radius = scaleRadius(12, level);
        Location center = player.getLocation();
        center.getWorld().getNearbyEntities(center, radius, radius, radius,
                e -> e instanceof LivingEntity && !(e instanceof Player))
                .forEach(e -> {
                    e.getWorld().spawnParticle(Particle.PORTAL, e.getLocation(), 30 + level*10, 0.5, 0.5, 0.5);
                    e.remove();
                });
        center.getWorld().playSound(center, Sound.ENTITY_WITHER_DEATH, 1.0f, 0.3f - (level-1)*0.03f);
    }
}
