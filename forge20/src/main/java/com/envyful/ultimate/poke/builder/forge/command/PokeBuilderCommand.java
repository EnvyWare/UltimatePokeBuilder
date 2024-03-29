package com.envyful.ultimate.poke.builder.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.SubCommands;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.command.annotate.permission.Permissible;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.ui.SelectPokemonUI;
import net.minecraft.server.level.ServerPlayer;

@Command(
        value = {
                "pokebuilder",
                "ultimatepokebuilder",
                "upb"
        }
)
@Permissible("ultimate.poke.builder.command")
@SubCommands(ReloadCommand.class)
public class PokeBuilderCommand {

    @CommandProcessor
    public void onCommand(@Sender ServerPlayer player, String[] args) {
        SelectPokemonUI.open(UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(player));
    }
}
