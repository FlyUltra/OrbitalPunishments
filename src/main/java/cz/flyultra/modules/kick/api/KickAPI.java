package cz.flyultra.modules.kick.api;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.modules.kick.Kick;
import cz.flyultra.utils.Utils;
import org.bukkit.entity.Player;

import java.util.List;

public class KickAPI {

    private OrbitalPunishments plugin;

    public KickAPI() {
        plugin = OrbitalPunishments.getInstance();

    }

    public void kickPlayer(Player sender, Player target, String reason) {
        if (!target.isOnline()) {
            return;
        }

        Kick kick = new Kick(target.getName(), sender.getName(), reason, System.currentTimeMillis());

        List<String> stringList = plugin.getCfg().get().getStringList("kick.listMessage");

        System.out.println("Kick string: " + stringList);

        String kickString = stringList.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "\n")
                .replace("%reason%", reason);

        target.kickPlayer(Utils.colorize(kickString));
        newLog(kick);

    }

    public void newLog(Kick kick) {
        plugin.getHikariHandler().newKick(kick);
    }



}
