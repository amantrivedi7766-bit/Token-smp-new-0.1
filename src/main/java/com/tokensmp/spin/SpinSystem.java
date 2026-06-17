package com.tokensmp.spin;

import com.tokensmp.TokenSMP;
import com.tokensmp.tokens.Token;
import org.bukkit.entity.Player;
import java.util.List;

public class SpinSystem {
    private final TokenSMP plugin;

    public SpinSystem(TokenSMP plugin) { this.plugin = plugin; }

    public void openSpinGUI(Player player) {
        List<Token> available = plugin.getTokenManager().getAvailableSpinTokens(player);
        if (available.isEmpty()) {
            player.sendMessage(plugin.getConfigManager().getMessage("spin.no-tokens"));
            return;
        }
        new SpinGUI(plugin, player, available).open();
    }

    public void performSpin(Player player, List<Token> available, Token winner) {
        plugin.getTokenManager().giveToken(player, winner, 1);
    }
}
