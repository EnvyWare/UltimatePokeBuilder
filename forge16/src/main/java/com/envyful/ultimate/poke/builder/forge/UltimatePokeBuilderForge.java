package com.envyful.ultimate.poke.builder.forge;

import com.envyful.api.concurrency.UtilConcurrency;
import com.envyful.api.concurrency.UtilLogger;
import com.envyful.api.config.yaml.YamlConfigFactory;
import com.envyful.api.database.Database;
import com.envyful.api.database.impl.SimpleHikariDatabase;
import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.api.forge.gui.factory.ForgeGuiFactory;
import com.envyful.api.forge.platform.ForgePlatformHandler;
import com.envyful.api.forge.player.ForgePlayerManager;
import com.envyful.api.forge.player.UsernameFactory;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.json.JsonUsernameCache;
import com.envyful.api.platform.PlatformProxy;
import com.envyful.api.player.SaveMode;
import com.envyful.api.player.save.impl.JsonSaveManager;
import com.envyful.ultimate.poke.builder.forge.command.PokeBuilderCommand;
import com.envyful.ultimate.poke.builder.forge.command.tokens.TokensCommand;
import com.envyful.ultimate.poke.builder.forge.config.GuiConfig;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderConfig;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderLocale;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderQueries;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoFactory;
import com.envyful.ultimate.poke.builder.forge.eco.player.PokeBuilderAttribute;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Mod("ultimatepokebuilder")
public class UltimatePokeBuilderForge {

    private static UltimatePokeBuilderForge instance;

    private ForgePlayerManager playerManager = new ForgePlayerManager();
    private ForgeCommandFactory commandFactory = new ForgeCommandFactory();

    private PokeBuilderConfig config;
    private PokeBuilderLocale locale;
    private GuiConfig guiConfig;

    private Database database;
    private Logger logger = LogManager.getLogger("ultimatepokebuilder");

    public UltimatePokeBuilderForge() {
        UtilLogger.setLogger(logger);
        UsernameFactory.setUsernameCache(new JsonUsernameCache(new File("config/UltimatePokeBuilder/usernames.json")));
        MinecraftForge.EVENT_BUS.register(this);
        instance = this;
        PlatformProxy.setHandler(ForgePlatformHandler.getInstance());
        GuiFactory.setPlatformFactory(new ForgeGuiFactory());
        GuiFactory.setPlayerManager(this.playerManager);
        PlatformProxy.setPlayerManager(this.playerManager);

        this.loadConfig();

        if (this.config.getSaveMode() == SaveMode.JSON) {
            this.playerManager.setSaveManager(new JsonSaveManager<>(this.playerManager));
        }

        EcoFactory.init(this.config);

        if (this.config.getEconomyHandler().equalsIgnoreCase("tokens")) {
            if (this.config.getSaveMode() == SaveMode.MYSQL) {
                UtilConcurrency.runAsync(() -> {
                    this.database = new SimpleHikariDatabase(this.config.getSqlDatabaseDetails());

                    try (Connection connection = this.database.getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement(PokeBuilderQueries.CREATE_TABLE)) {
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        } else {
            this.database = null;
        }
    }

    public void loadConfig() {
        try {
            this.config = YamlConfigFactory.getInstance(PokeBuilderConfig.class);
            this.locale = YamlConfigFactory.getInstance(PokeBuilderLocale.class);
            this.guiConfig = YamlConfigFactory.getInstance(GuiConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onServerStarting(RegisterCommandsEvent event) {
        this.playerManager.registerAttribute(PokeBuilderAttribute.class, PokeBuilderAttribute::new);
        this.commandFactory.registerCommand(event.getDispatcher(), this.commandFactory.parseCommand(new PokeBuilderCommand()));

        if (this.config.getEconomyHandler().equalsIgnoreCase("tokens")) {
            this.commandFactory.registerCommand(event.getDispatcher(), this.commandFactory.parseCommand(new TokensCommand()));
        }
    }

    public static UltimatePokeBuilderForge getInstance() {
        return instance;
    }

    public ForgePlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public static PokeBuilderConfig getConfig() {
        return instance.config;
    }

    public GuiConfig getGuiConfig() {
        return this.guiConfig;
    }

    public PokeBuilderLocale getLocale() {
        return this.locale;
    }

    public Database getDatabase() {
        return this.database;
    }
}
