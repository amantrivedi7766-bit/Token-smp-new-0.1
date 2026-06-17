package com.tokensmp.cooldown;

import com.tokensmp.TokenSMP;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private final TokenSMP plugin;
    private final Map<UUID, Map<Token, Long>> cooldowns = new HashMap<>();

    public CooldownManager(TokenSMP plugin) { this.plugin = plugin; }

    public boolean isOnCooldown(Player player, Token token) {
        Map<Token, Long> playerCooldowns = cooldowns.get(player.getUniqueId());
        if (playerCooldowns == null) return false;
        Long end = playerCooldowns.get(token);
        if (end == null) return false;
        return System.currentTimeMillis() < end;
    }

    public void startCooldown(Player player, Token token, int seconds) {
        long end = System.currentTimeMillis() + seconds * 1000L;
        cooldowns.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>()).put(token, end);
        new CooldownDisplay(plugin, player, token, seconds).show();
    }

    public void resetCooldown(Player player, Token token) {
        Map<Token, Long> map = cooldowns.get(player.getUniqueId());
        if (map != null) map.remove(token);
    }
}
