package com.envyful.ultimate.poke.builder.forge;

import com.envyful.api.concurrency.UtilConcurrency;
import com.envyful.api.config.yaml.YamlConfigFactory;
import com.envyful.api.database.Database;
import com.envyful.api.database.impl.SimpleHikariDatabase;
import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.api.forge.gui.factory.ForgeGuiFactory;
import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.api.forge.player.ForgePlayerManager;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.player.PlayerManager;
import com.envyful.ultimate.poke.builder.forge.command.PokeBuilderCommand;
import com.envyful.ultimate.poke.builder.forge.command.tokens.TokensCommand;
import com.envyful.ultimate.poke.builder.forge.config.GuiConfig;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderConfig;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderLocale;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderQueries;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoFactory;
import com.envyful.ultimate.poke.builder.forge.eco.player.PokeBuilderAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Mod(
        modid = "ultimatepokebuilder",
        name = "UltimatePokeBuilder Forge",
        version = UltimatePokeBuilderForge.VERSION,
        acceptableRemoteVersions = "*"
)
public class UltimatePokeBuilderForge {

    public static final String VERSION = "0.0.1";

    private static UltimatePokeBuilderForge instance;

    private PlayerManager<ForgeEnvyPlayer, EntityPlayerMP> playerManager = new ForgePlayerManager();
    private ForgeCommandFactory commandFactory = new ForgeCommandFactory();

    private PokeBuilderConfig config;
    private PokeBuilderLocale locale;
    private GuiConfig guiConfig;

    private Database database;

    @Mod.EventHandler
    public void onServerStarting(FMLPreInitializationEvent event) {
        GuiFactory.setPlatformFactory(new ForgeGuiFactory());
        instance = this;

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

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        this.playerManager.registerAttribute(this, PokeBuilderAttribute.class);
        this.commandFactory.registerCommand(event.getServer(), new PokeBuilderCommand());

        if (this.config.getEconomyHandler().equalsIgnoreCase("tokens")) {
            this.commandFactory.registerCommand(event.getServer(), new TokensCommand());
        }
    }

    public static UltimatePokeBuilderForge getInstance() {
        return instance;
    }

    public PlayerManager<ForgeEnvyPlayer, EntityPlayerMP> getPlayerManager() {
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
