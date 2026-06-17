package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SmiteAbility extends AbstractAbility {
    public SmiteAbility() {
        super("Smite", "Strikes multiple lightning bolts around you.", 40);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double damage = scaleDamage(5, level);
        double radius = scaleRadius(4, level);
        Location center = player.getLocation();
        int strikes = 6 + level * 3;
        for (int i = 0; i < strikes; i++) {
            Location loc = center.clone().add(Math.random()*radius*2 - radius, 0, Math.random()*radius*2 - radius);
            player.getWorld().strikeLightningEffect(loc);
            player.getWorld().getNearbyEntities(loc, 2, 2, 2, e -> e instanceof LivingEntity && e != player)
                    .forEach(e -> ((LivingEntity) e).damage(damage, player));
        }
        player.getWorld().playSound(center, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0.7f - (level-1)*0.05f);
    }
}
