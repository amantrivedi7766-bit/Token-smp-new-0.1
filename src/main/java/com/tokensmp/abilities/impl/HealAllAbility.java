package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HealAllAbility extends AbstractAbility {
    public HealAllAbility() {
        super("Heal All", "Heals all players fully.", 60);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double healPercent = 0.5 + level * 0.25;
        player.getWorld().getPlayers().forEach(p -> {
            p.setHealth(Math.min(p.getMaxHealth(), p.getHealth() + p.getMaxHealth() * healPercent));
            p.setFoodLevel(Math.min(20, p.getFoodLevel() + (int)(10 * healPercent)));
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 0));
        });
    }
}
