package cz.flyultra.modules.blacklist.command;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.utils.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BlackListCommand extends Command {

    private OrbitalPunishments plugin;

    public BlackListCommand() {
        super("blacklist", "desc", "");
        plugin = OrbitalPunishments.getInstance();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command cant be used from Console!");
            return;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            plugin.getMessageAPI().sendMessage(player, "wrongSyntax");
            return;
        }

        try {

            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
            if (offlinePlayer == null) {
                plugin.getMessageAPI().sendMessage(player, "targetIsNull", args[1]);
                return;
            }

            if (args[0].equalsIgnoreCase("add") && args.length == 2) {
                plugin.getBlackListAPI().addToList(player, offlinePlayer);
                return;
            }

            if (args[0].equalsIgnoreCase("remove") && args.length == 2) {
                plugin.getBlackListAPI().removeFromList(player, offlinePlayer);
                return;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            plugin.getMessageAPI().sendMessage(player, "wrongSyntax");
        }
        return;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
