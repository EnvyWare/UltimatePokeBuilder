package com.envyful.ultimate.poke.builder.forge.ui.type;

import com.envyful.api.config.type.ConfigInterface;
import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.config.type.PositionableConfigItem;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.config.UtilConfigItem;
import com.envyful.api.forge.gui.type.ConfirmationUI;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.gui.item.Displayable;
import com.envyful.api.gui.pane.Pane;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.api.player.PlayerManager;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import scala.actors.threadpool.Arrays;

import java.util.List;
import java.util.function.BiConsumer;

public class TrueFalseSelectionUI {

    private static void open(Builder config) {
        Pane pane = GuiFactory.paneBuilder()
                .topLeftX(0)
                .topLeftY(0)
                .width(9)
                .height(config.config.guiSettings.getHeight())
                .build();

        for (ConfigItem fillerItem : config.config.guiSettings.getFillerItems()) {
            pane.add(GuiFactory.displayable(UtilConfigItem.fromConfigItem(fillerItem)));
        }

        if (config.startsTrue) {
            UtilConfigItem.addConfigItem(pane, config.config.trueItem, (envyPlayer, clickType) -> {
                config.startsTrue = false;
                open(config);
            });
        } else {
            UtilConfigItem.addConfigItem(pane, config.config.falseItem, (envyPlayer, clickType) -> {
                config.startsTrue = true;
                open(config);
            });
        }

        UtilConfigItem.addConfigItem(pane, config.config.acceptItem, (envyPlayer, clickType) -> {
            if (config.startsTrue) {
                config.confirm.playerManager(config.playerManager);
                config.confirm.descriptionItem(UtilConfigItem.fromConfigItem(config.config.trueItem));
                config.confirm.returnHandler((envyPlayer1, clickType1) -> open(config));
                config.confirm.confirmHandler(config.trueAcceptHandler);
                config.confirm.open();
            } else {
                config.confirm.playerManager(config.playerManager);
                config.confirm.descriptionItem(UtilConfigItem.fromConfigItem(config.config.falseItem));
                config.confirm.returnHandler((envyPlayer1, clickType1) -> open(config));
                config.confirm.confirmHandler(config.falseAcceptHandler);
                config.confirm.open();
            }
        });

        UtilConfigItem.addConfigItem(pane, config.config.backButton, config.returnHandler);

        for (PositionableConfigItem displayItem : config.displayItems) {
            UtilConfigItem.addConfigItem(pane, displayItem);
        }

        GuiFactory.guiBuilder()
                .setPlayerManager(config.playerManager)
                .addPane(pane)
                .setCloseConsumer(envyPlayer -> {})
                .height(config.config.guiSettings.getHeight())
                .title(UtilChatColour.translateColourCodes('&', config.config.guiSettings.getTitle()))
                .build().open(config.player);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private EnvyPlayer<?> player = null;
        private PlayerManager<?, ?> playerManager = null;
        private TrueFalseConfig config = null;
        private BiConsumer<EnvyPlayer<?>, Displayable.ClickType> returnHandler = null;
        private BiConsumer<EnvyPlayer<?>, Displayable.ClickType> trueAcceptHandler = null;
        private BiConsumer<EnvyPlayer<?>, Displayable.ClickType> falseAcceptHandler = null;
        private ConfirmationUI.Builder confirm = null;
        private boolean startsTrue = true;
        private List<PositionableConfigItem> displayItems = Lists.newArrayList();

        protected Builder() {}

        public Builder player(EnvyPlayer<?> player) {
            this.player = player;
            return this;
        }

        public Builder playerManager(PlayerManager<?, ?> playerManager) {
            this.playerManager = playerManager;
            return this;
        }

        public Builder config(TrueFalseConfig config) {
            this.config = config;
            return this;
        }

        public Builder returnHandler(BiConsumer<EnvyPlayer<?>, Displayable.ClickType> returnHandler) {
            this.returnHandler = returnHandler;
            return this;
        }

        public Builder trueAcceptHandler(BiConsumer<EnvyPlayer<?>, Displayable.ClickType> trueAcceptHandler) {
            this.trueAcceptHandler = trueAcceptHandler;
            return this;
        }

        public Builder falseAcceptHandler(BiConsumer<EnvyPlayer<?>, Displayable.ClickType> falseAcceptHandler) {
            this.falseAcceptHandler = falseAcceptHandler;
            return this;
        }

        public Builder startsTrue(boolean startsTrue) {
            this.startsTrue = startsTrue;
            return this;
        }

        public Builder confirm(ConfirmationUI.Builder confirm) {
            this.confirm = confirm;
            return this;
        }

        public Builder displayItems(List<PositionableConfigItem> displayItems) {
            this.displayItems.addAll(displayItems);
            return this;
        }

        public Builder displayItem(PositionableConfigItem displayItem) {
            this.displayItems.add(displayItem);
            return this;
        }

        public Builder displayItems(PositionableConfigItem... displayItems) {
            this.displayItems.addAll(Arrays.asList(displayItems));
            return this;
        }

        public void open() {
            if (this.player == null || this.playerManager == null || this.config == null ||
                    this.returnHandler == null || this.trueAcceptHandler == null || this.falseAcceptHandler == null ||
                    this.confirm == null) {
                return;
            }

            TrueFalseSelectionUI.open(this);
        }
    }

    @ConfigSerializable
    public static class TrueFalseConfig {

        private ConfigInterface guiSettings = new ConfigInterface(
                "True or False", 3, "BLOCK",
                ImmutableMap.of("one", new ConfigItem(
                        "minecraft:stained_glass_pane", 1, (byte) 15, " ", Lists.newArrayList(), Maps.newHashMap()
                ))
        );

        private PositionableConfigItem trueItem;
        private PositionableConfigItem falseItem;

        private PositionableConfigItem acceptItem = new PositionableConfigItem(
                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&lCONFIRM",
                Lists.newArrayList(), 6, 1, Maps.newHashMap()
        );

        private PositionableConfigItem backButton = new PositionableConfigItem(
                "pixelmon:eject_button", 1, (byte) 0, "&cBack",
                Lists.newArrayList(), 0, 0, Maps.newHashMap()
        );

        public TrueFalseConfig(PositionableConfigItem trueItem, PositionableConfigItem falseItem) {
            this.trueItem = trueItem;
            this.falseItem = falseItem;
        }

        public TrueFalseConfig() {}

        public ConfigInterface getGuiSettings() {
            return this.guiSettings;
        }

        public PositionableConfigItem getTrueItem() {
            return this.trueItem;
        }

        public PositionableConfigItem getFalseItem() {
            return this.falseItem;
        }

        public PositionableConfigItem getAcceptItem() {
            return this.acceptItem;
        }

        public PositionableConfigItem getBackButton() {
            return this.backButton;
        }
    }
}
