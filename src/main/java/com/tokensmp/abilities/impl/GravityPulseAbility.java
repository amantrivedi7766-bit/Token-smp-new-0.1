package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class GravityPulseAbility extends AbstractAbility {
    public GravityPulseAbility() {
        super("Gravity Pulse", "Pulls all nearby entities towards you.", 30);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double radius = scaleRadius(6, level);
        double strength = 0.5 + (level - 1) * 0.3; // 0.5, 0.8, 1.1
        Location center = player.getLocation();
        player.getWorld().getNearbyEntities(center, radius, radius, radius,
                e -> e instanceof LivingEntity && e != player)
                .forEach(e -> {
                    Vector dir = center.toVector().subtract(e.getLocation().toVector()).normalize().multiply(strength);
                    e.setVelocity(dir);
                    e.getWorld().spawnParticle(Particle.DRAGON_BREATH, e.getLocation(), 10 + level*5, 0.2, 0.2, 0.2);
                });
        player.getWorld().playSound(center, Sound.ENTITY_ENDER_DRAGON_GROWL, 0.8f, 1.0f - (level-1)*0.1f);
    }
}
