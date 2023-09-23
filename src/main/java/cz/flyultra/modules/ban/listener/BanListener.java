package cz.flyultra.modules.ban.listener;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.modules.ban.Ban;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BanListener implements Listener {

    private OrbitalPunishments plugin;

    public BanListener() {
        plugin = OrbitalPunishments.getInstance();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getUniqueId());

        Ban ban = plugin.getBanAPI().getBan(offlinePlayer);

        if (ban == null || !ban.isActive()) {
            return;
        }


        plugin.getBanAPI().kickForBan(player, ban.getReason(), (ban.getDateOfExpire() - System.currentTimeMillis()), ban.isPerm());
    }


}
