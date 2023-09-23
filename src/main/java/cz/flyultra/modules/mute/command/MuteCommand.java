package cz.flyultra.modules.mute.command;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.utils.Utils;
import cz.flyultra.utils.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class MuteCommand extends Command {

    private OrbitalPunishments plugin;

    public MuteCommand() {
        super("mute", "description", "");
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
            return;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);



        long time = 0L;
        StringBuilder reasonBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            try {
                time = time + Utils.convertDate(args[i]);
            } catch (NumberFormatException e) {
                reasonBuilder.append(args[i]).append(" ");
            }
        }

        plugin.getMuteController().giveMute(player, offlinePlayer, reasonBuilder.toString(), time);

    }


    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
