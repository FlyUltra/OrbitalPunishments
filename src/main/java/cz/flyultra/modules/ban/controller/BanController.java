package cz.flyultra.modules.ban.controller;

import cz.flyultra.OrbitalPunishments;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class BanController {

    private OrbitalPunishments plugin;

    public BanController() {
        plugin = OrbitalPunishments.getInstance();
    }

    public void giveBan(Player sender, OfflinePlayer offlinePlayer, String reasonBuilder, long time) {
        if (plugin.getBanAPI().isBanned(offlinePlayer)) {
            plugin.getMessageAPI().sendMessage(sender, "bans.alreadyBanned", offlinePlayer.getPlayer(), 0L);
            return;
        }

        plugin.getBanAPI().giveBan(sender, offlinePlayer, reasonBuilder, time);

    }

    public void unBan(Player sender, OfflinePlayer offlinePlayer) {
        if (!plugin.getBanAPI().isBanned(offlinePlayer)) {
            plugin.getMessageAPI().sendMessage(sender, "bans.alreadyUnBanned", offlinePlayer.getPlayer(), 0L);
            return;
        }

        plugin.getBanAPI().unBan(sender, offlinePlayer);

    }
}
