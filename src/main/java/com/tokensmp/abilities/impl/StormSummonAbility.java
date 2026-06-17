package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class StormSummonAbility extends AbstractAbility {
    public StormSummonAbility() {
        super("Storm Summon", "Summons a thunderstorm around you.", 60);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        Location center = player.getLocation();
        player.getWorld().setStorm(true);
        player.getWorld().setThundering(true);
        int strikes = 10 + level * 5;
        for (int i = 0; i < strikes; i++) {
            Location loc = center.clone().add(Math.random()*12-6, 10, Math.random()*12-6);
            player.getWorld().strikeLightningEffect(loc);
        }
        player.getWorld().spawnParticle(Particle.CLOUD, center, 30 + level*10, 5, 2, 5);
        player.getWorld().playSound(center, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0.5f - (level-1)*0.05f);
    }
}
