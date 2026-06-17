package com.tokensmp.abilities;

import com.tokensmp.TokenSMP;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages ability configurations loaded from abilities.yml.
 * Admins can override cooldowns and descriptions per ability.
 */
public class AbilityManager {

    private final TokenSMP plugin;
    private final Map<String, AbilityConfig> abilityConfigs = new HashMap<>();

    public AbilityManager(TokenSMP plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    /**
     * Loads or reloads the abilities.yml file.
     */
    private void loadConfig() {
        File file = new File(plugin.getDataFolder(), "abilities.yml");
        if (!file.exists()) {
            plugin.saveResource("abilities.yml", false);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        // Clear existing configs (if reloading)
        abilityConfigs.clear();

        for (String abilityId : config.getKeys(false)) {
            int cooldown = config.getInt(abilityId + ".cooldown", 30);
            String description = config.getString(abilityId + ".description", "No description provided.");
            abilityConfigs.put(abilityId, new AbilityConfig(cooldown, description));
        }

        plugin.getLogger().info("Loaded " + abilityConfigs.size() + " ability configurations.");
    }

    /**
     * Gets the configured cooldown for an ability ID.
     * @param abilityId the ability identifier (e.g., "lightning_strike")
     * @return cooldown in seconds, default 30 if not found
     */
    public int getCooldown(String abilityId) {
        return abilityConfigs.getOrDefault(abilityId, new AbilityConfig(30, "")).cooldown;
    }

    /**
     * Gets the configured description for an ability ID.
     * @param abilityId the ability identifier
     * @return description string, default "No description." if not found
     */
    public String getDescription(String abilityId) {
        return abilityConfigs.getOrDefault(abilityId, new AbilityConfig(30, "No description.")).description;
    }

    /**
     * Reloads the configuration from disk (used by /tokensmp reload).
     */
    public void reload() {
        loadConfig();
    }

    /**
     * Simple inner class to hold cooldown + description together.
     */
    private static class AbilityConfig {
        int cooldown;
        String description;

        AbilityConfig(int cooldown, String description) {
            this.cooldown = cooldown;
            this.description = description;
        }
    }
}
