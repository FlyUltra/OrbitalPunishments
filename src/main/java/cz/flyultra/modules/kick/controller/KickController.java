package cz.flyultra.modules.kick.controller;

import cz.flyultra.OrbitalPunishments;
import org.bukkit.entity.Player;

public class KickController {

    private OrbitalPunishments plugin;

    public KickController() {
        plugin = OrbitalPunishments.getInstance();
    }

    public void kickPlayer(Player sender, Player target, String reason) {
        if (target == null || !target.isOnline()) {
            plugin.getMessageAPI().sendMessage(sender, "kick.targetIsOffline");
            return;
        }

        plugin.getKickAPI().kickPlayer(sender, target, reason);
    }

}
