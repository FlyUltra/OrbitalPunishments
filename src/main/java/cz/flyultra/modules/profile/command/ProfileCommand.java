package cz.flyultra.modules.profile.command;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.utils.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ProfileCommand extends Command {

    private OrbitalPunishments plugin;


    public ProfileCommand() {
        super("profile", "description", "");
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

            if (args.length != 2) {
                plugin.getMessageAPI().sendMessage(player, "wrongSyntax", args[0]);
                return;
            }

            if (args[1].equalsIgnoreCase("bans")) {
                plugin.getProfileController().sendBanLog(player, offlinePlayer);
                return;
            }

            if (args[1].equalsIgnoreCase("mutes")) {
                plugin.getProfileController().sendMuteLog(player, offlinePlayer);

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
