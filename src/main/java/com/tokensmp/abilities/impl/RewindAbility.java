package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RewindAbility extends AbstractAbility {
    public RewindAbility() {
        super("Rewind", "Resets health and hunger to 5 seconds ago.", 50);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double heal = 0.5 + level * 0.2; // 70% - 110%
        player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + player.getMaxHealth() * heal));
        player.setFoodLevel(Math.min(20, player.getFoodLevel() + (int)(10 * heal)));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60 + (level-1)*20, 0));
        player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f + (level-1)*0.1f);
    }
}
