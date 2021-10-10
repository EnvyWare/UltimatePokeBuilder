package com.envyful.ultimate.poke.builder.forge.player;

import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.api.forge.player.attribute.AbstractForgeAttribute;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;

public class PokeBuilderAttribute extends AbstractForgeAttribute<UltimatePokeBuilderForge> {

    private int tokens = 0;

    public PokeBuilderAttribute(UltimatePokeBuilderForge manager, EnvyPlayer<?> parent) {
        super(manager, (ForgeEnvyPlayer) parent);
    }

    @Override
    public void load() {
        if (this.manager.getDatabase() == null) {
            return;
        }

    }

    @Override
    public void save() {
        if (this.manager.getDatabase() == null) {
            return;
        }

    }
}
