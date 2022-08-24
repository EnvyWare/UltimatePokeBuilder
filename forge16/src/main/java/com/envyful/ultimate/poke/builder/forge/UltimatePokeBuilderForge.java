package com.envyful.ultimate.poke.builder.forge;

import com.envyful.api.concurrency.UtilConcurrency;
import com.envyful.api.config.yaml.YamlConfigFactory;
import com.envyful.api.database.Database;
import com.envyful.api.database.impl.SimpleHikariDatabase;
import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.api.forge.gui.factory.ForgeGuiFactory;
import com.envyful.api.forge.player.ForgePlayerManager;
import com.envyful.api.gui.factory.GuiFactory;
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

    public UltimatePokeBuilderForge() {
        MinecraftForge.EVENT_BUS.register(this);
        instance = this;
        GuiFactory.setPlatformFactory(new ForgeGuiFactory());

        this.loadConfig();

        EcoFactory.init(this.config);

        if (this.config.getEconomyHandler().equalsIgnoreCase("tokens")) {
            UtilConcurrency.runAsync(() -> {
                this.database = new SimpleHikariDatabase(this.config.getSqlDatabaseDetails());

                try (Connection connection = this.database.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(PokeBuilderQueries.CREATE_TABLE)) {
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
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
        this.playerManager.registerAttribute(this, PokeBuilderAttribute.class);
        this.commandFactory.registerCommand(event.getDispatcher(), new PokeBuilderCommand());

        if (this.config.getEconomyHandler().equalsIgnoreCase("tokens")) {
            this.commandFactory.registerCommand(event.getDispatcher(), new TokensCommand());
        }
    }

    public static UltimatePokeBuilderForge getInstance() {
        return instance;
    }

    public ForgePlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public PokeBuilderConfig getConfig() {
        return this.config;
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
