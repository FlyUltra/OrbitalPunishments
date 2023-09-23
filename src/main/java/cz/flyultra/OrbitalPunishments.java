package cz.flyultra;

import cz.flyultra.database.HikariController;
import cz.flyultra.database.HikariHandler;
import cz.flyultra.modules.ban.api.BanAPI;
import cz.flyultra.modules.ban.command.BanCommand;
import cz.flyultra.modules.ban.command.UnBanCommand;
import cz.flyultra.modules.ban.controller.BanController;
import cz.flyultra.modules.ban.listener.BanListener;
import cz.flyultra.modules.blacklist.api.BlackListAPI;
import cz.flyultra.modules.blacklist.command.BlackListCommand;
import cz.flyultra.modules.blacklist.listener.BlackListListener;
import cz.flyultra.modules.kick.api.KickAPI;
import cz.flyultra.modules.kick.command.KickCommand;
import cz.flyultra.modules.kick.controller.KickController;
import cz.flyultra.modules.messages.MessageAPI;
import cz.flyultra.modules.mute.api.MuteAPI;
import cz.flyultra.modules.mute.command.MuteCommand;
import cz.flyultra.modules.mute.command.UnMuteCommand;
import cz.flyultra.modules.mute.controller.MuteController;
import cz.flyultra.modules.mute.listener.MuteListener;
import cz.flyultra.modules.profile.command.ProfileCommand;
import cz.flyultra.modules.profile.controller.ProfileController;
import cz.flyultra.utils.config.ConfigAPI;
import lombok.Getter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class OrbitalPunishments extends JavaPlugin {

    @Getter
    private static OrbitalPunishments instance;

    @Getter
    private MuteAPI muteAPI;
    @Getter
    private MuteController muteController;

    @Getter
    private BanAPI banAPI;
    @Getter
    private BanController banController;

    @Getter
    private BlackListAPI blackListAPI;

    @Getter
    private KickAPI kickAPI;
    @Getter
    private KickController kickController;

    @Getter
    private MessageAPI messageAPI;

    @Getter
    private ProfileController profileController;

    @Getter
    private ConfigAPI cfg;

    @Getter
    private HikariController hikariController;
    @Getter
    private HikariHandler hikariHandler;

    @Override
    public void onEnable() {
        instance = this;

        loadConfig();
        setUpDatabase();
        hikariHandler = new HikariHandler(this);

        loadModules();
        load();
    }

    public boolean setUpDatabase() {
        hikariController = new HikariController();
        return hikariController.setup(this);
    }

    public void loadConfig() {
        cfg = new ConfigAPI(this, "config");
        cfg.create();
    }

    public void loadModules() {

        messageAPI = new MessageAPI();

        muteAPI = new MuteAPI();
        muteController = new MuteController();

        banAPI = new BanAPI();
        banController = new BanController();

        blackListAPI = new BlackListAPI();

        kickAPI = new KickAPI();
        kickController = new KickController();

        profileController = new ProfileController();
    }

    public void load() {
        registerListener(new MuteListener());
        registerListener(new BanListener());
        registerListener(new BlackListListener());
        new MuteCommand();
        new UnMuteCommand();
        new KickCommand();
        new BanCommand();
        new UnBanCommand();
        new ProfileCommand();
        new BlackListCommand();
    }

    @Override
    public void onDisable() {

    }

    /*-----------------------------------------------------------------------------*/

    private void registerCommand(CommandExecutor commandExecutor, String cmd) {
        Objects.requireNonNull(getCommand(cmd)).setExecutor(commandExecutor);}

    /*-----------------------------------------------------------------------------*/

    private void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    /*-----------------------------------------------------------------------------*/

}