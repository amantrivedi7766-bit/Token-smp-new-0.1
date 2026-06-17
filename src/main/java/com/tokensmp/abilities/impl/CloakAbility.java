package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CloakAbility extends AbstractAbility {
    public CloakAbility() {
        super("Cloak", "Become invisible and gain speed.", 30);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        int duration = scaleDuration(100, level);
        int speedAmp = level - 1;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, duration, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, speedAmp));
    }
}
