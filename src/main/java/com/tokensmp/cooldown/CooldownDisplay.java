package com.tokensmp.cooldown;

import com.tokensmp.TokenSMP;
import com.tokensmp.tokens.Token;
import com.tokensmp.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownDisplay {
    private final TokenSMP plugin;
    private final Player player;
    private final Token token;
    private final int totalSeconds;
    private BossBar bossBar;
    private int taskId;

    public CooldownDisplay(TokenSMP plugin, Player player, Token token, int seconds) {
        this.plugin = plugin;
        this.player = player;
        this.token = token;
        this.totalSeconds = seconds;
    }

    public void show() {
        bossBar = Bukkit.createBossBar(
                MessageUtil.color("&6" + token.getDisplayName() + " &7Cooldown"),
                BarColor.BLUE,
                BarStyle.SEGMENTED_10
        );
        bossBar.addPlayer(player);
        bossBar.setProgress(1.0);

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
            int elapsed = 0;
            @Override
            public void run() {
                elapsed++;
                double progress = 1.0 - (double) elapsed / totalSeconds;
                if (progress <= 0) {
                    bossBar.removePlayer(player);
                    bossBar.setVisible(false);
                    Bukkit.getScheduler().cancelTask(taskId);
                    player.sendActionBar(MessageUtil.color("&aCooldown finished!"));
                    return;
                }
                bossBar.setProgress(progress);
                player.sendActionBar(MessageUtil.color("&6" + token.getDisplayName() + " &f" + 
                        String.format("%.1f", progress * 100) + "%"));
            }
        }, 0, 20);
    }
}
