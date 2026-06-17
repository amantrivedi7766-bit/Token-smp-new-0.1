package com.tokensmp.commands;

import com.tokensmp.TokenSMP;
import com.tokensmp.gui.*;
import com.tokensmp.tokens.Token;
import com.tokensmp.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommands implements CommandExecutor {

    private final TokenSMP plugin;

    public PlayerCommands(TokenSMP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        switch (cmd.getName().toLowerCase()) {
            case "tokens":
            case "tokengui":
                new TokenGUI(plugin).open(player);
                break;
            case "tokeninfo":
                if (args.length < 1) {
                    player.sendMessage(MessageUtil.color("&cUsage: /tokeninfo <token>"));
                    return true;
                }
                Token token = Token.fromId(args[0]);
                if (token == null) {
                    player.sendMessage(MessageUtil.color("&cUnknown token."));
                    return true;
                }
                player.sendMessage(MessageUtil.color("&6Token: " + token.getDisplayName()));
                player.sendMessage(MessageUtil.color("&7Rarity: " + token.getRarity().getColor()));
                player.sendMessage(MessageUtil.color("&7Abilities:"));
                token.getAbilities().forEach(a -> 
                    player.sendMessage(MessageUtil.color("&8- &f" + a.getName() + " &7(" + a.getCooldownSeconds() + "s)"))
                );
                break;
            case "upgrade":
                new UpgradeGUI(plugin).open(player);
                break;
            case "bounty":
                new BountyGUI(plugin).open(player);
                break;
            case "spin":
                plugin.getSpinSystem().openSpinGUI(player);
                break;
            default:
                return false;
        }
        return true;
    }
}
