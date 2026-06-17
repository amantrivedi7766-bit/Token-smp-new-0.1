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

public class UpgradeGUI implements Listener {
    private final TokenSMP plugin;
    public UpgradeGUI(TokenSMP plugin) { this.plugin = plugin; Bukkit.getPluginManager().registerEvents(this, plugin); }
    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "§6§lToken Upgrade");
        // Show owned tokens with upgrade option
        player.openInventory(inv);
    }
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("§6§lToken Upgrade")) {
            e.setCancelled(true);
            // Handle upgrade logic
        }
    }
}
