package com.envyful.ultimate.poke.builder.forge.ui;

import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.config.UtilConfigInterface;
import com.envyful.api.forge.config.UtilConfigItem;
import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.gui.pane.Pane;
import com.envyful.api.reforged.pixelmon.sprite.UtilSprite;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.config.GuiConfig;
import com.pixelmonmod.api.pokemon.PokemonSpecification;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;

public class SelectPokemonUI {

    public static void open(ForgeEnvyPlayer player) {
        GuiConfig.SelectUI config = UltimatePokeBuilderForge.getInstance().getGuiConfig().getSelectPartyUI();

        Pane pane = GuiFactory.paneBuilder()
                .topLeftX(0)
                .topLeftY(0)
                .width(9)
                .height(config.getGuiSettings().getHeight())
                .build();

        UtilConfigInterface.fillBackground(pane, config.getGuiSettings());
        UtilConfigItem.builder().extendedConfigItem(player, pane, config.getInfoItem());

        Pokemon[] all = StorageProxy.getParty(player.getParent()).getAll();

        for (int i = 0; i < 6; i++) {
            if (config.getPartyPositions().size() <= i) {
                break;
            }

            if (all.length <= i) {
                continue;
            }

            int pos = config.getPartyPositions().get(i);
            Pokemon pokemon = all[i];

            if (pokemon == null) {
                continue;
            }

            int posX = pos % 9;
            int posY = pos / 9;

            if (pokemon.isEgg()) {
                pane.set(posX, posY, GuiFactory.displayable(UtilConfigItem.fromConfigItem(config.getEggItem())));
                continue;
            }

            if (isBlacklisted(pokemon)) {
                pane.set(posX, posY, GuiFactory.displayable(UtilConfigItem.fromConfigItem(config.getBlacklistedItem())));
                continue;
            }

            pane.set(pos % 9, pos / 9, GuiFactory.displayableBuilder(
                    UtilSprite.getPokemonElement(pokemon, config.getSpriteSettings()))
                    .clickHandler((envyPlayer, clickType) -> EditPokemonUI.open(player, pokemon))
                    .build());
        }

        GuiFactory.guiBuilder()
                .setPlayerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                .addPane(pane)
                .height(config.getGuiSettings().getHeight())
                .title(UtilChatColour.colour(config.getGuiSettings().getTitle()))
                .build().open(player);
    }

    private static boolean isBlacklisted(Pokemon pokemon) {
        for (PokemonSpecification blockedSpec : UltimatePokeBuilderForge.getInstance().getConfig().getBlockedSpecs()) {
            if (blockedSpec != null && blockedSpec.matches(pokemon)) {
                return true;
            }
        }

        return false;
    }

}
