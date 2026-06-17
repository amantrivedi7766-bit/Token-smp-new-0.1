package com.tokensmp.spin;

import com.tokensmp.TokenSMP;
import com.tokensmp.effects.EffectPlayer;
import com.tokensmp.tokens.Token;
import com.tokensmp.tokens.TokenRarity;
import com.tokensmp.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class SpinAnimation {

    private final TokenSMP plugin;
    private final Player player;
    private final Inventory inv;
    private final List<Token> tokens;
    private final Random random = new Random();
    private int ticks = 0;
    private double speed = 3.0; // slots per tick
    private double position = 0;
    private Token winner;
    private boolean finished = false;

    public SpinAnimation(TokenSMP plugin, Player player, Inventory inv, List<Token> tokens) {
        this.plugin = plugin;
        this.player = player;
        this.inv = inv;
        this.tokens = tokens;
        // Pre-select winner (weighted)
        winner = selectWinner();
    }

    private Token selectWinner() {
        // Weighted random based on rarity
        int totalWeight = tokens.stream().mapToInt(t -> t.getRarity().getWeight()).sum();
        int roll = random.nextInt(totalWeight);
        for (Token t : tokens) {
            roll -= t.getRarity().getWeight();
            if (roll < 0) return t;
        }
        return tokens.get(0);
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (finished) {
                    this.cancel();
                    return;
                }
                ticks++;
                // Slow down
                if (ticks > 20 * 5) { // after 5 seconds
                    speed *= 0.98;
                    if (speed < 0.1) {
                        speed = 0;
                        finished = true;
                        // Stop on winner
                        int centerSlot = 4;
                        inv.setItem(centerSlot, new ItemBuilder(Material.PAPER)
                                .name(winner.getRarity().getColor() + winner.getDisplayName())
                                .lore("&7You won!")
                                .modelData(winner.getModelData())
                                .build());
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                        EffectPlayer.spawnCelebration(player.getLocation());
                        // Give token
                        plugin.getTokenManager().giveToken(player, winner, 1);
                        // Close after delay
                        Bukkit.getScheduler().runTaskLater(plugin, () -> player.closeInventory(), 40);
                        return;
                    }
                }

                // Update slots
                position += speed;
                int slotIndex = (int) position % 9;
                Token displayToken = tokens.get(random.nextInt(tokens.size()));
                inv.setItem(slotIndex, new ItemBuilder(Material.PAPER)
                        .name(displayToken.getRarity().getColor() + displayToken.getDisplayName())
                        .modelData(displayToken.getModelData())
                        .build());
                // Play sound based on speed
                float pitch = (float) (1.0 - speed / 5.0);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.5f, pitch);
            }
        }.runTaskTimer(plugin, 0, 1);
    }
}
