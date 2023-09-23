package cz.flyultra.modules.mute.listener;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.modules.mute.Mute;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteListener implements Listener {

    private OrbitalPunishments plugin;

    public MuteListener() {
        this.plugin = OrbitalPunishments.getInstance();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getUniqueId());

        Mute mute = plugin.getMuteAPI().getMute(offlinePlayer);

        if (mute == null || !mute.isActive()) {
            return;
        }

        event.setCancelled(true);

        plugin.getMessageAPI().sendMessage(player, "mutes.chatEvent", null, (mute.getDateOfExpire() - System.currentTimeMillis()));
    }


}
