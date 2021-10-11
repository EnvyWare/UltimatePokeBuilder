package com.envyful.ultimate.poke.builder.forge.ui.confirm;

import com.envyful.api.config.type.ConfigInterface;
import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.gui.item.Displayable;
import com.envyful.api.player.EnvyPlayer;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.function.Function;

public class ConfirmationUI {

    private static void open() {}//TODO

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private EnvyPlayer<?> player;
        private ItemStack descriptionItem;
        private ConfirmConfig confirmConfig;
        private Function<EnvyPlayer<?>, Displayable.ClickType> returnHandler;
        private Function<EnvyPlayer<?>, Displayable.ClickType> confirmHandler;

        protected Builder() {}

        //TODO:

    }

    @ConfigSerializable
    public static class ConfirmConfig {

        private ConfigInterface guiSettings = new ConfigInterface(
                "UltimatePokeBuilder", 4, "BLOCK",
                ImmutableMap.of("one", new ConfigItem(
                        "minecraft:stained_glass_pane", 1, (byte) 15, " ", Lists.newArrayList(), Maps.newHashMap()
                ))
        );

        public ConfirmConfig() {
        }

        public ConfigInterface getGuiSettings() {
            return this.guiSettings;
        }
    }
}
