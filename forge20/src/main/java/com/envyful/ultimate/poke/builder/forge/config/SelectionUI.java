package com.envyful.ultimate.poke.builder.forge.config;

import com.envyful.api.config.type.ConfigInterface;
import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.config.type.ExtendedConfigItem;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.Map;

@ConfigSerializable
public class SelectionUI {

    private ConfigInterface guiSettings;

    private ExtendedConfigItem backButton = ExtendedConfigItem.builder()
            .type("pixelmon:eject_button")
            .amount(1)
            .name("&cBack")
            .positions(0, 0)
            .build();

    private int spritePos = 9;

    private ExtendedConfigItem editingDisplay;

    private Map<String, NextFunctionItem> items;

    public SelectionUI(ConfigInterface guiSettings, ExtendedConfigItem editingDisplay, Map<String, NextFunctionItem> items) {
        this.guiSettings = guiSettings;
        this.editingDisplay = editingDisplay;
        this.items = items;
    }

    public SelectionUI() {
    }

    public ConfigInterface getGuiSettings() {
        return this.guiSettings;
    }

    public ExtendedConfigItem getBackButton() {
        return this.backButton;
    }

    public int getSpritePos() {
        return this.spritePos;
    }

    public ExtendedConfigItem getEditingDisplay() {
        return this.editingDisplay;
    }

    public Map<String, NextFunctionItem> getItems() {
        return this.items;
    }

    @ConfigSerializable
    public static class NextFunctionItem {

        private ConfigItem displayItem;
        private boolean confirmNext;

        public NextFunctionItem(ConfigItem displayItem, boolean confirmNext) {
            this.displayItem = displayItem;
            this.confirmNext = confirmNext;
        }

        public NextFunctionItem() {
        }

        public ConfigItem getDisplayItem() {
            return this.displayItem;
        }

        public boolean isConfirmNext() {
            return this.confirmNext;
        }
    }
}
