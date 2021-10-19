package com.envyful.ultimate.poke.builder.forge.eco.handler.impl;

import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoHandler;
import com.envyful.ultimate.poke.builder.forge.eco.player.PokeBuilderAttribute;
import net.minecraft.entity.player.EntityPlayerMP;

public class TokenEcoHandler implements EcoHandler {

    private static final String HANDLER_ID = "Tokens";

    @Override
    public String getId() {
        return HANDLER_ID;
    }

    @Override
    public boolean hasBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        return getBalance(player) >= balance;
    }

    @Override
    public int getBalance(EnvyPlayer<EntityPlayerMP> player) {
        PokeBuilderAttribute attribute = player.getAttribute(UltimatePokeBuilderForge.class);

        if (attribute == null) {
            return 0;
        }

        return attribute.getTokens();
    }

    @Override
    public void setBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        PokeBuilderAttribute attribute = player.getAttribute(UltimatePokeBuilderForge.class);

        if (attribute == null) {
            return;
        }

        attribute.setTokens(balance);
    }

    @Override
    public void addBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        this.setBalance(player, this.getBalance(player) + balance);
    }

    @Override
    public void takeBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        this.setBalance(player, this.getBalance(player) - balance);
    }
}
