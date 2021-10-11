package com.envyful.ultimate.poke.builder.forge.command.tokens;

import com.envyful.api.command.annotate.Child;
import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.executor.Argument;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Completable;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.forge.command.completion.player.PlayerTabCompleter;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.player.PokeBuilderAttribute;
import net.minecraft.entity.player.EntityPlayerMP;

@Command(
        value = "give",
        description = "Tokens give command",
        aliases = {
                "g"
        }
)
@Permissible("ultimate.poke.builder.command.tokens.give")
@Child
public class GiveCommand {

    @CommandProcessor
    public void onCommand(@Sender EntityPlayerMP player,
                          @Completable(PlayerTabCompleter.class) @Argument EntityPlayerMP target,
                          int amount) {
        if (amount <= 0) {
            //TODO: send message
            return;
        }

        EnvyPlayer<?> targetPlayer = UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(target);
        PokeBuilderAttribute attribute = targetPlayer.getAttribute(UltimatePokeBuilderForge.class);
        attribute.setTokens(attribute.getTokens() + amount);
        //TODO: send message
    }
}