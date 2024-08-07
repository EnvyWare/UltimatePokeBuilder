package com.envyful.ultimate.poke.builder.forge.command.tokens;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.executor.Argument;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Completable;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.command.annotate.permission.Permissible;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.command.completion.player.PlayerTabCompleter;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.eco.player.PokeBuilderAttribute;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;

import java.util.Objects;

@Command(
        value = {
                "pay",
                "p"
        }
)
@Permissible("ultimate.poke.builder.command.tokens.pay")
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
        var senderAttribute = senderPlayer.getAttributeNow(PokeBuilderAttribute.class);

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

        if (Objects.equals(senderPlayer.getUniqueId(), targetPlayer.getUniqueId())) {
            sender.sendMessage(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getCannotPaySelf()
            ), Util.NIL_UUID);
            return;
        }

        var targetAttribute = targetPlayer.getAttributeNow(PokeBuilderAttribute.class);

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
