package com.tokensmp.abilities;

import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public interface Ability {
    String getName();
    String getDescription();
    int getCooldownSeconds();
    void execute(Player player, Token token);
    void playChargeAnimation(Player player);
    void playReleaseAnimation(Player player);
    void playImpactAnimation(Player player, Location target);
}
