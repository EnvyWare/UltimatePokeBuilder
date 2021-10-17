package com.envyful.ultimate.poke.builder.forge.ui.confirm;

import com.envyful.api.config.type.ConfigInterface;
import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.config.type.PositionableConfigItem;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.config.UtilConfigItem;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.gui.item.Displayable;
import com.envyful.api.gui.pane.Pane;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.function.BiConsumer;

public class ConfirmationUI {

    private static void open(Builder builder) {
        ConfirmConfig config = builder.confirmConfig;
        Pane pane = GuiFactory.paneBuilder()
                .topLeftX(0)
                .topLeftY(0)
                .width(9)
                .height(config.getGuiSettings().getHeight())
                .build();

        for (ConfigItem fillerItem : config.getGuiSettings().getFillerItems()) {
            pane.add(GuiFactory.displayable(UtilConfigItem.fromConfigItem(fillerItem)));
        }

        UtilConfigItem.addConfigItem(pane, config.getAcceptItem(), builder.confirmHandler);
        UtilConfigItem.addConfigItem(pane, config.getDeclineItem(), builder.returnHandler);

        if (builder.descriptionItem != null) {
            pane.set(config.getDescriptionPosition() % 9, config.getDescriptionPosition() / 9,
                     GuiFactory.displayable(builder.descriptionItem)
            );
        }

        GuiFactory.guiBuilder()
                .setPlayerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                .addPane(pane)
                .setCloseConsumer(envyPlayer -> {})
                .height(config.getGuiSettings().getHeight())
                .title(UtilChatColour.translateColourCodes('&', config.getGuiSettings().getTitle()))
                .build().open(builder.player);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private EnvyPlayer<?> player;
        private ItemStack descriptionItem;
        private ConfirmConfig confirmConfig;
        private BiConsumer<EnvyPlayer<?>, Displayable.ClickType> returnHandler;
        private BiConsumer<EnvyPlayer<?>, Displayable.ClickType> confirmHandler;

        protected Builder() {}

        public Builder player(EnvyPlayer<?> player) {
            this.player = player;
            return this;
        }

        public Builder descriptionItem(ItemStack descriptionItem) {
            this.descriptionItem = descriptionItem.copy();
            return this;
        }

        public Builder config(ConfirmConfig config) {
            this.confirmConfig = config;
            return this;
        }

        public Builder returnHandler(BiConsumer<EnvyPlayer<?>, Displayable.ClickType> returnHandler) {
            this.returnHandler = returnHandler;
            return this;
        }

        public Builder confirmHandler(BiConsumer<EnvyPlayer<?>, Displayable.ClickType> confirmHandler) {
            this.confirmHandler = confirmHandler;
            return this;
        }

        public void open() {
            if (this.player == null || this.confirmConfig == null) {
                return;
            }

            ConfirmationUI.open(this);
        }
    }

    @ConfigSerializable
    public static class ConfirmConfig {

        private ConfigInterface guiSettings = new ConfigInterface(
                "UltimatePokeBuilder", 4, "BLOCK",
                ImmutableMap.of("one", new ConfigItem(
                        "minecraft:stained_glass_pane", 1, (byte) 15, " ", Lists.newArrayList(), Maps.newHashMap()
                ))
        );

        private PositionableConfigItem declineItem = new PositionableConfigItem("minecraft:wool", 1, (byte) 14, "&c&lDECLINE",
                                                                    Lists.newArrayList(), 2, 1, Maps.newHashMap());


        private PositionableConfigItem acceptItem = new PositionableConfigItem("minecraft:wool", 1, (byte) 14,
                                                                               "&a&lACCEPT",
                                                                                Lists.newArrayList(), 6, 1,
                                                                               Maps.newHashMap());

        private int descriptionPosition = 13;

        public ConfirmConfig() {
        }

        public ConfigInterface getGuiSettings() {
            return this.guiSettings;
        }

        public PositionableConfigItem getDeclineItem() {
            return this.declineItem;
        }

        public PositionableConfigItem getAcceptItem() {
            return this.acceptItem;
        }

        public int getDescriptionPosition() {
            return this.descriptionPosition;
        }
    }
}
