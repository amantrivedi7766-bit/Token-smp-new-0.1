package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Sound;

import java.util.List;

public class ChainReactionAbility extends AbstractAbility {
    public ChainReactionAbility() {
        super("Chain Reaction", "Lightning chains to nearby mobs.", 45);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double damage = scaleDamage(6, level);
        double radius = scaleRadius(5, level);
        Location origin = player.getTargetBlock(null, 50).getLocation();
        List<LivingEntity> targets = player.getWorld().getNearbyEntities(origin, radius, radius, radius,
                e -> e instanceof LivingEntity && e != player).stream().map(e -> (LivingEntity) e).toList();
        int max = Math.min(targets.size(), 3 + level);
        for (int i = 0; i < max; i++) {
            LivingEntity e = targets.get(i);
            e.getWorld().strikeLightningEffect(e.getLocation());
            e.damage(damage, player);
            e.getWorld().playSound(e.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 1.2f - (level-1)*0.1f);
        }
    }
}
