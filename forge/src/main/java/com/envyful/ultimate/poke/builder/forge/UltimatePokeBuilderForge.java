package com.envyful.ultimate.poke.builder.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
        modid = "ultimatepokebuilder",
        name = "UltimatePokeBuilder Forge",
        version = UltimatePokeBuilderForge.VERSION,
        acceptableRemoteVersions = "*"
)
public class UltimatePokeBuilderForge {

    public static final String VERSION = "0.0.1";

    private static UltimatePokeBuilderForge instance;

    @Mod.EventHandler
    public void onServerStarting(FMLPreInitializationEvent event) {
        instance = this;
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    public static UltimatePokeBuilderForge getInstance() {
        return instance;
    }
}
