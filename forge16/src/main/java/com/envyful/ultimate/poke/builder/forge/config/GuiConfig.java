package com.envyful.ultimate.poke.builder.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.type.ConfigInterface;
import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.config.type.ExtendedConfigItem;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import com.envyful.api.forge.gui.type.*;
import com.envyful.api.reforged.pixelmon.config.SpriteConfig;
import com.envyful.api.type.Pair;
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
    private PokeBallUI ballUI = new PokeBallUI();
    private LevelUI levelUI = new LevelUI();
    private GrowthUI growthUI = new GrowthUI();
    private NatureUI natureUI = new NatureUI();
    private UntradeableUI untradeableUI = new UntradeableUI();
    private GenderUI genderUI = new GenderUI();

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

    public PokeBallUI getBallUI() {
        return this.ballUI;
    }

    public LevelUI getLevelUI() {
        return this.levelUI;
    }

    public GrowthUI getGrowthUI() {
        return this.growthUI;
    }

    public NatureUI getNatureUI() {
        return this.natureUI;
    }

    public UntradeableUI getUntradeableUI() {
        return this.untradeableUI;
    }

    public GenderUI getGenderUI() {
        return this.genderUI;
    }

    @ConfigSerializable
    public static class SelectUI {

        private ConfigInterface guiSettings = new ConfigInterface(
                "UltimatePokeBuilder", 3, "BLOCK",
                ImmutableMap.of("one", new ConfigItem(
                        "minecraft:stained_glass_pane", 1, (byte) 15, " ", Lists.newArrayList(), Maps.newHashMap()
                ))
        );

        private ExtendedConfigItem infoItem = new ExtendedConfigItem(
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

        private ConfigItem blacklistedItem = new ConfigItem(
                "minecraft:barrier", 1, (byte) 0, "&c&lBlocked", Lists.newArrayList(), Maps.newHashMap()
        );

        public SelectUI() {
        }

        public ConfigInterface getGuiSettings() {
            return this.guiSettings;
        }

        public ExtendedConfigItem getInfoItem() {
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

        public ConfigItem getBlacklistedItem() {
            return this.blacklistedItem;
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

        private ExtendedConfigItem backButton = new ExtendedConfigItem(
                "pixelmon:eject_button", 1, (byte) 0, "&cBack",
                Lists.newArrayList(), 0, 0, Maps.newHashMap()
        );

        private int spritePos = 9;

        private ExtendedConfigItem evButton = ExtendedConfigItem.builder()
                .type("pixelmon:hp_up")
                .amount(1)
                .name("&eEVs")
                .positions(Pair.of(7, 2))
                .requiresPermission("none", new ConfigItem("minecraft:barrier", 1, "&c&lNo Permission", Lists.newArrayList()))
                .build();

        private ExtendedConfigItem ivButton = ExtendedConfigItem.builder()
                .type("pixelmon:protein")
                .amount(1)
                .name("&eIVs")
                .positions(Pair.of(7, 1))
                .requiresPermission("none", new ConfigItem("minecraft:barrier", 1, "&c&lNo Permission", Lists.newArrayList()))
                .build();

        private ExtendedConfigItem abilityButton = ExtendedConfigItem.builder()
                .type("pixelmon:power_band")
                .amount(1)
                .name("&eAbility")
                .positions(Pair.of(5, 2))
                .requiresPermission("none", new ConfigItem("minecraft:barrier", 1, "&c&lNo Permission", Lists.newArrayList()))
                .build();

        private ExtendedConfigItem growthButton = ExtendedConfigItem.builder()
                .type("pixelmon:choice_scarf")
                .amount(1)
                .name("&eGrowth")
                .positions(Pair.of(5, 1))
                .requiresPermission("none", new ConfigItem("minecraft:barrier", 1, "&c&lNo Permission", Lists.newArrayList()))
                .build();

        private ExtendedConfigItem natureButton = ExtendedConfigItem.builder()
                .type("pixelmon:ground_gem")
                .amount(1)
                .name("&eNature")
                .positions(Pair.of(4, 2))
                .requiresPermission("none", new ConfigItem("minecraft:barrier", 1, "&c&lNo Permission", Lists.newArrayList()))
                .build();

        private ExtendedConfigItem levelButton = ExtendedConfigItem.builder()
                .type("pixelmon:rare_candy")
                .amount(1)
                .name("&eLevel")
                .positions(Pair.of(4, 1))
                .requiresPermission("none", new ConfigItem("minecraft:barrier", 1, "&c&lNo Permission", Lists.newArrayList()))
                .build();

        private ExtendedConfigItem pokeballButton = ExtendedConfigItem.builder()
                .type("pixelmon:poke_ball")
                .amount(1)
                .name("&ePokeBall")
                .positions(Pair.of(2, 2))
                .requiresPermission("none", new ConfigItem("minecraft:barrier", 1, "&c&lNo Permission", Lists.newArrayList()))
                .build();

        private ExtendedConfigItem shinyButton = ExtendedConfigItem.builder()
                .type("pixelmon:shiny_stone")
                .amount(1)
                .name("&eShiny")
                .positions(Pair.of(2, 1))
                .requiresPermission("none", new ConfigItem("minecraft:barrier", 1, "&c&lNo Permission", Lists.newArrayList()))
                .build();

        private ExtendedConfigItem genderButton = ExtendedConfigItem.builder()
                .type("pixelmon:stone")
                .amount(1)
                .name("&eGender")
                .positions(Pair.of(3, 2))
                .requiresPermission("none", new ConfigItem("minecraft:barrier", 1, "&c&lNo Permission", Lists.newArrayList()))
                .build();

        private SpriteConfig spriteSettings = new SpriteConfig();

        public ConfigInterface getGuiSettings() {
            return this.guiSettings;
        }

        public ExtendedConfigItem getBackButton() {
            return this.backButton;
        }

        public int getSpritePos() {
            return this.spritePos;
        }

        public SpriteConfig getSpriteSettings() {
            return this.spriteSettings;
        }

        public ExtendedConfigItem getEvButton() {
            return this.evButton;
        }

        public ExtendedConfigItem getIvButton() {
            return this.ivButton;
        }

        public ExtendedConfigItem getAbilityButton() {
            return this.abilityButton;
        }

        public ExtendedConfigItem getGrowthButton() {
            return this.growthButton;
        }

        public ExtendedConfigItem getNatureButton() {
            return this.natureButton;
        }

        public ExtendedConfigItem getLevelButton() {
            return this.levelButton;
        }

        public ExtendedConfigItem getPokeballButton() {
            return this.pokeballButton;
        }

        public ExtendedConfigItem getShinyButton() {
            return this.shinyButton;
        }

        public ExtendedConfigItem getGenderButton() {
            return this.genderButton;
        }
    }

    @ConfigSerializable
    public static class ShinyUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private TrueFalseSelectionUI.TrueFalseConfig trueFalseSettings = new TrueFalseSelectionUI.TrueFalseConfig(
                new ExtendedConfigItem("pixelmon:shiny_stone", 1, (byte) 0 , "&6&lSHINY",
                                           Lists.newArrayList(),
                                           2, 1, Maps.newHashMap()),
                new ExtendedConfigItem("pixelmon:dusk_stone", 1, (byte) 0 , "&f&lNON-SHINY",
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
    public static class UntradeableUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private TrueFalseSelectionUI.TrueFalseConfig trueFalseSettings = new TrueFalseSelectionUI.TrueFalseConfig(
                new ExtendedConfigItem("minecraft:barrier", 1, (byte) 0 , "&c&lUNTRADEABLE",
                        Lists.newArrayList(),
                        2, 1, Maps.newHashMap()),
                new ExtendedConfigItem("minecraft:barrier", 1, (byte) 0 , "&a&lTRADEABLE",
                        Lists.newArrayList(),
                        2, 1, Maps.newHashMap())
        );

        private int pokemonPos = 9;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public UntradeableUI() {}

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
    public static class GenderUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private TrueFalseSelectionUI.TrueFalseConfig trueFalseSettings = new TrueFalseSelectionUI.TrueFalseConfig(
                new ExtendedConfigItem("minecraft:stone", 1, (byte) 0 , "&b&lMALE",
                        Lists.newArrayList(),
                        2, 1, Maps.newHashMap()),
                new ExtendedConfigItem("minecraft:stone", 1, (byte) 0 , "&d&lFEMALE",
                        Lists.newArrayList(),
                        2, 1, Maps.newHashMap())
        );

        private int pokemonPos = 9;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public GenderUI() {}

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
                "UPB", 4, 252, 0,  new ExtendedConfigItem(
                "minecraft:chest", 1, (byte) 0, "&bCurrent Value: &a%value%",
                Lists.newArrayList(), 2, 1, Maps.newHashMap()),
                new HashMap<String, NumberModificationUI.EditValueButton>() {
                    {
                        this.put("one", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+1",
                                Lists.newArrayList(), 4, 1, Maps.newHashMap()
                        ), 1));

                        this.put("two", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+5",
                                Lists.newArrayList(), 5, 1, Maps.newHashMap()
                        ), 5));

                        this.put("three", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+10",
                                Lists.newArrayList(), 6, 1, Maps.newHashMap()
                        ), 10));

                        this.put("four", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+15",
                                Lists.newArrayList(), 7, 1, Maps.newHashMap()
                        ), 15));

                        this.put("five", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-1",
                                Lists.newArrayList(), 4, 2, Maps.newHashMap()
                        ), -1));

                        this.put("six", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-5",
                                Lists.newArrayList(), 5, 2, Maps.newHashMap()
                        ), -5));

                        this.put("seven", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-10",
                                Lists.newArrayList(), 6, 2, Maps.newHashMap()
                        ), -10));

                        this.put("eight", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
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
                "UPB", 4, 31, 0,  new ExtendedConfigItem(
                "minecraft:chest", 1, (byte) 0, "&bCurrent Value: &a%value%",
                Lists.newArrayList(), 2, 1, Maps.newHashMap()),
                new HashMap<String, NumberModificationUI.EditValueButton>() {
                    {
                        this.put("one", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+1",
                                Lists.newArrayList(), 4, 1, Maps.newHashMap()
                        ), 1));

                        this.put("two", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+5",
                                Lists.newArrayList(), 5, 1, Maps.newHashMap()
                        ), 5));

                        this.put("three", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+10",
                                Lists.newArrayList(), 6, 1, Maps.newHashMap()
                        ), 10));

                        this.put("four", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+15",
                                Lists.newArrayList(), 7, 1, Maps.newHashMap()
                        ), 15));

                        this.put("five", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-1",
                                Lists.newArrayList(), 4, 2, Maps.newHashMap()
                        ), -1));

                        this.put("six", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-5",
                                Lists.newArrayList(), 5, 2, Maps.newHashMap()
                        ), -5));

                        this.put("seven", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-10",
                                Lists.newArrayList(), 6, 2, Maps.newHashMap()
                        ), -10));

                        this.put("eight", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
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

    @ConfigSerializable
    public static class PokeBallUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private MultiSelectionUI.MultiSelectionConfig ballSelection = new MultiSelectionUI.MultiSelectionConfig(
                "UltimatePokeBuilder", 6,
                new HashMap<String, ConfigItem>() {
                    {
                        this.put("pokeball", new ConfigItem("pixelmon:poke_ball", 1, (byte) 0, "&aPokeball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("greatball", new ConfigItem("pixelmon:great_ball", 1, (byte) 0, "&aGreat Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("ultraball", new ConfigItem("pixelmon:ultra_ball", 1, (byte) 0, "&aUltra Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("masterball", new ConfigItem("pixelmon:master_ball", 1, (byte) 0, "&aMaster Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("levelball", new ConfigItem("pixelmon:level_ball", 1, (byte) 0, "&aLevel Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("moonball", new ConfigItem("pixelmon:moon_ball", 1, (byte) 0, "&aMoon Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("friendball", new ConfigItem("pixelmon:friend_ball", 1, (byte) 0, "&aFriend Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("loveball", new ConfigItem("pixelmon:love_ball", 1, (byte) 0, "&aLove Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("safariball", new ConfigItem("pixelmon:safari_ball", 1, (byte) 0, "&aSafari Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("heavyball", new ConfigItem("pixelmon:heavy_ball", 1, (byte) 0, "&aHeavy Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("fastball", new ConfigItem("pixelmon:fast_ball", 1, (byte) 0, "&aFast Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("repeatball", new ConfigItem("pixelmon:repeat_ball", 1, (byte) 0, "&aRepeat Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("timerball", new ConfigItem("pixelmon:timer_ball", 1, (byte) 0, "&aTimer Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("nestball", new ConfigItem("pixelmon:nest_ball", 1, (byte) 0, "&aNest Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("netball", new ConfigItem("pixelmon:net_ball", 1, (byte) 0, "&aNet Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("diveball", new ConfigItem("pixelmon:dive_ball", 1, (byte) 0, "&aDive Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("luxuryball", new ConfigItem("pixelmon:luxury_ball", 1, (byte) 0, "&aLuxury Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("healball", new ConfigItem("pixelmon:heal_ball", 1, (byte) 0, "&aHeal Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("duskball", new ConfigItem("pixelmon:dusk_ball", 1, (byte) 0, "&aDusk Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("premierball", new ConfigItem("pixelmon:premier_ball", 1, (byte) 0, "&aPremier Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("sportball", new ConfigItem("pixelmon:sport_ball", 1, (byte) 0, "&aSport Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("quickball", new ConfigItem("pixelmon:quick_ball", 1, (byte) 0, "&aQuick Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("lureball", new ConfigItem("pixelmon:lure_ball", 1, (byte) 0, "&aLure Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("parkball", new ConfigItem("pixelmon:park_ball", 1, (byte) 0, "&aPark Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("cherishball", new ConfigItem("pixelmon:cherish_ball", 1, (byte) 0, "&aCherish Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("gsball", new ConfigItem("pixelmon:gs_ball", 1, (byte) 0, "&aGS Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("beastball", new ConfigItem("pixelmon:beast_ball", 1, (byte) 0, "&aBeast Ball", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("dreamball", new ConfigItem("pixelmon:dream_ball", 1, (byte) 0, "&aDream Ball", Lists.newArrayList(), Maps.newHashMap()));
                    }
                }, Lists.newArrayList(12, 13, 14, 15, 16,
                21, 22, 23, 24, 25,
                30, 31, 32, 33, 34)
        );

        private int pokemonPos = 9;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public PokeBallUI() {}

        public int getPokemonPos() {
            return this.pokemonPos;
        }

        public MultiSelectionUI.MultiSelectionConfig getBallSelection() {
            return this.ballSelection;
        }

        public SpriteConfig getSpriteConfig() {
            return this.spriteConfig;
        }

        public ConfirmationUI.ConfirmConfig getConfirmConfig() {
            return this.confirmConfig;
        }

    }

    @ConfigSerializable
    public static class LevelUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private NumberModificationUI.NumberModificationConfig levelEditAmount = new NumberModificationUI.NumberModificationConfig(
                "UPB", 4, 100, 1,  new ExtendedConfigItem(
                "minecraft:chest", 1, (byte) 0, "&bCurrent Level: &a%value%",
                Lists.newArrayList(), 2, 1, Maps.newHashMap()),
                new HashMap<String, NumberModificationUI.EditValueButton>() {
                    {
                        this.put("one", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+1",
                                Lists.newArrayList(), 4, 1, Maps.newHashMap()
                        ), 1));

                        this.put("two", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+5",
                                Lists.newArrayList(), 5, 1, Maps.newHashMap()
                        ), 5));

                        this.put("three", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+10",
                                Lists.newArrayList(), 6, 1, Maps.newHashMap()
                        ), 10));

                        this.put("four", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 5, "&a&l+25",
                                Lists.newArrayList(), 7, 1, Maps.newHashMap()
                        ), 25));

                        this.put("five", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-1",
                                Lists.newArrayList(), 4, 2, Maps.newHashMap()
                        ), -1));

                        this.put("six", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-5",
                                Lists.newArrayList(), 5, 2, Maps.newHashMap()
                        ), -5));

                        this.put("seven", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-10",
                                Lists.newArrayList(), 6, 2, Maps.newHashMap()
                        ), -10));

                        this.put("eight", new NumberModificationUI.EditValueButton(new ExtendedConfigItem(
                                "minecraft:stained_glass_pane", 1, (byte) 14, "&c&l-25",
                                Lists.newArrayList(), 7, 2, Maps.newHashMap()
                        ), -25));
                    }
                }
        );

        private int pokemonPos = 9;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public LevelUI() {}

        public int getPokemonPos() {
            return this.pokemonPos;
        }

        public SpriteConfig getSpriteConfig() {
            return this.spriteConfig;
        }

        public ConfirmationUI.ConfirmConfig getConfirmConfig() {
            return this.confirmConfig;
        }

        public NumberModificationUI.NumberModificationConfig getLevelEditAmount() {
            return this.levelEditAmount;
        }

    }

    @ConfigSerializable
    public static class GrowthUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private MultiSelectionUI.MultiSelectionConfig growthSelection = new MultiSelectionUI.MultiSelectionConfig(
                "UltimatePokeBuilder", 6,
                new HashMap<String, ConfigItem>() {
                    {
                        this.put("microscopic", new ConfigItem("pixelmon:normal_gem", 1, (byte) 0, "&dMicroscopic", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("pygmy", new ConfigItem("pixelmon:psychic_gem", 1, (byte) 0, "&dPygmy", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("runt", new ConfigItem("pixelmon:fairy_gem", 1, (byte) 0, "&dRunt", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("small", new ConfigItem("pixelmon:poison_gem", 1, (byte) 0, "&dSmall", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("ordinary", new ConfigItem("pixelmon:ghost_gem", 1, (byte) 0, "&dOrdinary", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("huge", new ConfigItem("pixelmon:ice_gem", 1, (byte) 0, "&dHuge", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("giant", new ConfigItem("pixelmon:ice_gem", 1, (byte) 0, "&dGiant", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("enormous", new ConfigItem("pixelmon:fighting_gem", 1, (byte) 0, "&dEnormous", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("ginormous", new ConfigItem("pixelmon:dark_gem", 1, (byte) 0, "&dGinormous", Lists.newArrayList(), Maps.newHashMap()));
                    }
                }, Lists.newArrayList(12, 13, 14, 15, 16,
                                      21, 22, 23, 24, 25,
                                      30, 31, 32, 33, 34)
        );

        private int pokemonPos = 9;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public GrowthUI() {}

        public int getPokemonPos() {
            return this.pokemonPos;
        }

        public MultiSelectionUI.MultiSelectionConfig getGrowthSelection() {
            return this.growthSelection;
        }

        public SpriteConfig getSpriteConfig() {
            return this.spriteConfig;
        }

        public ConfirmationUI.ConfirmConfig getConfirmConfig() {
            return this.confirmConfig;
        }

    }

    @ConfigSerializable
    public static class NatureUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private MultiSelectionUI.MultiSelectionConfig natureSelection = new MultiSelectionUI.MultiSelectionConfig(
                "UltimatePokeBuilder", 6,
                new HashMap<String, ConfigItem>() {
                    {
                        this.put("hardy", new ConfigItem("pixelmon:assault_vest", 1, (byte) 0, "&b&lHardy", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("serious", new ConfigItem("pixelmon:focus_band", 1, (byte) 0, "&b&lSerious", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("docile", new ConfigItem("pixelmon:iron_ball", 1, (byte) 0, "&b&lDocile", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("bashful", new ConfigItem("pixelmon:smoke_ball", 1, (byte) 0, "&b&lBashful", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("quirky", new ConfigItem("pixelmon:zoom_lens", 1, (byte) 0, "&b&lQuirky", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("lonely", new ConfigItem("pixelmon:reaper_cloth", 1, (byte) 0, "&b&lLonely", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("brave", new ConfigItem("pixelmon:focus_sash", 1, (byte) 0, "&b&lBrave", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("adamant", new ConfigItem("pixelmon:metal_coat", 1, (byte) 0, "&b&lAdamant", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("naughty", new ConfigItem("pixelmon:lucky_punch", 1, (byte) 0, "&b&lNaughty", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("bold", new ConfigItem("pixelmon:life_orb", 1, (byte) 0, "&b&lBold", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("relaxed", new ConfigItem("pixelmon:soothe_bell", 1, (byte) 0, "&b&lRelaxed", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("impish", new ConfigItem("pixelmon:thick_club", 1, (byte) 0, "&b&lImpish", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("lax", new ConfigItem("pixelmon:shell_bell", 1, (byte) 0, "&b&lLax", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("timid", new ConfigItem("pixelmon:eject_button", 1, (byte) 0, "&b&lTimid", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("hasty", new ConfigItem("pixelmon:white_herb", 1, (byte) 0, "&b&lHasty", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("jolly", new ConfigItem("pixelmon:air_balloon", 1, (byte) 0, "&b&lJolly", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("naive", new ConfigItem("pixelmon:weakness_policy", 1, (byte) 0, "&b&lNaive", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("modest", new ConfigItem("pixelmon:hard_stone", 1, (byte) 0, "&b&lModest", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("mild", new ConfigItem("pixelmon:oval_stone", 1, (byte) 0, "&b&lMild", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("quiet", new ConfigItem("pixelmon:destiny_knot", 1, (byte) 0, "&b&lQuiet", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("rash", new ConfigItem("pixelmon:red_card", 1, (byte) 0, "&b&lRash", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("calm", new ConfigItem("pixelmon:mental_herb", 1, (byte) 0, "&b&lCalm", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("gentle", new ConfigItem("pixelmon:soft_sand", 1, (byte) 0, "&b&lGentle", Lists.newArrayList(), Maps.newHashMap()));
                        this.put("sassy", new ConfigItem("pixelmon:quick_claw", 1, (byte) 0, "&b&lSassy", Lists.newArrayList(), Maps.newHashMap()));
                    }
                }, Lists.newArrayList(12, 13, 14, 15, 16,
                                      21, 22, 23, 24, 25,
                                      30, 31, 32, 33, 34)
        );

        private int pokemonPos = 9;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public NatureUI() {}

        public int getPokemonPos() {
            return this.pokemonPos;
        }

        public MultiSelectionUI.MultiSelectionConfig getNatureSelection() {
            return this.natureSelection;
        }

        public SpriteConfig getSpriteConfig() {
            return this.spriteConfig;
        }

        public ConfirmationUI.ConfirmConfig getConfirmConfig() {
            return this.confirmConfig;
        }

    }
}
