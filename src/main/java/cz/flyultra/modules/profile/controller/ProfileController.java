package cz.flyultra.modules.profile.controller;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.modules.ban.Ban;
import cz.flyultra.modules.kick.Kick;
import cz.flyultra.modules.mute.Mute;
import cz.flyultra.utils.Utils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.List;

public class ProfileController {

    private OrbitalPunishments plugin;

    public ProfileController() {
        plugin = OrbitalPunishments.getInstance();
    }


    public void sendBanLog(Player sender, OfflinePlayer player) {
        List<Ban> getBans = plugin.getBanAPI().getPlayerBans(player);

        sender.sendMessage(Utils.colorize("&8------&7(&cBANS&7)&8------"));
        sender.sendMessage(Utils.colorize(" "));
        sender.sendMessage(Utils.colorize(" &7-" + player.getName()));
        sender.sendMessage(Utils.colorize(" "));
        for (int i = 0; i < 5; i++) {
            Ban ban = getBans.get(i);
            Timestamp timestamp = new Timestamp(ban.getDateOfCreation());
            sender.sendMessage(Utils.colorize("&7"+(i+1)+". &a" + timestamp+ " &7- &a" + Utils.formatDate((ban.getDateOfExpire() - ban.getDateOfCreation())) + " &7"+ ban.getReason()));
        }
        sender.sendMessage(Utils.colorize(" "));
        sender.sendMessage(Utils.colorize("&8------------------"));

    }

    public void sendMuteLog(Player sender, OfflinePlayer player) {
        List<Mute> getMutes = plugin.getMuteAPI().getPlayerMutes(player);

        sender.sendMessage(Utils.colorize("&8------&7(&cMUTES&7)&8------"));
        sender.sendMessage(Utils.colorize(" "));
        sender.sendMessage(Utils.colorize(" &7- " + player.getName()));
        sender.sendMessage(Utils.colorize(" "));
        for (int i = 0; i < getMutes.size(); i++) {
            Mute mute = getMutes.get(i);
            Timestamp timestamp = new Timestamp(mute.getDateOfCreation());
            sender.sendMessage(Utils.colorize("&7"+(i+1)+". &a" + timestamp+ " &7- &a" + Utils.formatDate((mute.getDateOfExpire() - mute.getDateOfCreation())) + " &7"+ mute.getReason()));
        }
        sender.sendMessage(Utils.colorize(" "));
        sender.sendMessage(Utils.colorize("&8------------------"));

    }
}
