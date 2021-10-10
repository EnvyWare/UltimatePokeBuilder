package com.envyful.ultimate.poke.builder.forge.eco.handler;

import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderConfig;
import com.envyful.ultimate.poke.builder.forge.eco.handler.impl.PixelmonEcoHandler;
import com.envyful.ultimate.poke.builder.forge.eco.handler.impl.TokenEcoHandler;
import net.minecraft.entity.player.EntityPlayerMP;

public class EcoFactory {

    private static EcoHandler handler;

    public static void init(PokeBuilderConfig config) {
        if (config.getEconomyHandler().equalsIgnoreCase("Tokens")) {
            handler = new TokenEcoHandler();
        } else {
            handler = new PixelmonEcoHandler();
        }
    }

    public static int getBalance(EnvyPlayer<EntityPlayerMP> player) {
        return handler.getBalance(player);
    }

    public static void setBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        handler.setBalance(player, balance);
    }

    public static void addBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        handler.addBalance(player, balance);
    }

    public static void takeBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        handler.addBalance(player, balance);
    }
}
