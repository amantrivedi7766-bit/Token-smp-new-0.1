package com.tokensmp.listeners;

import com.tokensmp.TokenSMP;
import com.tokensmp.effects.EffectPlayer;
import com.tokensmp.utils.MessageUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final TokenSMP plugin;

    public JoinListener(TokenSMP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        boolean firstJoin = !player.hasPlayedBefore();
        String title = firstJoin ? "&6&lWelcome to TokenSMP!" : "&eWelcome back!";
        String subtitle = firstJoin ? "&aYour token journey begins now!" : "&aSpin to win!";
        player.sendTitle(MessageUtil.color(title), MessageUtil.color(subtitle), 20, 60, 20);
        player.sendActionBar(MessageUtil.color("&7Tokens: &b" + plugin.getTokenManager().getOwnedTokens(player).size() + " &7| &e/spin to try your luck!"));
        EffectPlayer.spawnWelcomeParticles(player.getLocation());
        player.playSound(player.getLocation(), org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
    }
}
