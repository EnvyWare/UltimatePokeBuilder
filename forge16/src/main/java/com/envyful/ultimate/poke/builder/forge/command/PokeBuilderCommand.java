package com.envyful.ultimate.poke.builder.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.SubCommands;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.ui.SelectPokemonUI;
import net.minecraft.entity.player.ServerPlayerEntity;

@Command(
        value = "pokebuilder",
        description = "PokeBuilder main command",
        aliases = {
                "ultimatepokebuilder",
                "upb"
        }
)
@Permissible("ultimate.poke.builder.command")
@SubCommands(ReloadCommand.class)
public class PokeBuilderCommand {

    @CommandProcessor
    public void onCommand(@Sender ServerPlayerEntity player, String[] args) {
        SelectPokemonUI.open(UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(player));
    }
}
