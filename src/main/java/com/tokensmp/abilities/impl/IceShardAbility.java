package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IceShardAbility extends AbstractAbility {
    public IceShardAbility() {
        super("Ice Shard", "Shoots an ice shard that slows and damages.", 15);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double damage = scaleDamage(4, level);
        int duration = scaleDuration(40, level);
        double radius = scaleRadius(2, level);
        Location target = player.getTargetBlock(null, 30).getLocation().add(0.5, 0, 0.5);
        int count = 15 + level * 10;
        target.getWorld().spawnParticle(Particle.SNOWFLAKE, target, count, 0.3, 0.3, 0.3); // ✅ changed from SNOWBALL
        target.getWorld().playSound(target, Sound.BLOCK_GLASS_BREAK, 0.8f, 1.5f - (level-1)*0.1f);
        target.getWorld().getNearbyEntities(target, radius, radius, radius,
                e -> e instanceof LivingEntity && e != player)
                .forEach(e -> {
                    ((LivingEntity) e).damage(damage, player);
                    ((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, duration, level-1));
                });
    }
}
