package cz.flyultra.modules.kick.command;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.utils.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class KickCommand extends Command {

    private OrbitalPunishments plugin;

    public KickCommand() {
        super("kick", "Kick command", "");
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

        try {

            Player target = Bukkit.getPlayer(args[0]);

            StringBuilder reasonBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                reasonBuilder.append(args[i]).append(" ");
            }

            plugin.getKickController().kickPlayer(player, target, reasonBuilder.toString());

        } catch (ArrayIndexOutOfBoundsException e) {
            plugin.getMessageAPI().sendMessage(player, "wrongSyntax");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
