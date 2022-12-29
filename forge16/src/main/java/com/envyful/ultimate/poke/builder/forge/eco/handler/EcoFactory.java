package com.envyful.ultimate.poke.builder.forge.eco.handler;

import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderConfig;
import com.envyful.ultimate.poke.builder.forge.eco.handler.impl.PixelmonEcoHandler;
import com.envyful.ultimate.poke.builder.forge.eco.handler.impl.TokenEcoHandler;

public class EcoFactory {

    private static EcoHandler handler;

    public static void init(PokeBuilderConfig config) {
        if (config.getEconomyHandler().equalsIgnoreCase("Tokens")) {
            handler = new TokenEcoHandler();
        } else {
            handler = new PixelmonEcoHandler();
        }
    }

    public static boolean hasBalance(ForgeEnvyPlayer player, double balance) {
        return handler.hasBalance(player, balance);
    }

    public static double getBalance(ForgeEnvyPlayer player) {
        return handler.getBalance(player);
    }

    public static void setBalance(ForgeEnvyPlayer player, double balance) {
        handler.setBalance(player, balance);
    }

    public static void addBalance(ForgeEnvyPlayer player, double balance) {
        handler.addBalance(player, balance);
    }

    public static void takeBalance(ForgeEnvyPlayer player, double balance) {
        handler.takeBalance(player, balance);
    }
}
