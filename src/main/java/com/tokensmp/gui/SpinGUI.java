package com.tokensmp.spin;

import com.tokensmp.TokenSMP;
import com.tokensmp.tokens.Token;
import com.tokensmp.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import java.util.List;

public class SpinGUI {
    private final TokenSMP plugin;
    private final Player player;
    private final List<Token> availableTokens;
    private Inventory inv;

    public SpinGUI(TokenSMP plugin, Player player, List<Token> available) {
        this.plugin = plugin;
        this.player = player;
        this.availableTokens = available;
    }

    public void open() {
        inv = Bukkit.createInventory(null, 9, "§6§lToken Spin");
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build());
        }
        new SpinAnimation(plugin, player, inv, availableTokens).start();
        player.openInventory(inv);
    }
}
