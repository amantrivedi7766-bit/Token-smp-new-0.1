package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InvisibilityFieldAbility extends AbstractAbility {
    public InvisibilityFieldAbility() {
        super("Invisibility Field", "Makes you and nearby allies invisible.", 50);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double radius = scaleRadius(8, level);
        int duration = scaleDuration(160, level);
        player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius,
                e -> e instanceof Player && e != player)
                .forEach(e -> ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, duration, 0)));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, duration, 0));
    }
}
