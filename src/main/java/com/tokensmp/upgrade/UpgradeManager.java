package com.tokensmp.upgrade;

import com.tokensmp.TokenSMP;
import com.tokensmp.tokens.Token;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UpgradeManager {
    private final TokenSMP plugin;

    public UpgradeManager(TokenSMP plugin) { this.plugin = plugin; }

    public UpgradeResult attemptUpgrade(Player player, Token token) {
        int current = plugin.getTokenManager().getPlayerData(player.getUniqueId()).getTokenLevel(token);
        if (current >= 3) return UpgradeResult.MAX_LEVEL;
        if (!hasMaterials(player)) return UpgradeResult.MISSING_MATERIALS;
        if (Math.random() < plugin.getConfigManager().getUpgradeSuccessRate() / 100.0) {
            plugin.getTokenManager().getPlayerData(player.getUniqueId()).setTokenLevel(token, current + 1);
            consumeMaterials(player);
            return UpgradeResult.SUCCESS;
        }
        consumeMaterials(player);
        return UpgradeResult.FAIL;
    }

    private boolean hasMaterials(Player p) {
        return p.getInventory().contains(Material.DIAMOND, 5)
                && p.getInventory().contains(Material.NETHERITE_INGOT, 2)
                && p.getInventory().contains(Material.EMERALD, 10)
                && p.getInventory().contains(Material.AMETHYST_SHARD, 1);
    }

    private void consumeMaterials(Player p) {
        p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 5));
        p.getInventory().removeItem(new ItemStack(Material.NETHERITE_INGOT, 2));
        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 10));
        p.getInventory().removeItem(new ItemStack(Material.AMETHYST_SHARD, 1));
    }
}
