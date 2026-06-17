package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class VoidRiftAbility extends AbstractAbility {
    public VoidRiftAbility() {
        super("Void Rift", "Opens a rift that deals massive damage.", 70);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double damage = scaleDamage(15, level);
        double radius = scaleRadius(4, level);
        Location target = player.getTargetBlock(null, 50).getLocation().add(0.5, 0, 0.5);
        int count = 30 + level * 20;
        target.getWorld().spawnParticle(Particle.DRAGON_BREATH, target, count, 1, 1, 1);
        target.getWorld().playSound(target, Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 0.4f - (level-1)*0.05f);
        target.getWorld().getNearbyEntities(target, radius, radius, radius,
                e -> e instanceof LivingEntity && e != player)
                .forEach(e -> ((LivingEntity) e).damage(damage, player));
    }
}
