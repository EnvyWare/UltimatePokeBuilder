package com.envyful.ultimate.poke.builder.forge.ui;

import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.config.UtilConfigItem;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.gui.pane.Pane;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.api.reforged.pixelmon.sprite.UtilSprite;
import com.envyful.api.reforged.pixelmon.storage.UtilPixelmonPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.config.GuiConfig;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class SelectPokemonUI {

    public static void open(EnvyPlayer<EntityPlayerMP> player) {
        GuiConfig.SelectUI config = UltimatePokeBuilderForge.getInstance().getGuiConfig().getSelectPartyUI();

        Pane pane = GuiFactory.paneBuilder()
                .topLeftX(0)
                .topLeftY(0)
                .width(9)
                .height(config.getGuiSettings().getHeight())
                .build();

        for (ConfigItem fillerItem : config.getGuiSettings().getFillerItems()) {
            pane.add(GuiFactory.displayable(UtilConfigItem.fromConfigItem(fillerItem)));
        }

        ItemStack infoItem = UtilConfigItem.fromConfigItem(config.getInfoItem());

        if (infoItem != null) {
            pane.set(config.getInfoItem().getXPos(), config.getInfoItem().getYPos(), GuiFactory.displayable(infoItem));
        }

        Pokemon[] all = UtilPixelmonPlayer.getParty(player.getParent()).getAll();

        for (int i = 0; i < 6; i++) {
            if (config.getPartyPositions().size() <= i) {
                break;
            }

            if (all.length <= i) {

                continue;
            }

            int pos = config.getPartyPositions().get(i);
            Pokemon pokemon = all[i];
            int posX = pos % 9;
            int posY = pos / 9;

            if (pokemon.isEgg()) {
                pane.set(posX, posY, GuiFactory.displayable(UtilConfigItem.fromConfigItem(config.getEggItem())));
                continue;
            }


            pane.set(pos % 9, pos / 9, GuiFactory.displayableBuilder(
                    UtilSprite.getPokemonElement(pokemon, config.getSpriteSettings()))
                    .clickHandler((envyPlayer, clickType) -> {}) //TODO: open next UI
                    .build());
        }

        GuiFactory.guiBuilder()
                .setPlayerManager(UltimatePokeBuilderForge.getInstance().getPlayerManager())
                .addPane(pane)
                .height(config.getGuiSettings().getHeight())
                .title(UtilChatColour.translateColourCodes('&', config.getGuiSettings().getTitle()))
                .build().open(player);
    }

}
