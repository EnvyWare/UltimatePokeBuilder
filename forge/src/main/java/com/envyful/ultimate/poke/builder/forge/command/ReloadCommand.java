package com.envyful.ultimate.poke.builder.forge.command;

import com.envyful.api.command.annotate.Child;
import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.ui.SelectPokemonUI;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

@Command(
        value = "reload",
        description = "Reloads the configs"
)
@Permissible("ultimate.poke.builder.command.reload")
@Child
public class ReloadCommand {

    @CommandProcessor
    public void onCommand(@Sender ICommandSender player, String[] args) {
        UltimatePokeBuilderForge.getInstance().loadConfig();
    }
}
