package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.LivingEntity;   // ✅ ADDED
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FlameAuraAbility extends AbstractAbility {
    public FlameAuraAbility() {
        super("Flame Aura", "Ignite nearby mobs continuously.", 45);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        int duration = scaleDuration(60, level);
        double radius = scaleRadius(3, level);
        player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius,
                e -> e instanceof LivingEntity && e != player)
                .forEach(e -> ((LivingEntity) e).setFireTicks(duration));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, duration + 20, 0));
    }
}
