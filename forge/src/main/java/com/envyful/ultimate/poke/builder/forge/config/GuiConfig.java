package com.envyful.ultimate.poke.builder.forge.config;

import com.envyful.api.config.type.ConfigInterface;
import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.config.type.PermissibleConfigItem;
import com.envyful.api.config.type.PositionableConfigItem;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import com.envyful.api.reforged.pixelmon.config.SpriteConfig;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.List;

@ConfigSerializable
public class GuiConfig extends AbstractYamlConfig {

    private SelectUI selectPartyUI = new SelectUI();
    private EditPokemonUI editPokemonUI = new EditPokemonUI();

    public GuiConfig() {
        super();
    }

    public SelectUI getSelectPartyUI() {
        return this.selectPartyUI;
    }

    public EditPokemonUI getEditPokemonUI() {
        return this.editPokemonUI;
    }

    @ConfigSerializable
    public static class SelectUI {

        private ConfigInterface guiSettings = new ConfigInterface(
                "UltimatePokeBuilder", 3, "BLOCK",
                ImmutableMap.of("one", new ConfigItem(
                        "minecraft:stained_glass_pane", 1, (byte) 15, " ", Lists.newArrayList(), Maps.newHashMap()
                ))
        );

        private PositionableConfigItem infoItem = new PositionableConfigItem(
                "minecraft:nether_star", 1, (byte) 0, "&bClick the pokemon to edit", Lists.newArrayList(),
                0, 4, Maps.newHashMap()
        );

        private ConfigItem eggItem = new ConfigItem(
                "minecraft:barrier", 1, (byte) 0, "&cCannot edit eggs", Lists.newArrayList(), Maps.newHashMap()
        );

        private List<Integer> partyPositions = Lists.newArrayList(
                10, 11, 12, 14, 15, 16
        );

        private SpriteConfig spriteSettings = new SpriteConfig();

        public ConfigInterface getGuiSettings() {
            return this.guiSettings;
        }

        public PositionableConfigItem getInfoItem() {
            return this.infoItem;
        }

        public List<Integer> getPartyPositions() {
            return this.partyPositions;
        }

        public SpriteConfig getSpriteSettings() {
            return this.spriteSettings;
        }

        public ConfigItem getEggItem() {
            return this.eggItem;
        }
    }

    @ConfigSerializable
    public static class EditPokemonUI {

        private ConfigInterface guiSettings = new ConfigInterface(
                "UltimatePokeBuilder", 4, "BLOCK",
                ImmutableMap.of("one", new ConfigItem(
                        "minecraft:stained_glass_pane", 1, (byte) 15, " ", Lists.newArrayList(), Maps.newHashMap()
                ))
        );

        private PositionableConfigItem backButton = new PositionableConfigItem(
                "pixelmon:eject_button", 1, (byte) 0, "&cBack",
                Lists.newArrayList(), 0, 0, Maps.newHashMap()
        );

        private int spritePos = 9;

        private PermissibleConfigItem evButton = new PermissibleConfigItem(
                "pixelmon:hp_up", 1, (byte) 0, "&eEVs",
                Lists.newArrayList(), 7, 2, "", Maps.newHashMap(), new ConfigItem(
                "minecraft:barrier", 1, (byte) 0, "&cNo Permission", Lists.newArrayList(),
                Maps.newHashMap())
        );

        private PermissibleConfigItem ivButton = new PermissibleConfigItem(
                "pixelmon:protein", 1, (byte) 0, "&eIVs",
                Lists.newArrayList(), 7, 1, "", Maps.newHashMap(), new ConfigItem(
                "minecraft:barrier", 1, (byte) 0, "&cNo Permission", Lists.newArrayList(),
                Maps.newHashMap())
        );

        private PermissibleConfigItem abilityButton = new PermissibleConfigItem(
                "pixelmon:power_bank", 1, (byte) 0, "&eAbility",
                Lists.newArrayList(), 5, 2, "", Maps.newHashMap(), new ConfigItem(
                "minecraft:barrier", 1, (byte) 0, "&cNo Permission", Lists.newArrayList(),
                Maps.newHashMap())
        );

        private PermissibleConfigItem growthButton = new PermissibleConfigItem(
                "pixelmon:choice_scarf", 1, (byte) 0, "&eGrowth",
                Lists.newArrayList(), 5, 1, "", Maps.newHashMap(), new ConfigItem(
                "minecraft:barrier", 1, (byte) 0, "&cNo Permission", Lists.newArrayList(),
                Maps.newHashMap())
        );

        private PermissibleConfigItem natureButton = new PermissibleConfigItem(
                "pixelmon:ground_gem", 1, (byte) 0, "&eNature",
                Lists.newArrayList(), 4, 2, "", Maps.newHashMap(), new ConfigItem(
                "minecraft:barrier", 1, (byte) 0, "&cNo Permission", Lists.newArrayList(),
                Maps.newHashMap())
        );

        private PermissibleConfigItem levelButton = new PermissibleConfigItem(
                "pixelmon:rare_candy", 1, (byte) 0, "&eLevel",
                Lists.newArrayList(), 4, 1, "", Maps.newHashMap(), new ConfigItem(
                "minecraft:barrier", 1, (byte) 0, "&cNo Permission", Lists.newArrayList(),
                Maps.newHashMap())
        );

        private PermissibleConfigItem pokeballButton = new PermissibleConfigItem(
                "pixelmon:poke_ball", 1, (byte) 0, "&ePokeBall",
                Lists.newArrayList(), 2, 2, "", Maps.newHashMap(), new ConfigItem(
                "minecraft:barrier", 1, (byte) 0, "&cNo Permission", Lists.newArrayList(),
                Maps.newHashMap())
        );

        private PermissibleConfigItem shinyButton = new PermissibleConfigItem(
                "pixelmon:shiny_stone", 1, (byte) 0, "&eShiny",
                Lists.newArrayList(), 2, 1, "", Maps.newHashMap(), new ConfigItem(
                "minecraft:barrier", 1, (byte) 0, "&cNo Permission", Lists.newArrayList(),
                Maps.newHashMap())
        );

        private SpriteConfig spriteSettings = new SpriteConfig();

        public ConfigInterface getGuiSettings() {
            return this.guiSettings;
        }

        public PositionableConfigItem getBackButton() {
            return this.backButton;
        }

        public int getSpritePos() {
            return this.spritePos;
        }

        public SpriteConfig getSpriteSettings() {
            return this.spriteSettings;
        }

        public PermissibleConfigItem getEvButton() {
            return this.evButton;
        }

        public PermissibleConfigItem getIvButton() {
            return this.ivButton;
        }

        public PermissibleConfigItem getAbilityButton() {
            return this.abilityButton;
        }

        public PermissibleConfigItem getGrowthButton() {
            return this.growthButton;
        }

        public PermissibleConfigItem getNatureButton() {
            return this.natureButton;
        }

        public PermissibleConfigItem getLevelButton() {
            return this.levelButton;
        }

        public PermissibleConfigItem getPokeballButton() {
            return this.pokeballButton;
        }

        public PermissibleConfigItem getShinyButton() {
            return this.shinyButton;
        }
    }
}