package cz.flyultra.modules.ban.api;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.modules.ban.Ban;
import cz.flyultra.utils.Utils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BanAPI {

    private OrbitalPunishments plugin;

    public BanAPI() {
        this.plugin = OrbitalPunishments.getInstance();
        onLoad();
    }

    private List<Ban> banList;

    public void onLoad() {
        banList = new ArrayList<>();
        banList = plugin.getHikariHandler().getBans();
    }

    public void giveBan(Player sender, OfflinePlayer offlinePlayer, String reason, long time) {
        if (time == 0L) {
            Ban ban = new Ban(offlinePlayer.getName(), sender.getName(), reason, System.currentTimeMillis(), 0L, true, false, null);
            banList.add(ban);
            plugin.getHikariHandler().newBan(ban);

            plugin.getMessageAPI().sendMessage(sender, "bans.ban.admin.perm", offlinePlayer.getPlayer(), ban.getDateOfExpire());
            if (Objects.requireNonNull(offlinePlayer.getPlayer()).isOnline()) {
                kickForBan(offlinePlayer.getPlayer(), reason, time, ban.isPerm());
            }
            return;
        }

        Ban ban = new Ban(offlinePlayer.getName(), sender.getName(), reason, System.currentTimeMillis(), (System.currentTimeMillis() + time), false, false, null);
        banList.add(ban);
        plugin.getHikariHandler().newBan(ban);

        plugin.getMessageAPI().sendMessage(sender, "bans.ban.admin.temp", offlinePlayer.getPlayer(), time);
        if (Objects.requireNonNull(offlinePlayer.getPlayer()).isOnline()) {
            kickForBan(offlinePlayer.getPlayer(), reason, time, ban.isPerm());
        }

    }

    public void kickForBan(Player player, String reason, long time, boolean isPerm) {
        List<String> stringList = new ArrayList<>();
        if (isPerm) {
            stringList = plugin.getCfg().get().getStringList("bans.joinEvent.perm");
        } else {
            stringList = plugin.getCfg().get().getStringList("bans.joinEvent.temp");
        }

        String kickString = stringList.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "\n")
                .replace("%reason%", reason)
                .replace("%time%", Utils.formatDate(time));

        player.kickPlayer(Utils.colorize(kickString));
    }

    public void unBan(Player sender, OfflinePlayer offlinePlayer) {
        Ban ban = getBan(offlinePlayer);

        if (ban == null) {
            plugin.getMessageAPI().sendMessage(sender, "bans.alreadyUnBanned", offlinePlayer.getPlayer(), 0L);
            return;
        }

        ban.setUnBanned(true);
        ban.setUnBannedBy(sender.getName());
        banList.add(ban);
        plugin.getHikariHandler().updateBan(ban);
        plugin.getMessageAPI().sendMessage(sender, "bans.unBan.admin", offlinePlayer.getPlayer(), 0L);
        if (Objects.requireNonNull(offlinePlayer.getPlayer()).isOnline()) {
            plugin.getMessageAPI().sendMessage(offlinePlayer.getPlayer(), "bans.unBan.target", null, 0L);
        }
    }

    public boolean isBanned(OfflinePlayer player) {
        for (Ban ban : banList) {
            if (ban.getPlayerName().equalsIgnoreCase(player.getName()) && ban.isActive()) {
                return true;
            }
            continue;
        }
        return false;
    }

    public Ban getBan(OfflinePlayer player) {
        for (Ban ban : banList) {
            if (ban.getPlayerName().equalsIgnoreCase(player.getName()) && ban.isActive()) {
                return ban;
            }
            continue;
        }

        return null;
    }

    public List<Ban> getPlayerBans(OfflinePlayer player) {
        List<Ban> bans = new ArrayList<>();

        for (Ban ban : banList) {
            if (ban.getPlayerName().equalsIgnoreCase(player.getName())) {
                bans.add(ban);
                continue;
            }
            continue;
        }

        return bans;
    }

}
