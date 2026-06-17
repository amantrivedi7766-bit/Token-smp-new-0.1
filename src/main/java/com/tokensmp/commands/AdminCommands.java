
package com.tokensmp.commands;

import com.tokensmp.TokenSMP;
import com.tokensmp.gui.AdminGUI;
import com.tokensmp.tokens.Token;
import com.tokensmp.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {

    private final TokenSMP plugin;

    public AdminCommands(TokenSMP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("tokensmp.admin")) {
            sender.sendMessage(MessageUtil.color("&cNo permission."));
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("Usage: /tokensmp <reload|give|remove|reset|admin|cooldownreset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                plugin.getConfigManager().reload();
                sender.sendMessage(MessageUtil.color("&aConfig reloaded."));
                break;
            case "give":
                if (args.length < 3) {
                    sender.sendMessage("Usage: /tokensmp give <player> <token> [amount]");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage(MessageUtil.color("&cPlayer not found."));
                    return true;
                }
                Token token = Token.fromId(args[2]);
                if (token == null) {
                    sender.sendMessage(MessageUtil.color("&cUnknown token."));
                    return true;
                }
                int amount = args.length > 3 ? Integer.parseInt(args[3]) : 1;
                plugin.getTokenManager().giveToken(target, token, amount);
                sender.sendMessage(MessageUtil.color("&aGiven " + amount + " " + token.getDisplayName() + " to " + target.getName()));
                break;
            case "remove":
                // implement similarly
                sender.sendMessage("Not implemented yet.");
                break;
            case "reset":
                sender.sendMessage("Not implemented yet.");
                break;
            case "admin":
                if (sender instanceof Player) {
                    new AdminGUI(plugin).open((Player) sender);
                } else {
                    sender.sendMessage("Must be a player.");
                }
                break;
            case "cooldownreset":
                sender.sendMessage("Not implemented yet.");
                break;
            default:
                sender.sendMessage("Unknown subcommand.");
        }
        return true;
    }
}
