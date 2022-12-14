package com.envyful.ultimate.poke.builder.forge.ui;

import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.config.UtilConfigInterface;
import com.envyful.api.forge.config.UtilConfigItem;
import com.envyful.api.forge.gui.item.PositionableItem;
import com.envyful.api.forge.gui.type.*;
import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.gui.pane.Pane;
import com.envyful.api.reforged.pixelmon.config.PokeSpecPricing;
import com.envyful.api.reforged.pixelmon.config.UtilPokemonPrice;
import com.envyful.api.reforged.pixelmon.sprite.UtilSprite;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.config.GuiConfig;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderConfig;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoFactory;
import com.envyful.ultimate.poke.builder.forge.ui.transformer.PriceTransformer;
import com.pixelmonmod.api.Flags;
import com.pixelmonmod.pixelmon.api.pokemon.Nature;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ability.Ability;
import com.pixelmonmod.pixelmon.api.pokemon.item.pokeball.PokeBall;
import com.pixelmonmod.pixelmon.api.pokemon.item.pokeball.PokeBallRegistry;
import com.pixelmonmod.pixelmon.api.pokemon.species.gender.Gender;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;
import com.pixelmonmod.pixelmon.api.util.ITranslatable;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EditPokemonUI {

    public static void open(ForgeEnvyPlayer player, Pokemon pokemon) {
        GuiConfig.EditPokemonUI config = UltimatePokeBuilderForge.getInstance().getGuiConfig().getEditPokemonUI();

        Pane pane = GuiFactory.paneBuilder()
                .topLeftX(0)
                .topLeftY(0)
                .width(9)
                .height(config.getGuiSettings().getHeight())
                .build();

        UtilConfigInterface.fillBackground(pane, config.getGuiSettings());

        UtilConfigItem.builder()
                .combinedClickHandler(config.getBackButton(), (envyPlayer, clickType) -> SelectPokemonUI.open(player))
                .extendedConfigItem(player, pane, config.getBackButton());

        pane.set(config.getSpritePos() % 9, config.getSpritePos() / 9,
                GuiFactory.displayable(UtilSprite.getPokemonElement(pokemon, config.getSpriteSettings())));

        List<PokeSpecPricing> pricingModifiers = UltimatePokeBuilderForge.getInstance().getConfig().getPricingModifiers();

        if (!Objects.equals(pokemon.getGender(), Gender.NONE)) {
            UtilConfigItem.builder()
                    .combinedClickHandler(config.getGenderButton(), (envyPlayer, clickType) -> {
                        GuiConfig.GenderUI genderUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getGenderUI();

                        TrueFalseSelectionUI.builder()
                                .player(envyPlayer)
                                .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                                .config(genderUI.getTrueFalseSettings())
                                .confirm(ConfirmationUI.builder().config(genderUI.getConfirmConfig()))
                                .startsTrue(Objects.equals(pokemon.getGender(), Gender.MALE))
                                .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                                .trueAcceptHandler((envyPlayer1, clickType1) -> handleGenderConfirmation(player, pokemon, Gender.MALE))
                                .falseAcceptHandler((envyPlayer1, clickType1) -> handleGenderConfirmation(player, pokemon, Gender.FEMALE))
                                .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                                        pokemon,
                                        UltimatePokeBuilderForge.getInstance().getConfig().getGenderCost(),
                                        pricingModifiers
                                )))
                                .transformer(name -> name.replace("%current%", pokemon.getGender().getLocalizedName()))
                                .displayItem(new PositionableItem(
                                        UtilSprite.getPokemonElement(pokemon, genderUI.getSpriteConfig()),
                                        genderUI.getPokemonPos()
                                ))
                                .open();
                    })
                    .extendedConfigItem(player, pane, config.getGenderButton());
        }

        UtilConfigItem.builder()
                .combinedClickHandler(config.getUntradeableButton(), (envyPlayer, clickType) -> {
                    GuiConfig.UntradeableUI untradeableUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getUntradeableUI();

                    TrueFalseSelectionUI.builder()
                            .player(envyPlayer)
                            .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                            .config(untradeableUI.getTrueFalseSettings())
                            .confirm(ConfirmationUI.builder().config(untradeableUI.getConfirmConfig()))
                            .startsTrue(pokemon.isUntradeable())
                            .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                            .trueAcceptHandler((envyPlayer1, clickType1) -> handleUntradeableConfirmation(player, pokemon, true))
                            .falseAcceptHandler((envyPlayer1, clickType1) -> handleUntradeableConfirmation(player, pokemon, false))
                            .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                                    pokemon,
                                    UltimatePokeBuilderForge.getInstance().getConfig().getUntradeableCost(),
                                    pricingModifiers
                            )))
                            .transformer(name -> name.replace("%current%", pokemon.isUntradeable() + ""))
                            .displayItem(new PositionableItem(
                                    UtilSprite.getPokemonElement(pokemon, untradeableUI.getSpriteConfig()),
                                    untradeableUI.getPokemonPos()
                            ))
                            .open();
                })
                .extendedConfigItem(player, pane, config.getUntradeableButton());

        UtilConfigItem.builder()
                .combinedClickHandler(config.getShinyButton(), (envyPlayer, clickType) -> {
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
                            .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                                    pokemon,
                                    UltimatePokeBuilderForge.getInstance().getConfig().getShinyCost(),
                                    pricingModifiers
                            )))
                            .transformer(name -> name.replace("%current%", pokemon.getPalette().getLocalizedName()))
                            .displayItem(new PositionableItem(
                                    UtilSprite.getPokemonElement(pokemon, shinyUI.getSpriteConfig()),
                                    shinyUI.getPokemonPos()
                            ))
                            .open();
                })
                .extendedConfigItem(player, pane, config.getShinyButton());

        UtilConfigItem.builder()
                .combinedClickHandler(config.getAbilityButton(), (envyPlayer, clickType) -> {
                    GuiConfig.AbilitiesUI abilitiesUI =
                            UltimatePokeBuilderForge.getInstance().getGuiConfig().getAbilitiesUI();

                    List<String> abilitiesStripped = Arrays.stream(pokemon.getForm().getAbilities().getAll())
                            .map(ITranslatable::getLocalizedName).collect(Collectors.toList());

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
                                        pokemon.getForm().getAbilities().getAll()[index],
                                        ability.endsWith(" (HA)")
                                );
                            })
                            .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                                    pokemon,
                                    UltimatePokeBuilderForge.getInstance().getConfig().getAbilityCost(),
                                    pricingModifiers
                            )))
                            .transformer(name -> name.replace("%current%", pokemon.getAbility().getLocalizedName()))
                            .displayItem(new PositionableItem(
                                    UtilSprite.getPokemonElement(pokemon, abilitiesUI.getSpriteConfig()),
                                    abilitiesUI.getPokemonPos()
                            ))
                            .open();
                })
                .extendedConfigItem(player, pane, config.getAbilityButton());

        UtilConfigItem.builder()
                .combinedClickHandler(config.getEvButton(), (envyPlayer, clickType) -> {
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
                            .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                                    pokemon,
                                    UltimatePokeBuilderForge.getInstance().getConfig().getEvIncrementCosts().values().toArray(new Integer[0])[0],
                                    pricingModifiers
                            )))
                            .open();
                })
                .extendedConfigItem(player, pane, config.getEvButton());

        UtilConfigItem.builder()
                .combinedClickHandler(config.getIvButton(), (envyPlayer, clickType) -> {
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
                            .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                                    pokemon,
                                    UltimatePokeBuilderForge.getInstance().getConfig().getIvIncrementCosts().values().toArray(new Integer[0])[0],
                                    pricingModifiers
                            )))
                            .open();
                })
                .extendedConfigItem(player, pane, config.getIvButton());

        UtilConfigItem.builder()
                .combinedClickHandler(config.getPokeballButton(), (envyPlayer, clickType) -> {
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
                            .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                                    pokemon,
                                    UltimatePokeBuilderForge.getInstance().getConfig().getBallCosts().values().toArray(new Integer[0])[0],
                                    pricingModifiers
                            )))
                            .open();
                })
                .extendedConfigItem(player, pane, config.getPokeballButton());

        UtilConfigItem.builder()
                .combinedClickHandler(config.getNatureButton(), (envyPlayer, clickType) -> {
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
                            .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                                    pokemon,
                                    UltimatePokeBuilderForge.getInstance().getConfig().getNatureCosts().values().toArray(new Integer[0])[0],
                                    pricingModifiers
                            )))
                            .open();
                })
                .extendedConfigItem(player, pane, config.getNatureButton());

        UtilConfigItem.builder()
                .combinedClickHandler(config.getGrowthButton(), (envyPlayer, clickType) -> {
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
                            .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                                    pokemon,
                                    UltimatePokeBuilderForge.getInstance().getConfig().getGrowthCosts().values().toArray(new Integer[0])[0],
                                    pricingModifiers
                            )))
                            .open();
                })
                .extendedConfigItem(player, pane, config.getGrowthButton());

        UtilConfigItem.builder()
                .combinedClickHandler(config.getLevelButton(), (envyPlayer, clickType) -> {
                    GuiConfig.LevelUI levelUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getLevelUI();

                    NumberModificationUI.builder()
                            .player(player)
                            .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                            .config(levelUI.getLevelEditAmount())
                            .currentValue(pokemon.getPokemonLevel())
                            .returnHandler((envyPlayer1, clickType1) -> open(player, pokemon))
                            .confirm(ConfirmationUI.builder().config(levelUI.getConfirmConfig()))
                            .acceptHandler((envyPlayer1, clickType1, level) -> handleLevelConfirmation(player, pokemon, level))
                            .displayItem(new PositionableItem(
                                    UtilSprite.getPokemonElement(pokemon, levelUI.getSpriteConfig()),
                                    levelUI.getPokemonPos()
                            ))
                            .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                                    pokemon,
                                    UltimatePokeBuilderForge.getInstance().getConfig().getCostPerLevel(),
                                    pricingModifiers
                            )))
                            .open();
                })
                .extendedConfigItem(player, pane, config.getLevelButton());

        GuiFactory.guiBuilder()
                .setPlayerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                .addPane(pane)
                .height(config.getGuiSettings().getHeight())
                .title(UtilChatColour.colour(config.getGuiSettings().getTitle()))
                .build().open(player);
    }

    private static void handleUntradeableConfirmation(ForgeEnvyPlayer player, Pokemon pokemon, boolean untradeable) {
        if (pokemon.isUntradeable() == untradeable) {
            open(player, pokemon);

            if (untradeable) {
                player.message(UtilChatColour.colour(
                        UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonNowUntradeable()
                                .replace("%cost%", 0 + "")
                                .replace("%pokemon%", pokemon.getLocalizedName())
                ));
            } else {
                player.message(UtilChatColour.colour(
                        UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonNowTradeable()
                                .replace("%cost%", 0 + "")
                                .replace("%pokemon%", pokemon.getLocalizedName())
                ));
            }

            return;
        }

        int untradeableCost = (int)UtilPokemonPrice.getMinPrice(pokemon,
                UltimatePokeBuilderForge.getInstance().getConfig().getUntradeableCost(),
                UltimatePokeBuilderForge.getInstance().getConfig().getPricingModifiers());

        if (!EcoFactory.hasBalance(player, untradeableCost)) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, untradeableCost);

        if (untradeable) {
            pokemon.addFlag(Flags.UNTRADEABLE);
        } else {
            pokemon.removeFlag(Flags.UNTRADEABLE);
        }

        if (untradeable) {
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonNowUntradeable()
                            .replace("%cost%", untradeableCost + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
            ));
        } else {
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonNowTradeable()
                            .replace("%cost%", untradeableCost + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
            ));
        }

        open(player, pokemon);
    }

    private static void handleGenderConfirmation(ForgeEnvyPlayer player, Pokemon pokemon, Gender gender) {
        if (Objects.equals(pokemon.getGender(), gender)) {
            open(player, pokemon);

            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonGenderNow()
                            .replace("%cost%", 0 + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
                            .replace("%gender%", gender.getLocalizedName())
            ));
            return;
        }

        int genderCost = (int)UtilPokemonPrice.getMinPrice(pokemon,
                UltimatePokeBuilderForge.getInstance().getConfig().getGenderCost(),
                UltimatePokeBuilderForge.getInstance().getConfig().getPricingModifiers());

        if (!EcoFactory.hasBalance(player, genderCost)) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, genderCost);

        pokemon.setGender(gender);
        player.message(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonGenderNow()
                        .replace("%cost%", 0 + "")
                        .replace("%pokemon%", pokemon.getLocalizedName())
                        .replace("%gender%", gender.getLocalizedName())
        ));

        open(player, pokemon);
    }

    private static void handleShinyConfirmation(ForgeEnvyPlayer player, Pokemon pokemon, boolean shiny) {
        if (pokemon.isShiny() == shiny) {
            open(player, pokemon);

            if (shiny) {
                player.message(UtilChatColour.colour(
                        UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonNowNonShiny()
                                .replace("%cost%", 0 + "")
                                .replace("%pokemon%", pokemon.getLocalizedName())
                ));
            } else {
                player.message(UtilChatColour.colour(
                        UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonNowNonShiny()
                                .replace("%cost%", 0 + "")
                                .replace("%pokemon%", pokemon.getLocalizedName())
                ));
            }

            return;
        }

        int shinyCost = (int)UtilPokemonPrice.getMinPrice(pokemon,
                UltimatePokeBuilderForge.getInstance().getConfig().getShinyCost(),
                UltimatePokeBuilderForge.getInstance().getConfig().getPricingModifiers());

        if (!EcoFactory.hasBalance(player, shinyCost)) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, shinyCost);
        pokemon.setShiny(shiny);

        if (shiny) {
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonNowShiny()
                            .replace("%cost%", shinyCost + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
            ));
        } else {
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokemonNowNonShiny()
                            .replace("%cost%", shinyCost + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
            ));
        }

        open(player, pokemon);
    }

    private static void handleAbilityConfirmation(ForgeEnvyPlayer player, Pokemon pokemon,
                                                  Ability ability, boolean hiddenAbility) {
        if (Objects.equals(pokemon.getAbility(), ability)) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getAbilityChanged()
                            .replace("%cost%", 0 + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
                            .replace("%ability%", ability.getLocalizedName())
            ));
            return;
        }

        PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
        int cost = (int)UtilPokemonPrice.getMinPrice(pokemon, hiddenAbility ? config.getHiddenAbilityCost() :
                config.getAbilityCost(), config.getPricingModifiers());

        if (!EcoFactory.hasBalance(player, cost)) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setAbility(ability);
        player.message(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getAbilityChanged()
                        .replace("%cost%", cost + "")
                        .replace("%pokemon%", pokemon.getLocalizedName())
                        .replace("%ability%", ability.getLocalizedName())
        ));
        open(player, pokemon);
    }

    private static void openEVSelect(String s, ForgeEnvyPlayer player, Pokemon pokemon) {
        GuiConfig.EvUI evUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getEvUI();

        BattleStatsType statsType = findStatsType(s);

        if (statsType == null) {
            return;
        }

        NumberModificationUI.builder()
                .player(player)
                .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                .config(evUI.getEvEditAmount())
                .returnHandler((e, c) -> open(player, pokemon))
                .acceptHandler((envyPlayer, clickType, value) -> {
                    if (pokemon.getEVs().getStat(statsType) == value) {
                        open(player, pokemon);
                        player.message(UtilChatColour.colour(
                                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getEvChanged()
                                        .replace("%cost%", 0 + "")
                                        .replace("%pokemon%", pokemon.getLocalizedName())
                                        .replace("%ev%", statsType.getLocalizedName())
                                        .replace("%value%", value + "")
                        ));
                        return;
                    }

                    if (value > 0 && (pokemon.getEVs().getTotal() + value) > 510) {
                        open(player, pokemon);
                        player.message(UtilChatColour.colour(
                                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getEvsMax()));
                        return;
                    }

                    PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
                    int cost = (int)UtilPokemonPrice.getMinPrice(pokemon,
                            config.getEvIncrementCosts().get(s) * Math.abs(pokemon.getEVs().getStat(statsType) - value),
                            config.getPricingModifiers());

                    if (!EcoFactory.hasBalance(player, cost)) {
                        open(player, pokemon);
                        player.message(UtilChatColour.colour(
                                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
                        return;
                    }

                    EcoFactory.takeBalance(player, cost);
                    pokemon.getEVs().setStat(statsType, value);
                    player.message(UtilChatColour.colour(
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
                .currentValue(pokemon.getEVs().getStat(statsType))
                .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                        pokemon,
                        UltimatePokeBuilderForge.getInstance().getConfig().getEvIncrementCosts().values().toArray(new Integer[0])[0],
                        UltimatePokeBuilderForge.getInstance().getConfig().getPricingModifiers()
                )))
                .transformer(name -> name.replace("%current%", String.valueOf(pokemon.getEVs().getStat(statsType))))
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

    private static void openIVSelect(String s, ForgeEnvyPlayer player, Pokemon pokemon) {
        GuiConfig.IvUI ivUI = UltimatePokeBuilderForge.getInstance().getGuiConfig().getIvUI();

        BattleStatsType statsType = findStatsType(s);

        if (statsType == null) {
            return;
        }

        NumberModificationUI.builder()
                .player(player)
                .playerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                .config(ivUI.getIvEditAmount())
                .returnHandler((e, c) -> open(player, pokemon))
                .acceptHandler((envyPlayer, clickType, value) -> {
                    if (pokemon.getIVs().getStat(statsType) == value) {
                        open(player, pokemon);
                        player.message(UtilChatColour.colour(
                                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getIvChanged()
                                        .replace("%cost%", 0 + "")
                                        .replace("%pokemon%", pokemon.getLocalizedName())
                                        .replace("%iv%", statsType.getLocalizedName())
                                        .replace("%value%", value + "")
                        ));
                        return;
                    }

                    PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
                    int cost = (int)UtilPokemonPrice.getMinPrice(pokemon,
                            config.getIvIncrementCosts().get(s) * Math.abs(pokemon.getIVs().getStat(statsType) - value),
                            config.getPricingModifiers());

                    if (!EcoFactory.hasBalance(player, cost)) {
                        open(player, pokemon);
                        player.message(UtilChatColour.colour(
                                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()
                        ));
                        return;
                    }

                    EcoFactory.takeBalance(player, cost);
                    pokemon.getIVs().setStat(statsType, value);
                    player.message(UtilChatColour.colour(
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
                .currentValue(pokemon.getIVs().getStat(statsType))
                .transformer(PriceTransformer.of(UtilPokemonPrice.getMinPrice(
                        pokemon,
                        UltimatePokeBuilderForge.getInstance().getConfig().getIvIncrementCosts().values().toArray(new Integer[0])[0],
                        UltimatePokeBuilderForge.getInstance().getConfig().getPricingModifiers()
                )))
                .transformer(name -> name.replace("%current%", String.valueOf(pokemon.getIVs().getStat(statsType))))
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

    private static BattleStatsType findStatsType(String s) {
        for (BattleStatsType statValue : BattleStatsType.EV_IV_STATS) {
            if (statValue.name().equalsIgnoreCase(s) || statValue.getLocalizedName().equalsIgnoreCase(s)) {
                return statValue;
            }
        }

        return null;
    }

    private static void handleBallConfirmation(ForgeEnvyPlayer player, Pokemon pokemon, String s) {
        PokeBall pokeball = findPokeBall(s);

        if (pokeball == null) {
            return;
        }

        if (pokeball == pokemon.getBall()) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokeballChanged()
                            .replace("%cost%", 0 + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
                            .replace("%pokeball%", pokeball.getLocalizedName() + "")
            ));
            return;
        }

        PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
        int cost = (int)UtilPokemonPrice.getMinPrice(pokemon, config.getBallCosts().get(s), config.getPricingModifiers());

        if (!EcoFactory.hasBalance(player, cost)) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setBall(pokeball);
        player.message(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getPokeballChanged()
                        .replace("%cost%", cost + "")
                        .replace("%pokemon%", pokemon.getLocalizedName())
                        .replace("%pokeball%", pokeball.getLocalizedName() + "")
        ));
        open(player, pokemon);
    }

    private static PokeBall findPokeBall(String s) {
        for (PokeBall ball : PokeBallRegistry.getAll()) {
            if (ball.getName().equalsIgnoreCase(s)) {
                return ball;
            }
        }

        return null;
    }

    private static void handleLevelConfirmation(ForgeEnvyPlayer player, Pokemon pokemon, int level) {
        if (pokemon.getPokemonLevel() == level) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getLevelChanged()
                            .replace("%cost%", 0 + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
                            .replace("%level%", level + "")
            ));
            return;
        }

        PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
        int cost = (int)UtilPokemonPrice.getMinPrice(pokemon, config.getCostPerLevel() * Math.abs(pokemon.getPokemonLevel() - level), config.getPricingModifiers());

        if (!EcoFactory.hasBalance(player, cost)) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setLevel(level);
        player.message(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getLevelChanged()
                        .replace("%cost%", cost + "")
                        .replace("%pokemon%", pokemon.getLocalizedName())
                        .replace("%level%", level + "")
        ));
        open(player, pokemon);
    }

    private static void handleGrowthConfirmation(ForgeEnvyPlayer player, Pokemon pokemon, String s) {
        EnumGrowth growth = findGrowth(s);

        if (growth == null) {
            return;
        }

        if (pokemon.getGrowth() == growth) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getGrowthChanged()
                            .replace("%cost%", 0 + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
                            .replace("%growth%", growth.getLocalizedName())
            ));
            return;
        }

        PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
        int cost = (int)UtilPokemonPrice.getMinPrice(pokemon, config.getGrowthCosts().get(s), config.getPricingModifiers());

        if (!EcoFactory.hasBalance(player, cost)) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()));
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setGrowth(growth);
        player.message(UtilChatColour.colour(
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

    private static void handleNatureConfirmation(ForgeEnvyPlayer player, Pokemon pokemon, String s) {
        Nature nature = findNature(s);

        if (nature == null) {
            return;
        }

        if (pokemon.getNature() == nature) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getNatureChanged()
                            .replace("%cost%", 0 + "")
                            .replace("%pokemon%", pokemon.getLocalizedName())
                            .replace("%nature%", nature.getLocalizedName())
            ));
            return;
        }

        PokeBuilderConfig config = UltimatePokeBuilderForge.getInstance().getConfig();
        int cost = (int)UtilPokemonPrice.getMinPrice(pokemon, config.getNatureCosts().get(s), config.getPricingModifiers());

        if (!EcoFactory.hasBalance(player, cost)) {
            open(player, pokemon);
            player.message(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFunds()
            ));
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setNature(nature);
        player.message(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getNatureChanged()
                        .replace("%cost%", cost + "")
                        .replace("%pokemon%", pokemon.getLocalizedName())
                        .replace("%nature%", nature.getLocalizedName())
        ));
        open(player, pokemon);
    }

    private static Nature findNature(String s) {
        for (Nature nature : Nature.values()) {
            if (nature.name().equalsIgnoreCase(s)) {
                return nature;
            }
        }

        return null;
    }
}
