package cz.flyultra.modules.blacklist.api;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.utils.Utils;
import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackListAPI {

    private OrbitalPunishments plugin;

    @Getter
    private List<String> blackList;

    public BlackListAPI() {
        plugin = OrbitalPunishments.getInstance();
        blackList = new ArrayList<>();
        blackList = plugin.getHikariHandler().getBlackListPeople();

    }

    public void addToList(Player sender, OfflinePlayer offlinePlayer) {
        if (isInBlackList(offlinePlayer.getName())) {
            plugin.getMessageAPI().sendMessage(sender, "blackList.alreadyAdded", offlinePlayer.getName());
            return;
        }

        getBlackList().add(offlinePlayer.getName().toLowerCase());

        if (offlinePlayer.getPlayer() != null && offlinePlayer.getPlayer().isOnline()) {
            kickForBlackList(offlinePlayer.getPlayer());
        }
        plugin.getMessageAPI().sendMessage(sender, "blackList.add", offlinePlayer.getName());
        plugin.getHikariHandler().newBlackList(offlinePlayer.getName());
    }

    public void removeFromList(Player sender, OfflinePlayer offlinePlayer) {
        if (!isInBlackList(offlinePlayer.getName())) {
            plugin.getMessageAPI().sendMessage(sender, "blackList.alreadyRemoved", offlinePlayer.getName());
            return;
        }

        getBlackList().remove(offlinePlayer.getName().toLowerCase());
        plugin.getHikariHandler().removeBlackList(offlinePlayer.getName());
        plugin.getMessageAPI().sendMessage(sender, "blackList.remove", offlinePlayer.getName());
    }

    public boolean isInBlackList(Player player) {
        if (blackList.contains(player.getName().toLowerCase())) {
            return true;
        }

        return false;
    }

    public boolean isInBlackList(String name) {
        if (blackList.contains(name.toLowerCase())) {
            return true;
        }

        return false;
    }

    public void kickForBlackList(Player player) {
        List<String> stringList = plugin.getCfg().get().getStringList("blackList.joinEvent");

        String kickString = stringList.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "\n");

        player.kickPlayer(Utils.colorize(kickString));
    }
}
