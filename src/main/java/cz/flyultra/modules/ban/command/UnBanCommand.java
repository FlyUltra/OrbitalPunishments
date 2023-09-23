package cz.flyultra.modules.ban.command;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.utils.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class UnBanCommand extends Command {

    private OrbitalPunishments plugin;

    public UnBanCommand() {
        super("unban", "description", "");
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
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);

            plugin.getBanController().unBan(player, offlinePlayer);

        } catch (ArrayIndexOutOfBoundsException e) {
            plugin.getMessageAPI().sendMessage(player, "wrongSyntax");
        }
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}