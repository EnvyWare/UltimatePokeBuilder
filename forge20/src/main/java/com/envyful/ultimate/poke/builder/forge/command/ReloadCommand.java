package com.envyful.ultimate.poke.builder.forge.command;

import com.envyful.api.command.annotate.Child;
import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import net.minecraft.commands.CommandSource;

@Command(
        value = "reload",
        description = "Reloads the configs"
)
@Permissible("ultimate.poke.builder.command.reload")
@Child
public class ReloadCommand {

    @CommandProcessor
    public void onCommand(@Sender CommandSource player, String[] args) {
        UltimatePokeBuilderForge.getInstance().loadConfig();
        player.sendSystemMessage(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getReloadedConfigs()
        ));
    }
}
