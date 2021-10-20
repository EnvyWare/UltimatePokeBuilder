package com.envyful.ultimate.poke.builder.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.type.ConfigInterface;
import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.config.type.PermissibleConfigItem;
import com.envyful.api.config.type.PositionableConfigItem;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import com.envyful.api.forge.gui.type.ConfirmationUI;
import com.envyful.api.forge.gui.type.TrueFalseSelectionUI;
import com.envyful.api.reforged.pixelmon.config.SpriteConfig;
import com.envyful.ultimate.poke.builder.forge.ui.type.DynamicSelectionUI;
import com.envyful.ultimate.poke.builder.forge.ui.type.MultiSelectionUI;
import com.envyful.ultimate.poke.builder.forge.ui.type.NumberModificationUI;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.HashMap;
import java.util.List;

@ConfigSerializable
@ConfigPath("config/UltimatePokeBuilder/guis.yml")
@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class GuiConfig extends AbstractYamlConfig {

    private SelectUI selectPartyUI = new SelectUI();
    private EditPokemonUI editPokemonUI = new EditPokemonUI();
    private ShinyUI shinyUI = new ShinyUI();
    private AbilitiesUI abilitiesUI = new AbilitiesUI();
    private EvUI evUI = new EvUI();
    private IvUI ivUI = new IvUI();

    public GuiConfig() {
        super();
    }

    public SelectUI getSelectPartyUI() {
        return this.selectPartyUI;
    }

    public EditPokemonUI getEditPokemonUI() {
        return this.editPokemonUI;
    }

    public ShinyUI getShinyUI() {
        return this.shinyUI;
    }

    public AbilitiesUI getAbilitiesUI() {
        return this.abilitiesUI;
    }

    public EvUI getEvUI() {
        return this.evUI;
    }

    public IvUI getIvUI() {
        return this.ivUI;
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
                4, 0, Maps.newHashMap()
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
                "pixelmon:power_band", 1, (byte) 0, "&eAbility",
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

    @ConfigSerializable
    public static class ShinyUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private TrueFalseSelectionUI.TrueFalseConfig trueFalseSettings = new TrueFalseSelectionUI.TrueFalseConfig(
                new PositionableConfigItem("pixelmon:shiny_stone", 1, (byte) 0 , "&6&lSHINY",
                                           Lists.newArrayList(),
                                           2, 1, Maps.newHashMap()),
                new PositionableConfigItem("pixelmon:dusk_stone", 1, (byte) 0 , "&f&lNON-SHINY",
                                           Lists.newArrayList(),
                                           2, 1, Maps.newHashMap())
        );

        private int pokemonPos = 9;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public ShinyUI() {}

        public int getPokemonPos() {
            return this.pokemonPos;
        }

        public TrueFalseSelectionUI.TrueFalseConfig getTrueFalseSettings() {
            return this.trueFalseSettings;
        }

        public SpriteConfig getSpriteConfig() {
            return this.spriteConfig;
        }

        public ConfirmationUI.ConfirmConfig getConfirmConfig() {
            return this.confirmConfig;
        }
    }


    @ConfigSerializable
    public static class AbilitiesUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private DynamicSelectionUI.DynamicSelectionConfig abilitySelection = new DynamicSelectionUI.DynamicSelectionConfig(
                "UltimatePokeBuilder", 3,
                "&b", Lists.newArrayList(13, 14, 15),
                new ConfigItem("pixelmon:ability_capsule", 1, (byte) 0, "",
                               Lists.newArrayList(
                                       "&b&lAbility Cost: &a200 Tokens",
                                       "&b&lHidden Ability Cost: &a400 Tokens"
                               ), Maps.newHashMap())
        );

        private int pokemonPos = 9;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public AbilitiesUI() {}

        public int getPokemonPos() {
            return this.pokemonPos;
        }

        public DynamicSelectionUI.DynamicSelectionConfig getAbilitySelection() {
            return this.abilitySelection;
        }

        public SpriteConfig getSpriteConfig() {
            return this.spriteConfig;
        }

        public ConfirmationUI.ConfirmConfig getConfirmConfig() {
            return this.confirmConfig;
        }
    }

    @ConfigSerializable
    public static class EvUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private MultiSelectionUI.MultiSelectionConfig evSelection = new MultiSelectionUI.MultiSelectionConfig(
                "UltimatePokeBuilder", 4,
                new HashMap<String, ConfigItem>() {
                    {
                        this.put("hp", new ConfigItem("pixelmon:power_weight", 1, (byte) 0, "&a&lHP", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("attack", new ConfigItem("pixelmon:power_bracer", 1, (byte) 0, "&c&lAttack", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("defence", new ConfigItem("pixelmon:power_belt", 1, (byte) 0, "&6&lDefence", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("specialattack", new ConfigItem("pixelmon:power_lens", 1, (byte) 0, "&d&lSp. Attack", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("specialdefence", new ConfigItem("pixelmon:power_band", 1, (byte) 0, "&e&lSp. Defence", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("speed", new ConfigItem("pixelmon:power_anklet", 1, (byte) 0, "&b&lSpeed", Lists.newArrayList(), Maps.newHashMap()));
                    }
                }, Lists.newArrayList(12, 13, 14, 21, 22, 23)
        );

        private NumberModificationUI.NumberModificationConfig evEditAmount = new NumberModificationUI.NumberModificationConfig(
                "UPB", 4, 31, 0,  new PositionableConfigItem(
                "minecraft:chest", 1, (byte) 0, "&bCurrent Value: &a%value%",
                Lists.newArrayList(), 2, 1, Maps.newHashMap()),
                new HashMap<String, NumberModificationUI.EditValueButton>() {
                    {
                        this.put("one", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+1",
                                Lists.newArrayList(), 4, 1, Maps.newHashMap()
                        ), 1));

                        this.put("two", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+5",
                                Lists.newArrayList(), 5, 1, Maps.newHashMap()
                        ), 5));

                        this.put("three", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+10",
                                Lists.newArrayList(), 6, 1, Maps.newHashMap()
                        ), 10));

                        this.put("four", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+15",
                                Lists.newArrayList(), 7, 1, Maps.newHashMap()
                        ), 15));

                        this.put("five", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-1",
                                Lists.newArrayList(), 4, 2, Maps.newHashMap()
                        ), -1));

                        this.put("six", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-5",
                                Lists.newArrayList(), 5, 2, Maps.newHashMap()
                        ), -5));

                        this.put("seven", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-10",
                                Lists.newArrayList(), 6, 2, Maps.newHashMap()
                        ), -10));

                        this.put("eight", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-15",
                                Lists.newArrayList(), 7, 2, Maps.newHashMap()
                        ), -15));
                    }
                }
        );

        private int pokemonPos = 9;
        private int editDisplayPos = 18;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public EvUI() {}

        public int getPokemonPos() {
            return this.pokemonPos;
        }

        public MultiSelectionUI.MultiSelectionConfig getEvSelection() {
            return this.evSelection;
        }

        public SpriteConfig getSpriteConfig() {
            return this.spriteConfig;
        }

        public ConfirmationUI.ConfirmConfig getConfirmConfig() {
            return this.confirmConfig;
        }

        public NumberModificationUI.NumberModificationConfig getEvEditAmount() {
            return this.evEditAmount;
        }

        public int getEditDisplayPos() {
            return this.editDisplayPos;
        }
    }


    @ConfigSerializable
    public static class IvUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private MultiSelectionUI.MultiSelectionConfig ivSelection = new MultiSelectionUI.MultiSelectionConfig(
                "UltimatePokeBuilder", 4,
                new HashMap<String, ConfigItem>() {
                    {
                        this.put("hp", new ConfigItem("pixelmon:power_weight", 1, (byte) 0, "&a&lHP", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("attack", new ConfigItem("pixelmon:power_bracer", 1, (byte) 0, "&c&lAttack", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("defence", new ConfigItem("pixelmon:power_belt", 1, (byte) 0, "&6&lDefence", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("specialattack", new ConfigItem("pixelmon:power_lens", 1, (byte) 0, "&d&lSp. Attack", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("specialdefence", new ConfigItem("pixelmon:power_band", 1, (byte) 0, "&e&lSp. Defence", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("speed", new ConfigItem("pixelmon:power_anklet", 1, (byte) 0, "&b&lSpeed", Lists.newArrayList(), Maps.newHashMap()));
                    }
                }, Lists.newArrayList(12, 13, 14, 21, 22, 23)
        );

        private NumberModificationUI.NumberModificationConfig ivEditAmount = new NumberModificationUI.NumberModificationConfig(
                "UPB", 4, 31, 0,  new PositionableConfigItem(
                "minecraft:chest", 1, (byte) 0, "&bCurrent Value: &a%value%",
                Lists.newArrayList(), 2, 1, Maps.newHashMap()),
                new HashMap<String, NumberModificationUI.EditValueButton>() {
                    {
                        this.put("one", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+1",
                                Lists.newArrayList(), 4, 1, Maps.newHashMap()
                        ), 1));

                        this.put("two", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+5",
                                Lists.newArrayList(), 5, 1, Maps.newHashMap()
                        ), 5));

                        this.put("three", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+10",
                                Lists.newArrayList(), 6, 1, Maps.newHashMap()
                        ), 10));

                        this.put("four", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+15",
                                Lists.newArrayList(), 7, 1, Maps.newHashMap()
                        ), 15));

                        this.put("five", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-1",
                                Lists.newArrayList(), 4, 2, Maps.newHashMap()
                        ), -1));

                        this.put("six", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-5",
                                Lists.newArrayList(), 5, 2, Maps.newHashMap()
                        ), -5));

                        this.put("seven", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-10",
                                Lists.newArrayList(), 6, 2, Maps.newHashMap()
                        ), -10));

                        this.put("eight", new NumberModificationUI.EditValueButton(new PositionableConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-15",
                                Lists.newArrayList(), 7, 2, Maps.newHashMap()
                        ), -15));
                    }
                }
        );

        private int pokemonPos = 9;
        private int editDisplayPos = 18;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public IvUI() {}

        public int getPokemonPos() {
            return this.pokemonPos;
        }

        public MultiSelectionUI.MultiSelectionConfig getIvSelection() {
            return this.ivSelection;
        }

        public SpriteConfig getSpriteConfig() {
            return this.spriteConfig;
        }

        public ConfirmationUI.ConfirmConfig getConfirmConfig() {
            return this.confirmConfig;
        }

        public NumberModificationUI.NumberModificationConfig getIvEditAmount() {
            return this.ivEditAmount;
        }

        public int getEditDisplayPos() {
            return this.editDisplayPos;
        }
    }
}
