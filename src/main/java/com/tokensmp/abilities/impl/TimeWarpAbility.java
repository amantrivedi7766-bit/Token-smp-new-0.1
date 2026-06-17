package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
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
    public void execute(Player player, com.tokensmp.tokens.Token token) {
        Location old = lastPos.get(player.getUniqueId());
        if (old != null) {
            player.teleport(old);
            player.getWorld().spawnParticle(Particle.PORTAL, old, 30, 0.5, 0.5, 0.5);
            player.playSound(old, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
        }
        // store current for next use
        lastPos.put(player.getUniqueId(), player.getLocation());
    }
}
