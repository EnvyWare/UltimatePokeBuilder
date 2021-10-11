package com.envyful.ultimate.poke.builder.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigPath("config/UltimatePokeBuilder/locale.yml")
@ConfigSerializable
public class PokeBuilderLocale extends AbstractYamlConfig {

    private Messages messages = new Messages();

    public PokeBuilderLocale() {
        super();
    }

    public Messages getMessages() {
        return this.messages;
    }

    @ConfigSerializable
    public static class Messages {

        private String reloadedConfigs = "&e&l(!) &eReloaded configs";
        private String openingPokeBuilder = "&e&l(!) &eOpening PokeBuilder...";
        private String balance = "&e&l(!) &eTokens: %tokens%";
        private String receivedTokens = "&e&l(!) &eYou (%player%) were given %tokens% tokens by %sender%";
        private String tokensTaken = "&e&l(!) &eYou (%player%) lost %tokens% tokens taken by %sender%";
        private String givenTokens = "&e&l(!) &eYou (%sender%) gave %player% %tokens% tokens";
        private String takenTokens = "&e&l(!) &eYou (%sender%) took %tokens% tokens from %player%";

        private String amountMustBeGreaterThanZero = "&c&l(!) &cAmount of tokens cannot be less than zero.";
        private String cannotTakeThisMany = "&c&l(!) &c%player% doesn't have enough tokens for you to take this amount";

        public Messages() {}

        public String getReloadedConfigs() {
            return this.reloadedConfigs;
        }

        public String getOpeningPokeBuilder() {
            return this.openingPokeBuilder;
        }

        public String getBalance() {
            return this.balance;
        }

        public String getReceivedTokens() {
            return this.receivedTokens;
        }

        public String getTokensTaken() {
            return this.tokensTaken;
        }

        public String getGivenTokens() {
            return this.givenTokens;
        }

        public String getTakenTokens() {
            return this.takenTokens;
        }

        public String getAmountMustBeGreaterThanZero() {
            return this.amountMustBeGreaterThanZero;
        }

        public String getCannotTakeThisMany() {
            return this.cannotTakeThisMany;
        }
    }
}
