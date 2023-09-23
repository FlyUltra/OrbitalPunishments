package cz.flyultra.database;

import cz.flyultra.OrbitalPunishments;
import cz.flyultra.modules.ban.Ban;
import cz.flyultra.modules.kick.Kick;
import cz.flyultra.modules.mute.Mute;
import org.bukkit.Bukkit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HikariHandler {

    private OrbitalPunishments plugin;

    public HikariHandler(OrbitalPunishments plugin) {
        this.plugin = plugin;
    }

    /*-----------------------------------------------------------------------------*/

    public List<Ban> getBans() {
        List<Ban> bans = new ArrayList<>();
        String selectSQL = "SELECT * FROM `" + DatabaseOptions.BAN_TABLE + "`;";
        ArrayList<DBRow> rows = HikariController.DATABASE.query(selectSQL);

        for (DBRow row : rows) {
            Ban ban = new Ban(
                    row.getString("player_name"),
                    row.getString("banned_by"),
                    row.getString("reason"),
                    row.getLong("date_of_creation"),
                    row.getLong("date_of_expiration"),
                    row.getBoolean("is_perm"),
                    row.getBoolean("is_un_banned"),
                    row.getString("un_banned_by")
            );
            bans.add(ban);
        }
        return bans;
    }


    public List<Mute> getMutes() {
        List<Mute> mutes = new ArrayList<>();
        String selectSQL = "SELECT * FROM `" + DatabaseOptions.MUTE_TABLE + "`;";
        ArrayList<DBRow> rows = HikariController.DATABASE.query(selectSQL);

        for (DBRow row : rows) {
            Mute mute = new Mute(
                    row.getString("player_name"),
                    row.getString("muted_by"),
                    row.getString("reason"),
                    row.getLong("date_of_creation"),
                    row.getLong("date_of_expiration"),
                    row.getBoolean("is_perm"),
                    row.getBoolean("is_un_muted"),
                    row.getString("un_muted_by")
            );
            mutes.add(mute);
        }
        return mutes;
    }

    public List<String> getBlackListPeople() {
        List<String> blackList = new ArrayList<>();
        String selectSQL = "SELECT * FROM `" + DatabaseOptions.BLACK_LIST_TABLE + "`;";
        ArrayList<DBRow> rows = HikariController.DATABASE.query(selectSQL);

        for (DBRow row : rows) {
            String name = row.getString("player_name");

            blackList.add(name);
        }
        return blackList;
    }


    public void newBan(Ban ban) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            String insertSQL = "INSERT INTO `" + DatabaseOptions.BAN_TABLE +
                    "` (player_name, banned_by, reason, date_of_creation, date_of_expiration, is_perm, is_un_banned, un_banned_by) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?)";
            HikariController.DATABASE.query(insertSQL,
                    ban.getPlayerName(),
                    ban.getBannedBy(),
                    ban.getReason(),
                    ban.getDateOfCreation(),
                    ban.getDateOfExpire(),
                    ban.isPerm(),
                    ban.isUnBanned(),
                    ban.getUnBannedBy()
                    );
        });
    }

    public void newMute(Mute mute) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            String insertSQL = "INSERT INTO `" + DatabaseOptions.MUTE_TABLE +
                    "` (player_name, muted_by, reason, date_of_creation, date_of_expiration, is_perm, is_un_muted, un_muted_by) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?)";
            HikariController.DATABASE.query(insertSQL,
                    mute.getPlayerName(),
                    mute.getMutedBy(),
                    mute.getReason(),
                    mute.getDateOfCreation(),
                    mute.getDateOfExpire(),
                    mute.isPerm(),
                    mute.isUnMuted(),
                    mute.getUnMutedBy()
            );
        });
    }


    public void newBlackList(String name) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            String insertSQL = "INSERT INTO `" + DatabaseOptions.BLACK_LIST_TABLE +
                    "` (player_name) " +
                    "VALUES " +
                    "(?)";
            HikariController.DATABASE.query(insertSQL,
                    name.toLowerCase());
        });
    }

    public void removeBlackList(String name) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            String insertSQL = "DELETE FROM `" + DatabaseOptions.BLACK_LIST_TABLE +
                    "` WHERE player_name ?;";
            HikariController.DATABASE.query(insertSQL,
                    name.toLowerCase());
        });
    }


    public void newKick(Kick kick) {

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            String insertSQL = "INSERT INTO `" + DatabaseOptions.KICK_TABLE +
                    "` (player_name, kicked_by, reason, date_of_kick) " +
                    "VALUES " +
                    "(?, ?, ?, ?)";
            HikariController.DATABASE.query(insertSQL,
                    kick.getPlayerName(),
                    kick.getKickedBy(),
                    kick.getReason(),
                    kick.getDateOfKick());
        });
    }


    public void updateBan(Ban ban) {

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            String insertSQL = "UPDATE `" + DatabaseOptions.BAN_TABLE + "` SET is_perm = ?, is_un_banned = ?, un_banned_by = ? WHERE player_name = ? AND date_of_creation = ?";
            HikariController.DATABASE.query(insertSQL,
                    ban.isPerm(),
                    ban.isUnBanned(),
                    ban.getUnBannedBy(),

                    ban.getPlayerName(),
                    ban.getDateOfCreation()
            );
        });
    }

    public void updateMute(Mute mute) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            String insertSQL = "UPDATE `" + DatabaseOptions.MUTE_TABLE + "` SET is_perm = ?, is_un_muted = ?, un_muted_by = ? WHERE player_name = ? AND date_of_creation = ?";
            HikariController.DATABASE.query(insertSQL,
                    mute.isPerm(),
                    mute.isUnMuted(),
                    mute.getUnMutedBy(),

                    mute.getPlayerName(),
                    mute.getDateOfCreation()
            );
        });
    }

}
