package cz.flyultra.modules.messages;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.utils.Utils;
import org.bukkit.entity.Player;

public class MessageAPI {

    private OrbitalPunishments plugin;


    public MessageAPI() {
        plugin = OrbitalPunishments.getInstance();
    }

    public void sendMessage(Player player, String message) {
        String configMessage = plugin.getCfg().get().getString(message);
        if (configMessage == null) {
            System.out.println("Message is null: " + message);
            return;
        }
        player.sendMessage(Utils.colorize(configMessage));
    }

    public void sendMessage(Player player, String message, String playerName) {
        String configMessage = plugin.getCfg().get().getString(message);
        if (configMessage == null) {
            System.out.println("Message is null: " + message);
            return;
        }
        configMessage = configMessage.replace("%player%", playerName);

        player.sendMessage(Utils.colorize(configMessage));
    }


    public void sendMessage(Player sender, String message, Player player, long time) {
        String configMessage = plugin.getCfg().get().getString(message);

        if (configMessage == null) {
            System.out.println("Message is null: " + message);
            return;
        }

        configMessage = configMessage.replace("%time%",Utils.formatDate(time));

        if (player != null) {
            configMessage = configMessage.replace("%player%", player.getName());
        }

        sender.sendMessage(Utils.colorize(configMessage));
    }

}
