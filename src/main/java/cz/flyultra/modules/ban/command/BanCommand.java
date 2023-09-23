package cz.flyultra.modules.ban.command;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.utils.Utils;
import cz.flyultra.utils.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BanCommand extends Command {

    private OrbitalPunishments plugin;

    public BanCommand() {
        super("ban", "description", "");
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
            if (offlinePlayer == null) {
                plugin.getMessageAPI().sendMessage(player, "targetIsNull", args[0]);
                return;
            }

            long time = 0L;
            StringBuilder reasonBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                try {
                    time = time + Utils.convertDate(args[i]);
                } catch (NumberFormatException e) {
                    reasonBuilder.append(args[i]).append(" ");
                }
            }

            plugin.getBanController().giveBan(player, offlinePlayer, reasonBuilder.toString(), time);

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
