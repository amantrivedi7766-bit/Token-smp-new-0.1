package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class BlackHoleAbility extends AbstractAbility {
    public BlackHoleAbility() {
        super("Black Hole", "Creates a black hole that pulls and damages mobs.", 60);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double radius = scaleRadius(4, level);
        double damage = scaleDamage(4, level);
        Location center = player.getTargetBlock(null, 50).getLocation().add(0.5, 0, 0.5);
        for (int i = 0; i < 3 + level; i++) {
            player.getWorld().getNearbyEntities(center, radius, radius, radius,
                    e -> e instanceof LivingEntity && e != player)
                    .forEach(e -> {
                        e.teleport(center);
                        ((LivingEntity) e).damage(damage, player);
                    });
        }
        int count = 30 + level * 20;
        player.getWorld().spawnParticle(Particle.PORTAL, center, count, 1, 1, 1);
        player.getWorld().playSound(center, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0.5f - (level-1)*0.05f);
    }
}
