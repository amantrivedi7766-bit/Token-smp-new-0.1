package com.tokensmp.abilities;

import com.tokensmp.TokenSMP;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public interface Ability {
    String getName();
    String getDescription();
    int getBaseCooldown();
    void execute(Player player, Token token);

    default int getLevel(Player player, Token token) {
        return TokenSMP.getInstance()
                .getTokenManager()
                .getPlayerData(player.getUniqueId())
                .getTokenLevel(token);
    }

    default int getCooldown(Player player, Token token) {
        int base = getBaseCooldown();
        int level = getLevel(player, token);
        return Math.max(5, base - (level - 1) * 2);
    }

    void playChargeAnimation(Player player);
    void playReleaseAnimation(Player player);
    void playImpactAnimation(Player player, Location target);
}
