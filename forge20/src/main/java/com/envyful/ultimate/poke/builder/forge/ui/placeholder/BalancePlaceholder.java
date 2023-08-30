package com.envyful.ultimate.poke.builder.forge.ui.placeholder;

import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.api.text.parse.SimplePlaceholder;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoFactory;

public class BalancePlaceholder implements SimplePlaceholder {

    private final ForgeEnvyPlayer player;

    public BalancePlaceholder(ForgeEnvyPlayer player) {
        this.player = player;
    }

    @Override
    public String replace(String s) {
        return s.replace("%eco_balance%", String.valueOf(EcoFactory.getBalance(player)));
    }
}
