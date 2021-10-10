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
                                                (envyPlayer, clickType) -> {}); //TODO:
        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getAbilityButton(),
                                                (envyPlayer, clickType) -> {}); //TODO:
        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getEvButton(),
                                                (envyPlayer, clickType) -> {}); //TODO:
        UtilConfigItem.addPermissibleConfigItem(pane, player.getParent(), config.getIvButton(),
                                                (envyPlayer, clickType) -> {}); //TODO:
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
                .height(config.getGuiSettings().getHeight())
                .title(UtilChatColour.translateColourCodes('&', config.getGuiSettings().getTitle()))
                .build().open(player);
    }

}
