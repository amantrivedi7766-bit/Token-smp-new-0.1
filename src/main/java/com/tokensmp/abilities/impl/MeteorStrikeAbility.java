package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class MeteorStrikeAbility extends AbstractAbility {
    public MeteorStrikeAbility() {
        super("Meteor Strike", "Calls a meteor dealing massive damage.", 70);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        float explosionPower = 3.0f + (level - 1) * 0.5f;
        double damage = scaleDamage(10, level);
        double radius = scaleRadius(4, level);
        Location target = player.getTargetBlock(null, 50).getLocation().add(0.5, 0, 0.5);
        target.getWorld().createExplosion(target, explosionPower, true, true);
        target.getWorld().spawnParticle(Particle.EXPLOSION, target, 5 + level*3); // ✅ changed from EXPLOSION_LARGE
        target.getWorld().playSound(target, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0.7f - (level-1)*0.05f);
        target.getWorld().getNearbyEntities(target, radius, radius, radius,
                e -> e instanceof LivingEntity && e != player)
                .forEach(e -> ((LivingEntity) e).damage(damage, player));
    }
}
