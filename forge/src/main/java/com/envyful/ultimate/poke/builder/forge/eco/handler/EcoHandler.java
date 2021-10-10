package com.envyful.ultimate.poke.builder.forge.eco.handler;

import com.envyful.api.player.EnvyPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public interface EcoHandler {

    String getId();

    int getBalance(EnvyPlayer<EntityPlayerMP> player);

    void setBalance(EnvyPlayer<EntityPlayerMP> player, int balance);

    void addBalance(EnvyPlayer<EntityPlayerMP> player, int balance);

    void takeBalance(EnvyPlayer<EntityPlayerMP> player, int balance);

}
