package com.tokensmp.tokens;

import com.tokensmp.TokenSMP;
import com.tokensmp.data.PlayerData;
import org.bukkit.entity.Player;

import java.util.*;

public class TokenManager {

    private final TokenSMP plugin;
    private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    public TokenManager(TokenSMP plugin) {
        this.plugin = plugin;
        // load from database
    }

    public void giveToken(Player player, Token token, int amount) {
        PlayerData data = getPlayerData(player.getUniqueId());
        data.addToken(token, amount);
        plugin.getDataManager().savePlayerData(player.getUniqueId());
    }

    public boolean hasToken(Player player, Token token) {
        PlayerData data = getPlayerData(player.getUniqueId());
        return data.getTokens().getOrDefault(token, 0) > 0;
    }

    public Map<Token, Integer> getOwnedTokens(Player player) {
        return getPlayerData(player.getUniqueId()).getTokens();
    }

    public PlayerData getPlayerData(UUID uuid) {
        return playerDataMap.computeIfAbsent(uuid, k -> plugin.getDataManager().loadPlayerData(k));
    }

    public List<Token> getAvailableSpinTokens(Player player) {
        // returns non-admin tokens that player does not already own (or all if config allows duplicates)
        List<Token> available = new ArrayList<>();
        for (Token t : Token.values()) {
            if (!t.isAdminOnly()) {
                available.add(t);
            }
        }
        return available;
    }
}
