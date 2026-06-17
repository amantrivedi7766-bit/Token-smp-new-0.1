package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import java.util.List;

public class ChainReactionAbility extends AbstractAbility {
    public ChainReactionAbility() {
        super("Chain Reaction", "Lightning chains to nearby mobs.", 45);
    }
    @Override
    public void execute(Player player, com.tokensmp.tokens.Token token) {
        Location origin = player.getTargetBlock(null, 50).getLocation();
        List<LivingEntity> targets = player.getWorld().getNearbyEntities(origin, 5, 5, 5, e -> e instanceof LivingEntity && e != player).stream().map(e -> (LivingEntity) e).toList();
        for (int i = 0; i < Math.min(targets.size(), 5); i++) {
            LivingEntity e = targets.get(i);
            e.getWorld().strikeLightningEffect(e.getLocation());
            e.damage(6, player);
            e.getWorld().playSound(e.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8f, 1.2f);
        }
    }
}
