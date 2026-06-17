package com.tokensmp.config;

import com.tokensmp.TokenSMP;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private final TokenSMP plugin;
    private FileConfiguration config;

    public ConfigManager(TokenSMP plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }

    public String getMessage(String path) {
        return config.getString("messages." + path, "Message not found.");
    }

    public int getUpgradeSuccessRate() {
        return config.getInt("upgrade.success-rate", 70);
    }
}
