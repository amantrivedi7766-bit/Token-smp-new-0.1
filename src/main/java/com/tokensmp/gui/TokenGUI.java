package com.tokensmp.gui;

import com.tokensmp.TokenSMP;
import com.tokensmp.tokens.Token;
import com.tokensmp.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class TokenGUI implements Listener {

    private final TokenSMP plugin;

    public TokenGUI(TokenSMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "§6§lYour Tokens");
        Map<Token, Integer> tokens = plugin.getTokenManager().getOwnedTokens(player);
        int slot = 0;
        for (Map.Entry<Token, Integer> entry : tokens.entrySet()) {
            Token t = entry.getKey();
            int amount = entry.getValue();
            ItemStack item = new ItemBuilder(Material.PAPER)
                    .name(t.getRarity().getColor() + t.getDisplayName())
                    .lore("&7Amount: &f" + amount,
                          "&7Level: &f" + plugin.getTokenManager().getPlayerData(player.getUniqueId()).getTokenLevel(t),
                          "&7Abilities:",
                          "&8- " + t.getAbilities().get(0).getName(),
                          "&8- " + t.getAbilities().get(1).getName(),
                          "&8- " + t.getAbilities().get(2).getName())
                    .modelData(t.getModelData())
                    .build();
            inv.setItem(slot++, item);
        }
        // Fill rest with glass
        player.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("§6§lYour Tokens")) {
            e.setCancelled(true);
        }
    }
}
