package com.tokensmp.abilities.impl;

import com.tokensmp.abilities.AbstractAbility;
import com.tokensmp.effects.EffectPlayer;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;

public class LightningStrikeAbility extends AbstractAbility {
    public LightningStrikeAbility() {
        super("Lightning Strike", "Strikes lightning at your target dealing 8 damage.", 30);
    }
    @Override
    public void execute(Player player, com.tokensmp.tokens.Token token) {
        Location target = player.getTargetBlock(null, 50).getLocation().add(0.5, 0, 0.5);
        player.getWorld().strikeLightningEffect(target);
        for (LivingEntity e : player.getWorld().getNearbyEntities(target, 3, 3, 3, e -> e instanceof LivingEntity && e != player)) {
            ((LivingEntity) e).damage(8, player);
        }
        EffectPlayer.playImpactEffect(player, target);
        player.getWorld().playSound(target, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0.8f);
    }
}
