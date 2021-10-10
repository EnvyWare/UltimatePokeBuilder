package com.envyful.ultimate.poke.builder.forge.eco.handler.impl;

import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoHandler;
import net.minecraft.entity.player.EntityPlayerMP;

public class TokenEcoHandler implements EcoHandler {

    private static final String HANDLER_ID = "Tokens";

    @Override
    public String getId() {
        return HANDLER_ID;
    }

    @Override
    public int getBalance(EnvyPlayer<EntityPlayerMP> player) {
        return 0; //TODO:
    }

    @Override
    public void setBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        //TODO:
    }

    @Override
    public void addBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        //TODO:
    }

    @Override
    public void takeBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        //TODO:
    }
}
