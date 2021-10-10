package com.envyful.ultimate.poke.builder.forge.command.tokens;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.SubCommands;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.player.PokeBuilderAttribute;
import net.minecraft.entity.player.EntityPlayerMP;

@Command(
        value = "tokens",
        description = "Tokens main command",
        aliases = {
                "token"
        }
)
@Permissible("ultimate.poke.builder.command.tokens")
@SubCommands({GiveCommand.class, TakeCommand.class})
public class TokensCommand {

    @CommandProcessor
    public void onCommand(@Sender EntityPlayerMP player, String[] args) {
        EnvyPlayer<?> sender = UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(player);
        PokeBuilderAttribute attribute = sender.getAttribute(UltimatePokeBuilderForge.class);
        //TODO: send message
    }
}
