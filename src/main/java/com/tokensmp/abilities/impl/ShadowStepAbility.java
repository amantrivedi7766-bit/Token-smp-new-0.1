package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.tokens.Token;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ShadowStepAbility extends AbstractAbility {
    public ShadowStepAbility() {
        super("Shadow Step", "Teleport to the nearest shadow behind your target.", 35);
    }

    @Override
    public void execute(Player player, Token token) {
        int level = getLevel(player, token);
        double distance = 2 + (level - 1) * 1.0;
        Location target = player.getTargetBlock(null, 30).getLocation();
        Location behind = target.clone().add(player.getLocation().getDirection().multiply(-distance));
        player.teleport(behind);
        int count = 15 + level * 10;
        player.getWorld().spawnParticle(Particle.SMOKE_LARGE, behind, count, 0.3, 0.3, 0.3);
        player.playSound(behind, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0.8f + (level-1)*0.1f);
    }
}
