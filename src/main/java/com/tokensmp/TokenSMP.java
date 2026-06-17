package com.tokensmp;

import com.tokensmp.bounty.BountyManager;
import com.tokensmp.commands.AdminCommands;
import com.tokensmp.commands.PlayerCommands;
import com.tokensmp.config.ConfigManager;
import com.tokensmp.cooldown.CooldownManager;
import com.tokensmp.data.DataManager;
import com.tokensmp.listeners.JoinListener;
import com.tokensmp.spin.SpinSystem;
import com.tokensmp.tokens.TokenManager;
import com.tokensmp.upgrade.UpgradeManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TokenSMP extends JavaPlugin {

    private static TokenSMP instance;
    private TokenManager tokenManager;
    private SpinSystem spinSystem;
    private CooldownManager cooldownManager;
    private UpgradeManager upgradeManager;
    private BountyManager bountyManager;
    private DataManager dataManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Initialize managers
        this.configManager = new ConfigManager(this);
        this.dataManager = new DataManager(this);
        this.tokenManager = new TokenManager(this);
        this.cooldownManager = new CooldownManager(this);
        this.spinSystem = new SpinSystem(this);
        this.upgradeManager = new UpgradeManager(this);
        this.bountyManager = new BountyManager(this);

        // Register commands
        getCommand("tokens").setExecutor(new PlayerCommands(this));
        getCommand("tokengui").setExecutor(new PlayerCommands(this));
        getCommand("tokeninfo").setExecutor(new PlayerCommands(this));
        getCommand("upgrade").setExecutor(new PlayerCommands(this));
        getCommand("bounty").setExecutor(new PlayerCommands(this));
        getCommand("spin").setExecutor(new PlayerCommands(this));

        getCommand("tokensmp").setExecutor(new AdminCommands(this));

        // Register events
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);

        getLogger().info("TokenSMP enabled successfully!");
    }

    @Override
    public void onDisable() {
        dataManager.saveAll();
        getLogger().info("TokenSMP disabled.");
    }

    public static TokenSMP getInstance() {
        return instance;
    }

    public TokenManager getTokenManager() { return tokenManager; }
    public SpinSystem getSpinSystem() { return spinSystem; }
    public CooldownManager getCooldownManager() { return cooldownManager; }
    public UpgradeManager getUpgradeManager() { return upgradeManager; }
    public BountyManager getBountyManager() { return bountyManager; }
    public DataManager getDataManager() { return dataManager; }
    public ConfigManager getConfigManager() { return configManager; }
}
