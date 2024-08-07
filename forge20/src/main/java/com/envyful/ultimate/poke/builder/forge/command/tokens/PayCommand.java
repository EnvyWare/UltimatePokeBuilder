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
import net.minecraft.server.level.ServerPlayer;

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
    public void onCommand(@Sender ServerPlayer sender,
                          @Completable(PlayerTabCompleter.class) @Argument ServerPlayer target,
                          @Argument int amount) {
        if (amount <= 0) {
            sender.sendSystemMessage(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getAmountMustBeGreaterThanZero()
            ));
            return;
        }

        EnvyPlayer<?> senderPlayer = UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(sender);
        PokeBuilderAttribute senderAttribute = senderPlayer.getAttributeNow(PokeBuilderAttribute.class);

        if (senderAttribute == null) {
            return;
        }

        if (senderAttribute.getTokens() < amount) {
            sender.sendSystemMessage(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getInsufficientFundsPay()
            ));
            return;
        }

        EnvyPlayer<?> targetPlayer = UltimatePokeBuilderForge.getInstance().getPlayerManager().getPlayer(target);

        if (Objects.equals(senderPlayer.getUniqueId(), targetPlayer.getUniqueId())) {
            sender.sendSystemMessage(UtilChatColour.colour(
                    UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getCannotPaySelf()
            ));
            return;
        }

        PokeBuilderAttribute targetAttribute = targetPlayer.getAttributeNow(PokeBuilderAttribute.class);

        if (targetAttribute == null) {
            return;
        }

        senderAttribute.setTokens(senderAttribute.getTokens() - amount);
        sender.sendSystemMessage(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getSentTokens()
                        .replace("%amount%", amount + "")
                        .replace("%player%", targetPlayer.getName())
                        .replace("%new_total%", senderAttribute.getTokens() + "")
        ));

        targetAttribute.setTokens(targetAttribute.getTokens() + amount);
        target.sendSystemMessage(UtilChatColour.colour(
                UltimatePokeBuilderForge.getInstance().getLocale().getMessages().getReceivedPlayerTokens()
                        .replace("%amount%", amount + "")
                        .replace("%player%", senderPlayer.getName())
                        .replace("%new_total%", targetAttribute.getTokens() + "")
        ));
    }
}
