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
import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.eco.player.PokeBuilderAttribute;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;

import java.util.Objects;

@Command(
        value = "pay",
        description = "§7/tokens pay <player> <amount>",
        aliases = {
                "p"
        }
)
@Permissible("ultimate.poke.builder.command.tokens.pay")
@Child
public class PayCommand {


    @CommandProcessor
    public void onCommand(@Sender ServerPlayerEntity sender,
                          @Completable(PlayerTabCompleter.class) @Argument ServerPlayerEntity target,
                          @Argument int amount) {
        if (amount <= 0) {
            sender.sendMessage(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getAmountMustBeGreaterThanZero()
            ), Util.NIL_UUID);
            return;
        }

        EnvyPlayer<?> senderPlayer = UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(sender);
        PokeBuilderAttribute senderAttribute = senderPlayer.getAttribute(UltimatePokeBuilderForge.class);

        if (senderAttribute == null) {
            return;
        }

        if (senderAttribute.getTokens() < amount) {
            sender.sendMessage(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFundsPay()
            ), Util.NIL_UUID);
            return;
        }

        EnvyPlayer<?> targetPlayer = UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(target);

        if (Objects.equals(senderPlayer.getUuid(), targetPlayer.getUuid())) {
            sender.sendMessage(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getCannotPaySelf()
            ), Util.NIL_UUID);
            return;
        }

        PokeBuilderAttribute targetAttribute = targetPlayer.getAttribute(UltimatePokeBuilderForge.class);

        if (targetAttribute == null) {
            return;
        }

        senderAttribute.setTokens(senderAttribute.getTokens() - amount);
        sender.sendMessage(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getSentTokens()
                        .replace("%amount%", amount + "")
                        .replace("%player%", targetPlayer.getName())
                        .replace("%new_total%", senderAttribute.getTokens() + "")
        ), Util.NIL_UUID);

        targetAttribute.setTokens(targetAttribute.getTokens() + amount);
        target.sendMessage(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getReceivedPlayerTokens()
                        .replace("%amount%", amount + "")
                        .replace("%player%", senderPlayer.getName())
                        .replace("%new_total%", targetAttribute.getTokens() + "")
        ), Util.NIL_UUID);
    }
}
