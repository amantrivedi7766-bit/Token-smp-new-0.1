package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimeWarpAbility extends AbstractAbility {
    private static final Map<UUID, Location> lastPos = new HashMap<>();

    public TimeWarpAbility() {
        super("Time Warp", "Rewinds your position 5 seconds back.", 40);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        Location old = lastPos.get(player.getUniqueId());
        if (old != null) {
            player.teleport(old);
            int count = 20 + level * 10;
            player.getWorld().spawnParticle(Particle.PORTAL, old, count, 0.5, 0.5, 0.5);
            player.playSound(old, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f + (level-1)*0.1f);
        }
        lastPos.put(player.getUniqueId(), player.getLocation());
    }
}
