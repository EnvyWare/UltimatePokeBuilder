package com.envyful.ultimate.poke.builder.forge.ui;

import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.config.UtilConfigItem;
import com.envyful.api.forge.gui.item.PositionableItem;
import com.envyful.api.forge.gui.type.*;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.gui.pane.Pane;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.api.reforged.pixelmon.sprite.UtilSprite;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.config.GuiConfig;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderConfig;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoFactory;
import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import com.pixelmonmod.pixelmon.enums.items.EnumPokeballs;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.List;

public class EditPokemonUI {

    public static void open(EnvyPlayer<EntityPlayerMP> player, Pokemon pokemon) {
        GuiConfig.EditPokemonUI config = UltimatePokeBuilderForge.getInstance().getGuiConfig().getEditPokemonUI();

        Pane pane = GuiFactory.paneBuilder()
                .topLeftX(0)
                .topLeftY(0)
                .width(9)
                .height(config.getGuiSettings().getHeight())
                .build();

        for (ConfigItem fillerItem : config.getGuiSettings().getFillerItems()) {
            pane.add(GuiFactory.displayable(UtilConfigItem.fromConfigItem(fillerItem)));
        }

        UtilConfigItem.addConfigItem(pane, config.getBackButton(),
                                     (envyPlayer, clickType) -> SelectPokemonUI.open(player));

        pane.set(config.getSpritePos() % 9, config.getSpritePos() / 9,
                 GuiFactory.displayable(UtilSprite.getPokemonElement(pokemon, config.getSpriteSettings())));

        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getShinyButton(),
                                                (envyPlayer, clickType) -> {
                                                    GuiConfig.ShinyUI shinyUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getShinyUI();

                                                    TrueFalseSelectionUI.builder()
                                                            .player(envyPlayer)
                                                            .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                                                            .config(shinyUI.getTrueFalseSettings())
                                                            .confirm(ConfirmationUI.builder().config(shinyUI.getConfirmConfig()))
                                                            .startsTrue(!pokemon.isShiny())
                                                            .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                                                            .trueAcceptHandler((envyPlayer1, clickType1) -> handleShinyConfirmation(player, pokemon, true))
                                                            .falseAcceptHandler((envyPlayer1, clickType1) -> handleShinyConfirmation(player, pokemon, false))
                                                            .displayItem(new PositionableItem(
                                                                    UtilSprite.getPokemonElement(pokemon, shinyUI.getSpriteConfig()),
                                                                    shinyUI.getPokemonPos()
                                                            ))
                                                            .open();
                                                });

        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getAbilityButton(),
                                                (envyPlayer, clickType) -> {
                                                    GuiConfig.AbilitiesUI abilitiesUI =
                                                            UltimatePokeBuilderForge.getInstance().getGuiConfig().getAbilitiesUI();

                                                    List<String> abilitiesStripped = Lists.newArrayList(pokemon.getSpecies().getBaseStats().getAbilitiesArray());

                                                    if (abilitiesStripped.size() == 3 && abilitiesStripped.get(2) != null) {
                                                        abilitiesStripped.set(2, abilitiesStripped.get(2) + " (HA)");
                                                    }

                                                    abilitiesStripped.removeIf(s -> s == null || s.equalsIgnoreCase("null"));

                                                    DynamicSelectionUI.builder()
                                                            .player(player)
                                                            .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                                                            .config(abilitiesUI.getAbilitySelection())
                                                            .confirm(ConfirmationUI.builder().config(abilitiesUI.getConfirmConfig()))
                                                            .displayNames(abilitiesStripped)
                                                            .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                                                            .acceptHandler((envyPlayer1, clickType1, ability) -> {
                                                                int index = abilitiesStripped.indexOf(ability);

                                                                handleAbilityConfirmation(player, pokemon,
                                                                                          pokemon.getBaseStats().getAllAbilities().get(index),
                                                                                          ability.endsWith(" (HA")
                                                                );
                                                            })
                                                            .displayItem(new PositionableItem(
                                                                    UtilSprite.getPokemonElement(pokemon, abilitiesUI.getSpriteConfig()),
                                                                    abilitiesUI.getPokemonPos()
                                                            ))
                                                            .open();
                                                });
        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getEvButton(),
                                                (envyPlayer, clickType) -> {
                                                    GuiConfig.EvUI evUI =
                                                            UltimatePokeBuilderForge.getInstance().getGuiConfig().getEvUI();

                                                    MultiSelectionUI.builder()
                                                            .player(player)
                                                            .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                                                            .config(evUI.getEvSelection())
                                                            .page(0)
                                                            .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                                                            .selectHandler((envyPlayer1, clickType1, s) -> openEVSelect(s,
                                                                                                             player, pokemon))
                                                            .displayItem(new PositionableItem(
                                                                    UtilSprite.getPokemonElement(pokemon, evUI.getSpriteConfig()),
                                                                    evUI.getPokemonPos()
                                                            ))
                                                            .open();
                                                });
        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getIvButton(),
                                                (envyPlayer, clickType) -> {
                                                    GuiConfig.IvUI ivUI =
                                                            UltimatePokeBuilderForge.getInstance().getGuiConfig().getIvUI();

                                                    MultiSelectionUI.builder()
                                                            .player(player)
                                                            .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                                                            .config(ivUI.getIvSelection())
                                                            .page(0)
                                                            .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                                                            .selectHandler((envyPlayer1, clickType1, s) -> openIVSelect(s, player, pokemon))
                                                            .displayItem(new PositionableItem(
                                                                    UtilSprite.getPokemonElement(pokemon, ivUI.getSpriteConfig()),
                                                                    ivUI.getPokemonPos()
                                                            ))
                                                            .open();
                                                });
        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getPokeballButton(),
                                                (envyPlayer, clickType) -> {
                                                    GuiConfig.PokeBallUI ballUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getBallUI();

                                                    MultiSelectionUI.builder()
                                                            .player(player)
                                                            .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                                                            .config(ballUI.getBallSelection())
                                                            .page(0)
                                                            .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                                                            .confirm(ConfirmationUI.builder().config(ballUI.getConfirmConfig()))
                                                            .acceptHandler((envyPlayer1, clickType1, s) -> handleBallConfirmation(player, pokemon, s))
                                                            .displayItem(new PositionableItem(
                                                                    UtilSprite.getPokemonElement(pokemon, ballUI.getSpriteConfig()),
                                                                    ballUI.getPokemonPos()
                                                            ))
                                                            .open();
                                                });

        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getNatureButton(),
                                                (envyPlayer, clickType) -> {
                                                    GuiConfig.NatureUI natureUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getNatureUI();

                                                    MultiSelectionUI.builder()
                                                            .player(player)
                                                            .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                                                            .config(natureUI.getNatureSelection())
                                                            .page(0)
                                                            .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                                                            .confirm(ConfirmationUI.builder().config(natureUI.getConfirmConfig()))
                                                            .acceptHandler((envyPlayer1, clickType1, s) -> handleNatureConfirmation(player, pokemon, s))
                                                            .displayItem(new PositionableItem(
                                                                    UtilSprite.getPokemonElement(pokemon, natureUI.getSpriteConfig()),
                                                                    natureUI.getPokemonPos()
                                                            ))
                                                            .open();
                                                });
        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getGrowthButton(),
                                                (envyPlayer, clickType) -> {
                                                    GuiConfig.GrowthUI growthUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getGrowthUI();

                                                    MultiSelectionUI.builder()
                                                            .player(player)
                                                            .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                                                            .config(growthUI.getGrowthSelection())
                                                            .page(0)
                                                            .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                                                            .confirm(ConfirmationUI.builder().config(growthUI.getConfirmConfig()))
                                                            .acceptHandler((envyPlayer1, clickType1, s) -> handleGrowthConfirmation(player, pokemon, s))
                                                            .displayItem(new PositionableItem(
                                                                    UtilSprite.getPokemonElement(pokemon, growthUI.getSpriteConfig()),
                                                                    growthUI.getPokemonPos()
                                                            ))
                                                            .open();
                                                });

        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getLevelButton(),
                                                (envyPlayer, clickType) -> {
                                                    GuiConfig.LevelUI levelUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getLevelUI();

                                                    NumberModificationUI.builder()
                                                            .player(player)
                                                            .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                                                            .config(levelUI.getLevelEditAmount())
                                                            .currentValue(pokemon.getLevel())
                                                            .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                                                            .confirm(ConfirmationUI.builder().config(levelUI.getConfirmConfig()))
                                                            .acceptHandler((envyPlayer1, clickType1, level) -> handleLevelConfirmation(player, pokemon, level))
                                                            .displayItem(new PositionableItem(
                                                                    UtilSprite.getPokemonElement(pokemon, levelUI.getSpriteConfig()),
                                                                    levelUI.getPokemonPos()
                                                            ))
                                                            .open();
                                                });

        GuiFactory.guiBuilder()
                .setPlayerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                .addPane(pane)
                .setCloseConsumer(envyPlayer -> {})
                .height(config.getGuiSettings().getHeight())
                .title(UtilChatColour.translateColourCodes('&', config.getGuiSettings().getTitle()))
                .build().open(player);
    }

    private static void handleShinyConfirmation(EnvyPlayer<EntityPlayerMP> player, Pokemon pokemon, boolean shiny) {
        if (pokemon.isShiny() == shiny) {
            open(player, pokemon);
            return;
        }

        int shinyCost = UltimatePokeBuilderForge.getInstance().getConfig().getShinyCost();

        if (!EcoFactory.hasBalance(player, shinyCost)) {
            open(player, pokemon);
            player.message(UtilChatColour.translateColourCodes('&',
                                                               UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, shinyCost);
        pokemon.setShiny(shiny);

        if (shiny) {
            player.message(UtilChatColour.translateColourCodes(
                    '&',
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonNowShiny()
                            .replace("%cost%", shinyCost + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
            ));
        } else {
            player.message(UtilChatColour.translateColourCodes(
                    '&',
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonNowShiny()
                            .replace("%cost%", shinyCost + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
            ));
        }

        open(player, pokemon);
    }

    private static void handleAbilityConfirmation(EnvyPlayer<EntityPlayerMP> player, Pokemon pokemon,
                                                  AbilityBase ability, boolean hiddenAbility) {
        if (pokemon.getAbility() == ability) {
            open(player, pokemon);
            return;
        }

        PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
        int cost  = hiddenAbility ? config.getHiddenAbilityCost() : config.getAbilityCost();

        if (!EcoFactory.hasBalance(player, cost)) {
            open(player, pokemon);
            player.message(UtilChatColour.translateColourCodes('&',
                                                               UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setAbility(ability);
        player.message(UtilChatColour.translateColourCodes(
                '&',
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getAbilityChanged()
                        .replace("%cost%", cost + "")
                        .replace("%pokemon%", pokemon.getLocalizedName())
                        .replace("%ability%", ability.getLocalizedName())
        ));
        open(player, pokemon);
    }

    private static void openEVSelect(String s, EnvyPlayer<EntityPlayerMP> player, Pokemon pokemon) {
        GuiConfig.EvUI evUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getEvUI();

        StatsType statsType = findStatsType(s);

        if (statsType == null) {
            return;
        }

        NumberModificationUI.builder()
                .player(player)
                .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                .config(evUI.getEvEditAmount())
                .returnHandler((e, c) -> open(player, pokemon))
                .acceptHandler((envyPlayer, clickType, value) -> {
                    if (pokemon.getEVs().get(statsType) == value) {
                        open(player, pokemon);
                        return;
                    }

                    PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
                    int cost = config.getEvIncrementCosts().get(s) * Math.abs(pokemon.getEVs().get(statsType) - value);

                    if (!EcoFactory.hasBalance(player, cost)) {
                        open(player, pokemon);
                        player.message(UtilChatColour.translateColourCodes('&',
                                                                           UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
                        return;
                    }

                    EcoFactory.takeBalance(player, cost);
                    pokemon.getEVs().set(statsType, value);
                    player.message(UtilChatColour.translateColourCodes(
                            '&',
                            UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getEvChanged()
                                    .replace("%cost%", cost + "")
                                    .replace("%pokemon%", pokemon.getLocalizedName())
                                    .replace("%ev%", statsType.getLocalizedName())
                                    .replace("%value%", value + "")
                    ));
                    open(player, pokemon);
                })
                .key(s)
                .confirm(ConfirmationUI.builder().config(evUI.getConfirmConfig()))
                .currentValue(pokemon.getEVs().get(statsType))
                .displayItem(new PositionableItem(
                        UtilSprite.getPokemonElement(pokemon, evUI.getSpriteConfig()),
                        evUI.getPokemonPos()
                ))
                .displayItem(new PositionableItem(
                        UtilConfigItem.fromConfigItem(evUI.getEvSelection().getOptions().get(s)),
                        evUI.getEditDisplayPos()
                ))
                .open();
    }

    private static void openIVSelect(String s, EnvyPlayer<EntityPlayerMP> player, Pokemon pokemon) {
        GuiConfig.IvUI ivUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getIvUI();

        StatsType statsType = findStatsType(s);

        if (statsType == null) {
            return;
        }

        NumberModificationUI.builder()
                .player(player)
                .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                .config(ivUI.getIvEditAmount())
                .returnHandler((e, c) -> open(player, pokemon))
                .acceptHandler((envyPlayer, clickType, value) -> {
                    if (pokemon.getIVs().get(statsType) == value) {
                        open(player, pokemon);
                        return;
                    }

                    PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
                    int cost = config.getEvIncrementCosts().get(s) * Math.abs(pokemon.getEVs().get(statsType) - value);

                    if (!EcoFactory.hasBalance(player, cost)) {
                        open(player, pokemon);
                        player.message(UtilChatColour.translateColourCodes(
                                '&',
                                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()
                        ));
                        return;
                    }

                    EcoFactory.takeBalance(player, cost);
                    pokemon.getIVs().set(statsType, value);
                    player.message(UtilChatColour.translateColourCodes(
                            '&',
                            UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getIvChanged()
                                    .replace("%cost%", cost + "")
                                    .replace("%pokemon%", pokemon.getLocalizedName())
                                    .replace("%iv%", statsType.getLocalizedName())
                                    .replace("%value%", value + "")
                    ));
                    open(player, pokemon);
                })
                .key(s)
                .confirm(ConfirmationUI.builder().config(ivUI.getConfirmConfig()))
                .currentValue(pokemon.getIVs().get(statsType))
                .displayItem(new PositionableItem(
                        UtilSprite.getPokemonElement(pokemon, ivUI.getSpriteConfig()),
                        ivUI.getPokemonPos()
                ))
                .displayItem(new PositionableItem(
                        UtilConfigItem.fromConfigItem(ivUI.getIvSelection().getOptions().get(s)),
                        ivUI.getEditDisplayPos()
                ))
                .open();
    }

    private static StatsType findStatsType(String s) {
        for (StatsType statValue : StatsType.getStatValues()) {
            if (statValue.name().equalsIgnoreCase(s)) {
                return statValue;
            }
        }

        return null;
    }

    private static void handleBallConfirmation(EnvyPlayer<EntityPlayerMP> player, Pokemon pokemon, String s) {
        EnumPokeballs pokeball = findPokeBall(s);

        if (pokeball == null) {
            return;
        }

        if (pokeball == pokemon.getCaughtBall()) {
            open(player, pokemon);
            return;
        }

        PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
        int cost = config.getBallCosts().get(s);

        if (!EcoFactory.hasBalance(player, cost)) {
            open(player, pokemon);
            player.message(UtilChatColour.translateColourCodes('&',
                                                               UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setCaughtBall(pokeball);
        player.message(UtilChatColour.translateColourCodes(
                '&',
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokeballChanged()
                        .replace("%cost%", cost + "")
                        .replace("%pokemon%", pokemon.getLocalizedName())
                        .replace("%pokeball%", pokeball.getLocalizedName() + "")
        ));
        open(player, pokemon);
    }

    private static EnumPokeballs findPokeBall(String s) {
        for (EnumPokeballs ball : EnumPokeballs.values()) {
            if (ball.name().equalsIgnoreCase(s)) {
                return ball;
            }
        }

        return null;
    }

    private static void handleLevelConfirmation(EnvyPlayer<EntityPlayerMP> player, Pokemon pokemon, int level) {
        if (pokemon.getLevel() == level) {
            open(player, pokemon);
            return;
        }

        PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
        int cost = config.getCostPerLevel() * Math.abs(pokemon.getLevel() - level);

        if (!EcoFactory.hasBalance(player, cost)) {
            open(player, pokemon);
            player.message(UtilChatColour.translateColourCodes('&',
                                                               UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setLevel(level);
        player.message(UtilChatColour.translateColourCodes(
                '&',
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getLevelChanged()
                        .replace("%cost%", cost + "")
                        .replace("%pokemon%", pokemon.getLocalizedName())
                        .replace("%level%", level + "")
        ));
        open(player, pokemon);
    }

    private static void handleGrowthConfirmation(EnvyPlayer<EntityPlayerMP> player, Pokemon pokemon, String s) {
        EnumGrowth growth = findGrowth(s);

        if (growth == null) {
            return;
        }

        if (pokemon.getGrowth() == growth) {
            open(player, pokemon);
            return;
        }

        PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
        int cost = config.getGrowthCosts().get(s);

        if (!EcoFactory.hasBalance(player, cost)) {
            open(player, pokemon);
            player.message(UtilChatColour.translateColourCodes('&',
                                                               UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setGrowth(growth);
        player.message(UtilChatColour.translateColourCodes(
                '&',
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getGrowthChanged()
                        .replace("%cost%", cost + "")
                        .replace("%pokemon%", pokemon.getLocalizedName())
                        .replace("%growth%", growth.getLocalizedName())
        ));
        open(player, pokemon);
    }

    private static EnumGrowth findGrowth(String s) {
        for (EnumGrowth growth : EnumGrowth.values()) {
            if (growth.name().equalsIgnoreCase(s)) {
                return growth;
            }
        }

        return null;
    }

    private static void handleNatureConfirmation(EnvyPlayer<EntityPlayerMP> player, Pokemon pokemon, String s) {
        EnumNature nature = findNature(s);

        if (nature == null) {
            return;
        }

        if (pokemon.getNature() == nature) {
            open(player, pokemon);
            return;
        }

        PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
        int cost = config.getNatureCosts().get(s);

        if (!EcoFactory.hasBalance(player, cost)) {
            open(player, pokemon);
            player.message(UtilChatColour.translateColourCodes(
                    '&',
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()
            ));
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setNature(nature);
        player.message(UtilChatColour.translateColourCodes(
                '&',
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getNatureChanged()
                        .replace("%cost%", cost + "")
                        .replace("%pokemon%", pokemon.getLocalizedName())
                        .replace("%nature%", nature.getLocalizedName())
        ));
        open(player, pokemon);
    }

    private static EnumNature findNature(String s) {
        for (EnumNature nature : EnumNature.values()) {
            if (nature.name().equalsIgnoreCase(s)) {
                return nature;
            }
        }

        return null;
    }
}
