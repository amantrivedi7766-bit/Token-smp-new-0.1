package com.tokensmp.gui;

import com.tokensmp.TokenSMP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class AdminGUI implements Listener {
    private final TokenSMP plugin;
    public AdminGUI(TokenSMP plugin) { this.plugin = plugin; Bukkit.getPluginManager().registerEvents(this, plugin); }
    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "§c§lAdmin Control Panel");
        player.openInventory(inv);
    }
}
