package com.envyful.ultimate.poke.builder.forge.command.tokens;

import com.envyful.api.command.annotate.Child;
import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.executor.Argument;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Completable;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.command.completion.player.PlayerTabCompleter;
import com.envyful.api.forge.player.util.UtilPlayer;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.eco.player.PokeBuilderAttribute;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;

@Command(
        value = "give",
        description = "§7/tokens give <player> <amount>",
        aliases = {
                "g"
        }
)
@Permissible("ultimate.poke.builder.command.tokens.give")
@Child
public class GiveCommand {

    @CommandProcessor
    public void onCommand(@Sender ICommandSource player,
                          @Completable(PlayerTabCompleter.class) @Argument ServerPlayerEntity target,
                          @Argument int amount) {
        if (amount <= 0) {
            player.sendMessage(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getAmountMustBeGreaterThanZero()
            ), Util.NIL_UUID);
            return;
        }

        EnvyPlayer<?> targetPlayer = UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(target);
        PokeBuilderAttribute attribute = targetPlayer.getAttribute(UltimatePokeBuilderForge.class);

        attribute.setTokens(attribute.getTokens() + amount);
        player.sendMessage(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance()
                        .getLocale().getMessages().getGivenTokens()
                        .replace("%sender%", UtilPlayer.getName(player))
                        .replace("%player%", target.getName().getString())
                        .replace("%tokens%", amount + "")
        ), Util.NIL_UUID);

        target.sendMessage(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance()
                        .getLocale().getMessages().getReceivedTokens()
                        .replace("%sender%", UtilPlayer.getName(player))
                        .replace("%player%", target.getName().getString())
                        .replace("%tokens%", amount + "")
        ), Util.NIL_UUID);

    }
}
