package com.envyful.ultimate.poke.builder.forge.ui;

import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.config.UtilConfigItem;
import com.envyful.api.forge.gui.item.PositionableItem;
import com.envyful.api.forge.gui.type.ConfirmationUI;
import com.envyful.api.forge.gui.type.TrueFalseSelectionUI;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.gui.pane.Pane;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.api.reforged.pixelmon.sprite.UtilSprite;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.config.GuiConfig;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderConfig;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoFactory;
import com.envyful.ultimate.poke.builder.forge.ui.type.DynamicSelectionUI;
import com.envyful.ultimate.poke.builder.forge.ui.type.MultiSelectionUI;
import com.envyful.ultimate.poke.builder.forge.ui.type.NumberModificationUI;
import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
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
                                                (envyPlayer, clickType) -> {}); //TODO:
        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getNatureButton(),
                                                (envyPlayer, clickType) -> {}); //TODO:
        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getGrowthButton(),
                                                (envyPlayer, clickType) -> {}); //TODO:
        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getLevelButton(),
                                                (envyPlayer, clickType) -> {}); //TODO:

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
            open(player, pokemon); //TODO: send message
            return;
        }

        EcoFactory.takeBalance(player, shinyCost);
        pokemon.setShiny(shiny);
        //TODO: send message
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
            open(player, pokemon); //TODO: send message
            return;
        }

        EcoFactory.takeBalance(player, cost);
        pokemon.setAbility(ability);
        //TODO: send message
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
                    int cost = config.getEvIncrementCosts().get(s) * (pokemon.getEVs().get(statsType) - value);

                    if (!EcoFactory.hasBalance(player, cost)) {
                        open(player, pokemon); //TODO: send message
                        return;
                    }

                    EcoFactory.takeBalance(player, cost);
                    pokemon.getEVs().set(statsType, value);
                    //TODO: send message
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
                    int cost = config.getEvIncrementCosts().get(s) * (pokemon.getEVs().get(statsType) - value);

                    if (!EcoFactory.hasBalance(player, cost)) {
                        open(player, pokemon); //TODO: send message
                        return;
                    }

                    EcoFactory.takeBalance(player, cost);
                    pokemon.getIVs().set(statsType, value);
                    //TODO: send message
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

}
