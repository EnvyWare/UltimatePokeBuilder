package com.envyful.ultimate.poke.builder.forge.command.tokens;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.SubCommands;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.command.annotate.permission.Permissible;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.eco.player.PokeBuilderAttribute;
import net.minecraft.entity.player.ServerPlayerEntity;

@Command(
        value = {
                "tokens",
                "token"
        }
)
@Permissible("ultimate.poke.builder.command.tokens")
@SubCommands({GiveCommand.class, TakeCommand.class, PayCommand.class})
public class TokensCommand {

    @CommandProcessor
    public void onCommand(@Sender ServerPlayerEntity player, String[] args) {
        EnvyPlayer<?> sender = UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(player);
        var attribute = sender.getAttributeNow(PokeBuilderAttribute.class);

        sender.message(UtilChatColour.colour(
                        UltimatePokeBuilderForge.getInstance()
                                .getLocale().getMessages().getBalance()
                                .replace("%player%", player.getName().getString())
                                .replace("%tokens%", attribute.getTokens() + "")
                )
        );
    }
}
