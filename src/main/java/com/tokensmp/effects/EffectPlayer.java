package com.tokensmp.effects;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class EffectPlayer {

    public static void playChargeEffect(Player player) {
        Location loc = player.getLocation();
        player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, loc, 20, 0.5, 1, 0.5);
        player.playSound(loc, Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 0.5f);
    }

    public static void playReleaseEffect(Player player) {
        Location loc = player.getLocation();
        player.getWorld().spawnParticle(Particle.DRAGON_BREATH, loc, 30, 0.5, 0.5, 0.5);
        player.playSound(loc, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.5f);
    }

    public static void playImpactEffect(Player player, Location target) {
        target.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, target, 10, 0.5, 0.5, 0.5);
        target.getWorld().playSound(target, Sound.ENTITY_GENERIC_EXPLODE, 0.8f, 1.0f);
    }

    public static void spawnCelebration(Location loc) {
        loc.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 50, 0.5, 0.5, 0.5);
        loc.getWorld().playSound(loc, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 1.0f);
    }

    public static void spawnWelcomeParticles(Location loc) {
        loc.getWorld().spawnParticle(Particle.PORTAL, loc, 30, 0.5, 0.5, 0.5);
        loc.getWorld().playSound(loc, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
    }
}
