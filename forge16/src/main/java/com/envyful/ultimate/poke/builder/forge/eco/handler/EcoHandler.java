package com.envyful.ultimate.poke.builder.forge.eco.handler;

import com.envyful.api.forge.player.ForgeEnvyPlayer;

public interface EcoHandler {

    String getId();

    boolean hasBalance(ForgeEnvyPlayer player, int balance);

    int getBalance(ForgeEnvyPlayer player);

    void setBalance(ForgeEnvyPlayer player, int balance);

    void addBalance(ForgeEnvyPlayer player, int balance);

    void takeBalance(ForgeEnvyPlayer player, int balance);

}
