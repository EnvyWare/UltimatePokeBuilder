package com.envyful.ultimate.poke.builder.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.type.ConfigInterface;
import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.config.type.ExtendedConfigItem;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import com.envyful.api.forge.gui.type.*;
import com.envyful.api.reforged.pixelmon.config.SpriteConfig;
import com.envyful.api.type.Pair;
import com.google.common.collect.Lists;
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
    private UnbreedableUI unbreedableUI = new UnbreedableUI();

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

    public UnbreedableUI getUnbreedableUI() {
        return this.unbreedableUI;
    }

    @ConfigSerializable
    public static class SelectUI {

        private ConfigInterface guiSettings = ConfigInterface.builder()
                .title("UltimatePokeBuilder")
                .height(3)
                .fillType(ConfigInterface.FillType.BLOCK)
                .fillerItem(ConfigItem.builder()
                        .type("minecraft:black_stained_glass_pane")
                        .amount(1)
                        .name(" ")
                        .build())
                .build();

        private ExtendedConfigItem infoItem = ExtendedConfigItem.builder()
                .type("minecraft:nether_star")
                .amount(1)
                .name("&bClick the pokemon to edit")
                .positions(Pair.of(4, 0))
                .build();

        private ConfigItem eggItem = ConfigItem.builder()
                .type("minecraft:barrier")
                .amount(1)
                .name("&cCannot edit eggs").build();

        private List<Integer> partyPositions = Lists.newArrayList(
                10, 11, 12, 14, 15, 16
        );

        private SpriteConfig spriteSettings = new SpriteConfig();

        private ConfigItem blacklistedItem = ConfigItem.builder()
                .type("minecraft:barrier")
                .amount(1)
                .name("&c&lBlocked").build();

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

        private ConfigInterface guiSettings = ConfigInterface.builder()
                .title("UltimatePokeBuilder")
                .height(4)
                .fillType(ConfigInterface.FillType.BLOCK)
                .fillerItem(ConfigItem.builder()
                        .type("minecraft:black_stained_glass_pane")
                        .amount(1)
                        .name(" ")
                        .build())
                .build();

        private ExtendedConfigItem backButton = ExtendedConfigItem.builder()
                .type("pixelmon:eject_button")
                .amount(1)
                .name("&cBack")
                .positions(Pair.of(0, 0))
                .build();

        private int spritePos = 9;

        private ExtendedConfigItem evButton = ExtendedConfigItem.builder()
                .type("pixelmon:hp_up")
                .amount(1)
                .name("&eEVs")
                .positions(Pair.of(7, 2))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
                .build();

        private ExtendedConfigItem ivButton = ExtendedConfigItem.builder()
                .type("pixelmon:protein")
                .amount(1)
                .name("&eIVs")
                .positions(Pair.of(7, 1))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
                .build();

        private ExtendedConfigItem abilityButton = ExtendedConfigItem.builder()
                .type("pixelmon:power_band")
                .amount(1)
                .name("&eAbility")
                .positions(Pair.of(5, 2))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
                .build();

        private ExtendedConfigItem growthButton = ExtendedConfigItem.builder()
                .type("pixelmon:choice_scarf")
                .amount(1)
                .name("&eGrowth")
                .positions(Pair.of(5, 1))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
                .build();

        private ExtendedConfigItem natureButton = ExtendedConfigItem.builder()
                .type("pixelmon:ground_gem")
                .amount(1)
                .name("&eNature")
                .positions(Pair.of(4, 2))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
                .build();

        private ExtendedConfigItem untradeableButton = ExtendedConfigItem
                .builder()
                .type("minecraft:barrier")
                .amount(1)
                .name("&eUntradeable")
                .positions(Pair.of(3, 1))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
                .build();
        private ExtendedConfigItem levelButton = ExtendedConfigItem.builder()
                .type("pixelmon:rare_candy")
                .amount(1)
                .name("&eLevel")
                .positions(Pair.of(4, 1))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
                .build();

        private ExtendedConfigItem pokeballButton = ExtendedConfigItem.builder()
                .type("pixelmon:poke_ball")
                .amount(1)
                .name("&ePokeBall")
                .positions(Pair.of(2, 2))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
                .build();

        private ExtendedConfigItem shinyButton = ExtendedConfigItem.builder()
                .type("pixelmon:shiny_stone")
                .amount(1)
                .name("&eShiny")
                .positions(Pair.of(2, 1))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
                .build();

        private ExtendedConfigItem genderButton = ExtendedConfigItem.builder()
                .type("minecraft:stone")
                .amount(1)
                .name("&eGender")
                .positions(Pair.of(3, 2))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
                .build();

        private ExtendedConfigItem unbreedableButton = ExtendedConfigItem
                .builder()
                .type("minecraft:barrier")
                .amount(1)
                .name("&eUnbreedable")
                .positions(Pair.of(3, 2))
                .requiresPermission("none", ConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lNo Permission").build())
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

        public ExtendedConfigItem getUntradeableButton() {
            return this.untradeableButton;
        }

        public ExtendedConfigItem getUnbreedableButton() {
            return this.unbreedableButton;
        }
    }

    @ConfigSerializable
    public static class ShinyUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private TrueFalseSelectionUI.TrueFalseConfig trueFalseSettings = new TrueFalseSelectionUI.TrueFalseConfig(
                ExtendedConfigItem.builder()
                        .type("pixelmon:shiny_stone")
                        .amount(1)
                        .name("&6&lSHINY")
                        .positions(2, 1)
                        .build(),

                ExtendedConfigItem.builder()
                        .type("pixelmon:shiny_stone")
                        .amount(1)
                        .name("&f&lNON-SHINY")
                        .positions(2, 1)
                        .build()
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
                ExtendedConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lUNTRADEABLE")
                        .positions(2, 1)
                        .build(),
                ExtendedConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&a&lTRADEABLE")
                        .positions(2, 1)
                        .build()
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
    public static class UnbreedableUI {

        private ConfirmationUI.ConfirmConfig confirmConfig = new ConfirmationUI.ConfirmConfig();

        private TrueFalseSelectionUI.TrueFalseConfig trueFalseSettings = new TrueFalseSelectionUI.TrueFalseConfig(
                ExtendedConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&c&lUNBREEDABLE")
                        .positions(2, 1)
                        .build(),
                ExtendedConfigItem.builder()
                        .type("minecraft:barrier")
                        .amount(1)
                        .name("&a&lBREEDABLE")
                        .positions(2, 1)
                        .build()
        );

        private int pokemonPos = 9;

        private SpriteConfig spriteConfig = new SpriteConfig();

        public UnbreedableUI() {
        }

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
                ExtendedConfigItem.builder()
                        .type("minecraft:stone")
                        .amount(1)
                        .name("&b&lMALE")
                        .positions(2, 1)
                        .build(),
                ExtendedConfigItem.builder()
                        .type("minecraft:stone")
                        .amount(1)
                        .name("&d&lFEMALE")
                        .positions(2, 1)
                        .build()
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
                ConfigItem.builder().type("pixelmon:ability_capsule")
                        .amount(1).name(" ")
                        .lore(
                                "&b&lAbility Cost: &a200 Tokens",
                                "&b&lHidden Ability Cost: &a400 Tokens"
                        )
                        .build()
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
                        this.put("hp", ConfigItem.builder().type("pixelmon:power_weight").name("&a&lHP").amount(1).build());
                        this.put("attack", ConfigItem.builder().type("pixelmon:power_bracer").name("&c&lAttack").amount(1).build());
                        this.put("defense", ConfigItem.builder().type("pixelmon:power_belt").name("&6&lDefence").amount(1).build());
                        this.put("special_attack", ConfigItem.builder().type("pixelmon:power_lens").name("&d&lSp. Attack").amount(1).build());
                        this.put("special_defense", ConfigItem.builder().type("pixelmon:power_band").name("&e&lSp. Defence").amount(1).build());
                        this.put("speed", ConfigItem.builder().type("pixelmon:power_anklet").name("&b&lSpeed").amount(1).build());
                    }
                }, Lists.newArrayList(12, 13, 14, 21, 22, 23)
        );

        private NumberModificationUI.NumberModificationConfig evEditAmount = new NumberModificationUI.NumberModificationConfig(
                "UPB", 4, 252, 0,
                ExtendedConfigItem.builder()
                        .type("minecraft:chest")
                        .amount(1)
                        .name("&bCurrent Value: &a%value%")
                        .positions(2, 1)
                        .build(),
                new HashMap<>() {
                    {
                        this.put("one", new NumberModificationUI.EditValueButton(ExtendedConfigItem.builder().type("minecraft:lime_stained_glass_pane").name("&a&l+1").amount(1).positions(Pair.of(4, 1)).build(), 1));

                        this.put("two", new NumberModificationUI.EditValueButton(ExtendedConfigItem.builder().type("minecraft:lime_stained_glass_pane").name("&a&l+5").amount(1).positions(Pair.of(5, 1)).build(), 5));

                        this.put("three", new NumberModificationUI.EditValueButton(ExtendedConfigItem.builder().type("minecraft:lime_stained_glass_pane").name("&a&l+10").amount(1).positions(Pair.of(6, 1)).build(), 10));

                        this.put("four", new NumberModificationUI.EditValueButton(ExtendedConfigItem.builder().type("minecraft:lime_stained_glass_pane").name("&a&l+15").amount(1).positions(Pair.of(7, 1)).build(), 15));

                        this.put("five", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-1")
                                        .amount(1)
                                        .positions(4, 2)
                                        .build(), -1));

                        this.put("six", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-5")
                                        .amount(1)
                                        .positions(5, 2)
                                        .build(), -5));

                        this.put("seven", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-10")
                                        .amount(1)
                                        .positions(6, 2)
                                        .build(), -10));

                        this.put("eight", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-15")
                                        .amount(1)
                                        .positions(7, 2)
                                        .build(), -15));
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
                        this.put("hp", ConfigItem.builder().type("pixelmon:power_weight").name("&a&lHP").amount(1).build());
                        this.put("attack", ConfigItem.builder().type("pixelmon:power_bracer").name("&c&lAttack").amount(1).build());
                        this.put("defense", ConfigItem.builder().type("pixelmon:power_belt").name("&6&lDefence").amount(1).build());
                        this.put("special_attack", ConfigItem.builder().type("pixelmon:power_lens").name("&d&lSp. Attack").amount(1).build());
                        this.put("special_defense", ConfigItem.builder().type("pixelmon:power_band").name("&e&lSp. Defence").amount(1).build());
                        this.put("speed", ConfigItem.builder().type("pixelmon:power_anklet").name("&b&lSpeed").amount(1).build());
                    }
                }, Lists.newArrayList(12, 13, 14, 21, 22, 23)
        );

        private NumberModificationUI.NumberModificationConfig ivEditAmount = new NumberModificationUI.NumberModificationConfig(
                "UPB", 4, 31, 0,
                ExtendedConfigItem.builder()
                        .type("minecraft:chest")
                        .amount(1)
                        .name("&bCurrent Value: &a%value%")
                        .positions(2, 1)
                        .build(),
                new HashMap<>() {
                    {
                        this.put("one", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:lime_stained_glass_pane")
                                        .name("&a&l+1")
                                        .amount(1)
                                        .positions(4, 1)
                                        .build(), 1));

                        this.put("two", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:lime_stained_glass_pane")
                                        .name("&a&l+5")
                                        .amount(1)
                                        .positions(5, 1)
                                        .build(), 5));

                        this.put("three", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:lime_stained_glass_pane")
                                        .name("&a&l+10")
                                        .amount(1)
                                        .positions(6, 1)
                                        .build(), 10));

                        this.put("four", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:lime_stained_glass_pane")
                                        .name("&a&l+15")
                                        .amount(1)
                                        .positions(7, 1)
                                        .build(), 15));

                        this.put("five", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-1")
                                        .amount(1)
                                        .positions(4, 2)
                                        .build(), -1));

                        this.put("six", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-5")
                                        .amount(1)
                                        .positions(5, 2)
                                        .build(), -5));
                        this.put("seven", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-10")
                                        .amount(1)
                                        .positions(6, 2)
                                        .build(), -10));

                        this.put("eight", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-15")
                                        .amount(1)
                                        .positions(7, 2)
                                        .build(), -15));
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
                        this.put("poke_ball", ConfigItem.builder().type("pixelmon:poke_ball").name("&aPokeball").amount(1).build());
                        this.put("great_ball", ConfigItem.builder().type("pixelmon:great_ball").name("&aGreat Ball").amount(1).build());
                        this.put("ultra_ball", ConfigItem.builder().type("pixelmon:ultra_ball").name("&aUltra Ball").amount(1).build());
                        this.put("master_ball", ConfigItem.builder().type("pixelmon:master_ball").name("&aMaster Ball").amount(1).build());
                        this.put("level_ball", ConfigItem.builder().type("pixelmon:level_ball").name("&aLevel Ball").amount(1).build());
                        this.put("moon_ball", ConfigItem.builder().type("pixelmon:moon_ball").name("&aMoon Ball").amount(1).build());
                        this.put("friend_ball", ConfigItem.builder().type("pixelmon:friend_ball").name("&aFriend Ball").amount(1).build());
                        this.put("love_ball", ConfigItem.builder().type("pixelmon:love_ball").name("&aLove Ball").amount(1).build());
                        this.put("safari_ball", ConfigItem.builder().type("pixelmon:safari_ball").name("&aSafari Ball").amount(1).build());
                        this.put("heavy_ball", ConfigItem.builder().type("pixelmon:heavy_ball").name("&aHeavy Ball").amount(1).build());
                        this.put("fast_ball", ConfigItem.builder().type("pixelmon:fast_ball").name("&aFast Ball").amount(1).build());
                        this.put("repeat_ball", ConfigItem.builder().type("pixelmon:repeat_ball").name("&aRepeat Ball").amount(1).build());
                        this.put("timer_ball", ConfigItem.builder().type("pixelmon:timer_ball").name("&aTimer Ball").amount(1).build());
                        this.put("nest_ball", ConfigItem.builder().type("pixelmon:nest_ball").name("&aNest Ball").amount(1).build());
                        this.put("net_ball", ConfigItem.builder().type("pixelmon:net_ball").name("&aNet Ball").amount(1).build());
                        this.put("dive_ball", ConfigItem.builder().type("pixelmon:dive_ball").name("&aDive Ball").amount(1).build());
                        this.put("luxury_ball", ConfigItem.builder().type("pixelmon:luxury_ball").name("&aLuxury Ball").amount(1).build());
                        this.put("heal_ball", ConfigItem.builder().type("pixelmon:heal_ball").name("&aHeal Ball").amount(1).build());
                        this.put("dusk_ball", ConfigItem.builder().type("pixelmon:dusk_ball").name("&aDusk Ball").amount(1).build());
                        this.put("premier_ball", ConfigItem.builder().type("pixelmon:premier_ball").name("&aPremier Ball").amount(1).build());
                        this.put("sport_ball", ConfigItem.builder().type("pixelmon:sport_ball").name("&aSport Ball").amount(1).build());
                        this.put("quick_ball", ConfigItem.builder().type("pixelmon:quick_ball").name("&aQuick Ball").amount(1).build());
                        this.put("lure_ball", ConfigItem.builder().type("pixelmon:lure_ball").name("&aLure Ball").amount(1).build());
                        this.put("park_ball", ConfigItem.builder().type("pixelmon:park_ball").name("&aPark Ball").amount(1).build());
                        this.put("cherish_ball", ConfigItem.builder().type("pixelmon:cherish_ball").name("&aCherish Ball").amount(1).build());
                        this.put("gs_ball", ConfigItem.builder().type("pixelmon:gs_ball").name("&aGS Ball").amount(1).build());
                        this.put("beast_ball", ConfigItem.builder().type("pixelmon:beast_ball").name("&aBeast Ball").amount(1).build());
                        this.put("dream_ball", ConfigItem.builder().type("pixelmon:dream_ball").name("&aDream Ball").amount(1).build());
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
                "UPB", 4, 100, 1,
                ExtendedConfigItem.builder()
                        .type("minecraft:chest")
                        .amount(1)
                        .name("&bCurrent Level: &a%value%")
                        .positions(2, 1)
                        .build(),
                new HashMap<>() {
                    {
                        this.put("one", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:lime_stained_glass_pane")
                                        .name("&a&l+1")
                                        .amount(1)
                                        .positions(4, 1)
                                        .build(), 1));

                        this.put("two", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:lime_stained_glass_pane")
                                        .name("&a&l+5")
                                        .amount(1)
                                        .positions(5, 1)
                                        .build(), 5));

                        this.put("three", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:lime_stained_glass_pane")
                                        .name("&a&l+10")
                                        .amount(1)
                                        .positions(6, 1)
                                        .build(), 10));

                        this.put("four", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:lime_stained_glass_pane")
                                        .name("&a&l+25")
                                        .amount(1)
                                        .positions(7, 1)
                                        .build(), 25));

                        this.put("five", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-1")
                                        .amount(1)
                                        .positions(4, 2)
                                        .build(), -1));

                        this.put("six", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-5")
                                        .amount(1)
                                        .positions(5, 2)
                                        .build(), -5));

                        this.put("seven", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-10")
                                        .amount(1)
                                        .positions(6, 2)
                                        .build(), -10));
                        this.put("eight", new NumberModificationUI.EditValueButton(
                                ExtendedConfigItem.builder()
                                        .type("minecraft:red_stained_glass_pane")
                                        .name("&c&l-25")
                                        .amount(1)
                                        .positions(7, 2)
                                        .build(), -25));
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
                        this.put("microscopic", ConfigItem.builder().type("pixelmon:normal_gem").name("&dMicroscopic").amount(1).build());
                        this.put("pygmy", ConfigItem.builder().type("pixelmon:psychic_gem").name("&dPygmy").amount(1).build());
                        this.put("runt", ConfigItem.builder().type("pixelmon:fairy_gem").name("&dRunt").amount(1).build());
                        this.put("small", ConfigItem.builder().type("pixelmon:poison_gem").name("&dSmall").amount(1).build());
                        this.put("ordinary", ConfigItem.builder().type("pixelmon:ghost_gem").name("&dOrdinary").amount(1).build());
                        this.put("huge", ConfigItem.builder().type("pixelmon:ice_gem").name("&dHuge").amount(1).build());
                        this.put("giant", ConfigItem.builder().type("pixelmon:ice_gem").name("&dGiant").amount(1).build());
                        this.put("enormous", ConfigItem.builder().type("pixelmon:fighting_gem").name("&dEnormous").amount(1).build());
                        this.put("ginormous", ConfigItem.builder().type("pixelmon:dark_gem").name("&dGinormous").amount(1).build());
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
                        this.put("hardy", ConfigItem.builder().type("pixelmon:assault_vest").name("&b&lHardy").amount(1).build());
                        this.put("serious", ConfigItem.builder().type("pixelmon:focus_band").name("&b&lSerious").amount(1).build());
                        this.put("docile", ConfigItem.builder().type("pixelmon:iron_ball").name("&b&lDocile").amount(1).build());
                        this.put("bashful", ConfigItem.builder().type("pixelmon:smoke_ball").name("&b&lBashful").amount(1).build());
                        this.put("quirky", ConfigItem.builder().type("pixelmon:zoom_lens").name("&b&lQuirky").amount(1).build());
                        this.put("lonely", ConfigItem.builder().type("pixelmon:reaper_cloth").name("&b&lLonely").amount(1).build());
                        this.put("brave", ConfigItem.builder().type("pixelmon:focus_sash").name("&b&lBrave").amount(1).build());
                        this.put("adamant", ConfigItem.builder().type("pixelmon:metal_coat").name("&b&lAdamant").amount(1).build());
                        this.put("naughty", ConfigItem.builder().type("pixelmon:lucky_punch").name("&b&lNaughty").amount(1).build());
                        this.put("bold", ConfigItem.builder().type("pixelmon:life_orb").name("&b&lBold").amount(1).build());
                        this.put("relaxed", ConfigItem.builder().type("pixelmon:soothe_bell").name("&b&lRelaxed").amount(1).build());
                        this.put("impish", ConfigItem.builder().type("pixelmon:thick_club").name("&b&lImpish").amount(1).build());
                        this.put("lax", ConfigItem.builder().type("pixelmon:shell_bell").name("&b&lLax").amount(1).build());
                        this.put("timid", ConfigItem.builder().type("pixelmon:eject_button").name("&b&lTimid").amount(1).build());
                        this.put("hasty", ConfigItem.builder().type("pixelmon:white_herb").name("&b&lHasty").amount(1).build());
                        this.put("jolly", ConfigItem.builder().type("pixelmon:air_balloon").name("&b&lJolly").amount(1).build());
                        this.put("naive", ConfigItem.builder().type("pixelmon:weakness_policy").name("&b&lNaive").amount(1).build());
                        this.put("modest", ConfigItem.builder().type("pixelmon:hard_stone").name("&b&lModest").amount(1).build());
                        this.put("mild", ConfigItem.builder().type("pixelmon:oval_stone").name("&b&lMild").amount(1).build());
                        this.put("quiet", ConfigItem.builder().type("pixelmon:destiny_knot").name("&b&lQuiet").amount(1).build());
                        this.put("rash", ConfigItem.builder().type("pixelmon:red_card").name("&b&lRash").amount(1).build());
                        this.put("calm", ConfigItem.builder().type("pixelmon:mental_herb").name("&b&lCalm").amount(1).build());
                        this.put("gentle", ConfigItem.builder().type("pixelmon:soft_sand").name("&b&lGentle").amount(1).build());
                        this.put("sassy", ConfigItem.builder().type("pixelmon:quick_claw").name("&b&lSassy").amount(1).build());
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
