package com.envyful.ultimate.poke.builder.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.command.annotate.permission.Permissible;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import net.minecraft.command.ICommandSource;
import net.minecraft.util.Util;

@Command(
        value = "reload"
)
@Permissible("ultimate.poke.builder.command.reload")
public class ReloadCommand {

    @CommandProcessor
    public void onCommand(@Sender ICommandSource player, String[] args) {
        UltimatePokeBuilderForge.getInstance().loadConfig();
        player.sendMessage(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getReloadedConfigs()
        ), Util.NIL_UUID);
    }
}
