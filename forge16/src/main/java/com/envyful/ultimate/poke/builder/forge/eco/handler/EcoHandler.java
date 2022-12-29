package com.envyful.ultimate.poke.builder.forge.eco.handler;

import com.envyful.api.forge.player.ForgeEnvyPlayer;

public interface EcoHandler {

    String getId();

    boolean hasBalance(ForgeEnvyPlayer player, double balance);

    double getBalance(ForgeEnvyPlayer player);

    void setBalance(ForgeEnvyPlayer player, double balance);

    void addBalance(ForgeEnvyPlayer player, double balance);

    void takeBalance(ForgeEnvyPlayer player, double balance);

}
