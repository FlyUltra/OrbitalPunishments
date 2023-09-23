package cz.flyultra.modules.mute.api;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.modules.mute.Mute;
import cz.flyultra.utils.Utils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MuteAPI {

    private OrbitalPunishments plugin;

    public MuteAPI() {
        this.plugin = OrbitalPunishments.getInstance();
        onLoad();
    }

    private List<Mute> muteList;

    public void onLoad() {
        muteList = new ArrayList<>();
        muteList = plugin.getHikariHandler().getMutes();
    }

    public void giveMute(Player sender, OfflinePlayer offlinePlayer, String reason, long time) {
        if (time == 0L) {
            Mute mute = new Mute(offlinePlayer.getName(), sender.getName(), reason, System.currentTimeMillis(), 0L, true, false, null);
            muteList.add(mute);
            plugin.getHikariHandler().newMute(mute);

            plugin.getMessageAPI().sendMessage(sender, "mutes.mute.admin.perm", offlinePlayer.getPlayer(), mute.getDateOfExpire());

            if (Objects.requireNonNull(offlinePlayer.getPlayer()).isOnline()) {
                plugin.getMessageAPI().sendMessage(offlinePlayer.getPlayer(), "mutes.mute.target.perm", offlinePlayer.getPlayer(), mute.getDateOfExpire());
            }
            return;
        }

        Mute mute = new Mute(offlinePlayer.getName(), sender.getName(), reason, System.currentTimeMillis(), (System.currentTimeMillis() + time), false, false, null);
        muteList.add(mute);
        plugin.getHikariHandler().newMute(mute);

        plugin.getMessageAPI().sendMessage(sender, "mutes.mute.admin.temp", offlinePlayer.getPlayer(), time);

        if (Objects.requireNonNull(offlinePlayer.getPlayer()).isOnline()) {
            plugin.getMessageAPI().sendMessage(offlinePlayer.getPlayer(), "mutes.mute.target.temp", offlinePlayer.getPlayer(), time);
        }

    }

    public void unMute(Player sender, OfflinePlayer offlinePlayer) {
        Mute mute = getMute(offlinePlayer);

        if (mute == null) {
            plugin.getMessageAPI().sendMessage(sender, "mutes.alreadyUnMuted", offlinePlayer.getPlayer(), 0L);
            return;
        }

        mute.setUnMuted(true);
        mute.setUnMutedBy(sender.getName());
        muteList.add(mute);
        plugin.getHikariHandler().updateMute(mute);

        plugin.getMessageAPI().sendMessage(sender, "mutes.unMute.admin", offlinePlayer.getPlayer(), 0L);

        if (offlinePlayer.getPlayer() == null) {
            return;
        }

        if (offlinePlayer.getPlayer().isOnline()) {
            plugin.getMessageAPI().sendMessage(offlinePlayer.getPlayer(), "mutes.unMute.target", sender, 0L);
        }
    }

    public boolean isMuted(OfflinePlayer player) {
        for (Mute mute : muteList) {
            if (mute.getPlayerName().equalsIgnoreCase(player.getName()) && mute.isActive()) {
                return true;
            }
            continue;
        }
        return false;
    }

    public Mute getMute(OfflinePlayer player) {
        for (Mute mute : muteList) {
            if (mute.getPlayerName().equalsIgnoreCase(player.getName()) && mute.isActive()) {
                return mute;
            }
            continue;
        }

        return null;
    }

    public List<Mute> getPlayerMutes(OfflinePlayer player) {
        List<Mute> mutes = new ArrayList<>();

        for (Mute mute : muteList) {
            if (mute.getPlayerName().equalsIgnoreCase(player.getName())) {
                mutes.add(mute);
                continue;
            }
            continue;
        }

        return mutes;
    }

}
