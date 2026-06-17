package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SnowStormAbility extends AbstractAbility {
    public SnowStormAbility() {
        super("Snow Storm", "Creates a snow storm reducing visibility.", 40);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double radius = scaleRadius(8, level);
        Location center = player.getLocation();
        int count = 30 + level * 20;
        for (int i = 0; i < count; i++) {
            Location loc = center.clone().add(Math.random()*radius*2 - radius, Math.random()*5, Math.random()*radius*2 - radius);
            player.getWorld().spawnParticle(Particle.SNOWFLAKE, loc, 2);
        }
        player.getWorld().playSound(center, Sound.WEATHER_RAIN_ABOVE, 0.5f, 0.5f - (level-1)*0.05f);
    }
}
