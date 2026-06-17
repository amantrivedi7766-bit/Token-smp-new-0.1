package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class StormSummonAbility extends AbstractAbility {
    public StormSummonAbility() {
        super("Storm Summon", "Summons a thunderstorm around you.", 60);
    }
    @Override
    public void execute(Player player, com.tokensmp.tokens.Token token) {
        Location center = player.getLocation();
        player.getWorld().setStorm(true);
        player.getWorld().setThundering(true);
        for (int i = 0; i < 20; i++) {
            Location loc = center.clone().add(Math.random()*10-5, 10, Math.random()*10-5);
            player.getWorld().strikeLightningEffect(loc);
        }
        player.getWorld().playSound(center, Sound.AMBIENT_WEATHER_THUNDER, 1.0f, 0.5f);
    }
}
