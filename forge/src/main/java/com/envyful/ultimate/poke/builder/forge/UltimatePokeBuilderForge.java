package com.envyful.ultimate.poke.builder.forge;

import com.envyful.api.config.yaml.YamlConfigFactory;
import com.envyful.api.forge.gui.factory.ForgeGuiFactory;
import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.api.forge.player.ForgePlayerManager;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.api.player.PlayerManager;
import com.envyful.ultimate.poke.builder.forge.config.GuiConfig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.client.GuiBackupFailed;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.IOException;

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

    private GuiConfig guiConfig;

    @Mod.EventHandler
    public void onServerStarting(FMLPreInitializationEvent event) {
        GuiFactory.setPlatformFactory(new ForgeGuiFactory());
        instance = this;

        this.loadConfig();
    }

    public void loadConfig() {
        try {
            this.guiConfig = YamlConfigFactory.getInstance(GuiConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    public static UltimatePokeBuilderForge getInstance() {
        return instance;
    }

    public PlayerManager<ForgeEnvyPlayer, EntityPlayerMP> getPlayerManager() {
        return this.playerManager;
    }

    public GuiConfig getGuiConfig() {
        return this.guiConfig;
    }
}
