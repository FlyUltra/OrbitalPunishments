package cz.flyultra.database;


import cz.flyultra.OrbitalPunishments;

import java.sql.SQLException;

public class HikariController {
    public static Hikari DATABASE;

    private HikariHandler hikariHandler;
    private OrbitalPunishments plugin;

    /**
     * Setup the database
     *
     * @param plugin Main class instance
     * @return Whether it was successful
     */
    public boolean setup(OrbitalPunishments plugin) {
        this.plugin = plugin;
        DATABASE = new Hikari();
        this.hikariHandler = new HikariHandler(plugin);
        create();
        return true;
    }

    /*-----------------------------------------------------------------------------*/

    private void create() {
        try {

            String bans = "CREATE TABLE IF NOT EXISTS `"
                    + DatabaseOptions.BAN_TABLE + "` (`"
                    + DatabaseOptions.ID + "` int NOT NULL AUTO_INCREMENT, `"
                    + DatabaseOptions.BAN_PLAYER_NAME + "` varchar(200), `"
                    + DatabaseOptions.BAN_BANNED_BY + "` varchar(200), `"
                    + DatabaseOptions.BAN_REASON + "` TEXT, `"
                    + DatabaseOptions.BAN_OF_CREATION + "` BIGINT, `"
                    + DatabaseOptions.BAN_OF_EXPIRE + "` BIGINT, `"
                    + DatabaseOptions.BAN_IS_PERM + "` bit, `"
                    + DatabaseOptions.BAN_IS_UN_BANNED + "` bit, `"
                    + DatabaseOptions.BAN_UN_BANNED_BY + "` varchar(200), PRIMARY KEY (`" + DatabaseOptions.ID + "`));";
            getDatabase().getConnection().prepareStatement(bans).executeUpdate();

            String kicks = "CREATE TABLE IF NOT EXISTS `"
                    + DatabaseOptions.KICK_TABLE + "` (`"
                    + DatabaseOptions.ID + "` int NOT NULL AUTO_INCREMENT, `"
                    + DatabaseOptions.KICK_PLAYER_NAME + "` varchar(200), `"
                    + DatabaseOptions.KICK_KICKED_BY + "` varchar(200), `"
                    + DatabaseOptions.KICK_REASON + "` TEXT, `"
                    + DatabaseOptions.KICK_DATE_OF_KICK + "` BIGINT, PRIMARY KEY (`" + DatabaseOptions.ID + "`));";
            getDatabase().getConnection().prepareStatement(kicks).executeUpdate();


            String blackList = "CREATE TABLE IF NOT EXISTS `"
                    + DatabaseOptions.BLACK_LIST_TABLE + "` (`"
                    + DatabaseOptions.ID + "` int NOT NULL AUTO_INCREMENT, `"
                    + DatabaseOptions.BLACK_LIST_NAME + "` varchar(200), PRIMARY KEY (`" + DatabaseOptions.ID + "`));";
            getDatabase().getConnection().prepareStatement(blackList).executeUpdate();


            String mutes = "CREATE TABLE IF NOT EXISTS `"
                    + DatabaseOptions.MUTE_TABLE + "` (`"
                    + DatabaseOptions.ID + "` int NOT NULL AUTO_INCREMENT, `"
                    + DatabaseOptions.MUTE_PLAYER_NAME + "` varchar(200), `"
                    + DatabaseOptions.MUTE_MUTED_BY + "` varchar(200), `"
                    + DatabaseOptions.MUTE_REASON + "` TEXT, `"
                    + DatabaseOptions.MUTE_OF_CREATION + "` BIGINT, `"
                    + DatabaseOptions.MUTE_OF_EXPIRE + "` BIGINT, `"
                    + DatabaseOptions.MUTE_IS_PERM + "` bit, `"
                    + DatabaseOptions.MUTE_IS_UN_MUTED + "` bit, `"
                    + DatabaseOptions.MUTE_UN_MUTED_BY + "` varchar(200), PRIMARY KEY (`" + DatabaseOptions.ID + "`));";
            getDatabase().getConnection().prepareStatement(mutes).executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public HikariHandler getHikariHandler() {
        return hikariHandler;
    }

    public Hikari getDatabase() {
        return DATABASE;
    }

}
