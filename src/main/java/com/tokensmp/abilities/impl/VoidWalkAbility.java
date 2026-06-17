package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class VoidWalkAbility extends AbstractAbility {
    public VoidWalkAbility() {
        super("Void Walk", "Teleport to the void and back (escape).", 90);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        Location voidLoc = new Location(player.getWorld(), 0, -60, 0);
        player.teleport(voidLoc);
        int count = 20 + level * 15;
        player.getWorld().spawnParticle(Particle.PORTAL, voidLoc, count, 1, 1, 1);
        player.playSound(voidLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f + (level-1)*0.1f);
        long delay = Math.max(10, 20 - (level-1)*5);
        Bukkit.getScheduler().runTaskLater(com.tokensmp.TokenSMP.getInstance(), () -> {
            player.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().add(0.5, 1, 0.5));
        }, delay);
    }
}
