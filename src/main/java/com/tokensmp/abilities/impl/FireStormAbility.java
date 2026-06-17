package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class FireStormAbility extends AbstractAbility {
    public FireStormAbility() {
        super("Fire Storm", "Creates a fire storm around you.", 50);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double damage = scaleDamage(4, level);
        int duration = scaleDuration(40, level);
        double radius = scaleRadius(6, level);
        Location center = player.getLocation();
        int particles = 20 + level * 15;
        for (int i = 0; i < particles; i++) {
            Location loc = center.clone().add(Math.random()*radius*2 - radius, 0, Math.random()*radius*2 - radius);
            player.getWorld().spawnParticle(Particle.FLAME, loc, 5, 0.2, 0.5, 0.2);
            player.getWorld().getNearbyEntities(loc, 2, 2, 2, e -> e instanceof LivingEntity && e != player)
                    .forEach(e -> {
                        ((LivingEntity) e).setFireTicks(duration);
                        ((LivingEntity) e).damage(damage, player);
                    });
        }
        player.getWorld().playSound(center, Sound.ENTITY_BLAZE_SHOOT, 1.0f, 0.6f - (level-1)*0.05f);
    }
}
