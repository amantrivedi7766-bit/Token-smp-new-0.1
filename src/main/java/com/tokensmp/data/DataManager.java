package com.tokensmp.data;

import com.tokensmp.TokenSMP;
import com.tokensmp.tokens.Token;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class DataManager {

    private final TokenSMP plugin;
    private final File dataFolder;

    public DataManager(TokenSMP plugin) {
        this.plugin = plugin;
        this.dataFolder = new File(plugin.getDataFolder(), "playerdata");
        if (!dataFolder.exists()) dataFolder.mkdirs();
    }

    public PlayerData loadPlayerData(UUID uuid) {
        File file = new File(dataFolder, uuid.toString() + ".yml");
        if (!file.exists()) {
            return new PlayerData(uuid);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        PlayerData data = new PlayerData(uuid);
        // load tokens
        if (config.contains("tokens")) {
            for (String key : config.getConfigurationSection("tokens").getKeys(false)) {
                Token token = Token.fromId(key);
                if (token != null) {
                    int amount = config.getInt("tokens." + key);
                    data.addToken(token, amount);
                }
            }
        }
        if (config.contains("levels")) {
            for (String key : config.getConfigurationSection("levels").getKeys(false)) {
                Token token = Token.fromId(key);
                if (token != null) {
                    int level = config.getInt("levels." + key);
                    data.setTokenLevel(token, level);
                }
            }
        }
        return data;
    }

    public void savePlayerData(UUID uuid) {
        PlayerData data = plugin.getTokenManager().getPlayerData(uuid);
        File file = new File(dataFolder, uuid.toString() + ".yml");
        FileConfiguration config = new YamlConfiguration();
        // save tokens
        data.getTokens().forEach((token, amount) -> config.set("tokens." + token.getId(), amount));
        data.getTokenLevels().forEach((token, level) -> config.set("levels." + token.getId(), level));
        try {
            config.save(file);
        } catch (Exception e) {
            plugin.getLogger().warning("Failed to save data for " + uuid);
        }
    }

    public void saveAll() {
        // save all loaded players
        plugin.getTokenManager().getPlayerDataMap().keySet().forEach(this::savePlayerData);
    }
}
