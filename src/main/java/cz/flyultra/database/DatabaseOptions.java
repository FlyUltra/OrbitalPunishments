package cz.flyultra.database;


import cz.flyultra.OrbitalPunishments;

public class DatabaseOptions {

    public static String TABLE_PREFIX = OrbitalPunishments.getInstance().getCfg().get().getString("database.tablePrefix");

    public static String ID = "id";

    public static String BAN_TABLE = TABLE_PREFIX + "bans";
    public static String BAN_PLAYER_NAME = "player_name";
    public static String BAN_BANNED_BY = "banned_by";
    public static String BAN_REASON = "reason";
    public static String BAN_OF_CREATION = "date_of_creation";
    public static String BAN_OF_EXPIRE = "date_of_expiration";
    public static String BAN_IS_PERM = "is_perm";
    public static String BAN_IS_UN_BANNED = "is_un_banned";
    public static String BAN_UN_BANNED_BY = "un_banned_by";


    public static String KICK_TABLE = TABLE_PREFIX + "kicks";
    public static String KICK_PLAYER_NAME = "player_name";
    public static String KICK_KICKED_BY = "kicked_by";
    public static String KICK_REASON = "reason";
    public static String KICK_DATE_OF_KICK = "date_of_kick";


    public static String BLACK_LIST_TABLE = TABLE_PREFIX + "black_list";
    public static String BLACK_LIST_NAME = "player_name";


    public static String MUTE_TABLE = TABLE_PREFIX + "mutes";
    public static String MUTE_PLAYER_NAME = "player_name";
    public static String MUTE_MUTED_BY = "muted_by";
    public static String MUTE_REASON = "reason";
    public static String MUTE_OF_CREATION = "date_of_creation";
    public static String MUTE_OF_EXPIRE = "date_of_expiration";
    public static String MUTE_IS_PERM = "is_perm";
    public static String MUTE_IS_UN_MUTED = "is_un_muted";
    public static String MUTE_UN_MUTED_BY = "un_muted_by";



}
