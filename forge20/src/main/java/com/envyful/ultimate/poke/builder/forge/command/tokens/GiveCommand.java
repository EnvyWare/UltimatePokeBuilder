package com.envyful.ultimate.poke.builder.forge.command.tokens;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.executor.Argument;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Completable;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.command.annotate.permission.Permissible;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.command.completion.player.PlayerTabCompleter;
import com.envyful.api.forge.player.util.UtilPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.eco.player.PokeBuilderAttribute;
import net.minecraft.commands.CommandSource;
import net.minecraft.server.level.ServerPlayer;

@Command(
        value = {
                "give",
                "g"
        }
)
@Permissible("ultimate.poke.builder.command.tokens.give")
public class GiveCommand {

    @CommandProcessor
    public void onCommand(@Sender CommandSource player,
                          @Completable(PlayerTabCompleter.class) @Argument ServerPlayer target,
                          @Argument int amount) {
        if (amount <= 0) {
            player.sendSystemMessage(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getAmountMustBeGreaterThanZero()
            ));
            return;
        }

        var targetPlayer = UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(target);
        var attribute = targetPlayer.getAttributeNow(PokeBuilderAttribute.class);

        attribute.setTokens(attribute.getTokens() + amount);
        player.sendSystemMessage(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance()
                        .getLocale().getMessages().getGivenTokens()
                        .replace("%sender%", UtilPlayer.getName(player))
                        .replace("%player%", target.getName().getString())
                        .replace("%tokens%", amount + "")
        ));

        target.sendSystemMessage(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance()
                        .getLocale().getMessages().getReceivedTokens()
                        .replace("%sender%", UtilPlayer.getName(player))
                        .replace("%player%", target.getName().getString())
                        .replace("%tokens%", amount + "")
        ));

    }
}
