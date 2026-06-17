package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class LightningStrikeAbility extends AbstractAbility {
    public LightningStrikeAbility() {
        super("Lightning Strike", "Strikes lightning at your target.", 30);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double damage = scaleDamage(8, level);
        Location target = player.getTargetBlock(null, 50).getLocation().add(0.5, 0, 0.5);
        player.getWorld().strikeLightningEffect(target);
        for (LivingEntity e : player.getWorld().getNearbyEntities(target, 3, 3, 3, e -> e instanceof LivingEntity && e != player)) {
            ((LivingEntity) e).damage(damage, player);
        }
        // More particles at higher levels
        int count = 10 + level * 5;
        player.getWorld().spawnParticle(Particle.DUST, target, count, 0.5, 0.5, 0.5, new org.bukkit.Particle.DustOptions(org.bukkit.Color.ORANGE, 1));
        player.getWorld().playSound(target, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0.8f - (level-1)*0.05f);
    }
}
