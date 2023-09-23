package cz.flyultra.modules.mute.controller;

import cz.flyultra.OrbitalPunishments;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class MuteController {

    private OrbitalPunishments plugin;

    public MuteController() {
        plugin = OrbitalPunishments.getInstance();
    }

    public void giveMute(Player sender, OfflinePlayer offlinePlayer, String reasonBuilder, long time) {
        if (plugin.getMuteAPI().isMuted(offlinePlayer)) {
            plugin.getMessageAPI().sendMessage(sender, "mutes.alreadyMuted", offlinePlayer.getPlayer(), 0L);
            return;
        }

        plugin.getMuteAPI().giveMute(sender, offlinePlayer, reasonBuilder, time);

    }

    public void unMute(Player sender, OfflinePlayer offlinePlayer) {
        if (!plugin.getMuteAPI().isMuted(offlinePlayer)) {
            plugin.getMessageAPI().sendMessage(sender, "mutes.alreadyUnMuted", offlinePlayer.getPlayer(), 0L);
            return;
        }

        plugin.getMuteAPI().unMute(sender, offlinePlayer);

    }
}
