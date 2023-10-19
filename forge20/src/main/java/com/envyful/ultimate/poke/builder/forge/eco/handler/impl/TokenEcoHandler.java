package com.envyful.ultimate.poke.builder.forge.eco.handler.impl;

import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoHandler;
import com.envyful.ultimate.poke.builder.forge.eco.player.PokeBuilderAttribute;

public class TokenEcoHandler implements EcoHandler {

    private static final String HANDLER_ID = "Tokens";

    @Override
    public String getId() {
        return HANDLER_ID;
    }

    @Override
    public boolean hasBalance(ForgeEnvyPlayer player, double balance) {
        return getBalance(player) >= balance;
    }

    @Override
    public double getBalance(ForgeEnvyPlayer player) {
        PokeBuilderAttribute attribute = player.getAttribute(PokeBuilderAttribute.class);

        if (attribute == null) {
            return 0;
        }

        return attribute.getTokens();
    }

    @Override
    public void setBalance(ForgeEnvyPlayer player, double balance) {
        PokeBuilderAttribute attribute = player.getAttribute(PokeBuilderAttribute.class);

        if (attribute == null) {
            return;
        }

        attribute.setTokens(balance);
    }

    @Override
    public void addBalance(ForgeEnvyPlayer player, double balance) {
        this.setBalance(player, this.getBalance(player) + balance);
    }

    @Override
    public void takeBalance(ForgeEnvyPlayer player, double balance) {
        this.setBalance(player, this.getBalance(player) - balance);
    }
}
