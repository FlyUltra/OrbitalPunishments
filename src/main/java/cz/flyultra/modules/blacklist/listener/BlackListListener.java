package cz.flyultra.modules.blacklist.listener;


import cz.flyultra.OrbitalPunishments;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BlackListListener implements Listener {

    private OrbitalPunishments plugin;

    public BlackListListener() {
        plugin = OrbitalPunishments.getInstance();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (plugin.getBlackListAPI().isInBlackList(player)) {
            plugin.getBlackListAPI().kickForBlackList(player);
        }
    }

}
