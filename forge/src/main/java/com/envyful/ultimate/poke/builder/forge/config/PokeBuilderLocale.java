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
        private String sentTokens = "&c&l(!) %amount% send to %player% %new_total%";
        private String receivedPlayerTokens = "&c&l(!) %amount% received from %player% %new_total%";

        private String cannotPaySelf = "&c&l(!) &cCannot pay yourself!";
        private String amountMustBeGreaterThanZero = "&c&l(!) &cAmount of tokens cannot be less than zero.";
        private String cannotTakeThisMany = "&c&l(!) &c%player% doesn't have enough tokens for you to take this amount";
        private String insufficientFunds = "&c&l(!) &cYou do not have enough money to purchase this!";
        private String insufficientFundsPay = "&c&l(!) &cYou do not have enough tokens";
        private String evsMax = "&c&l(!) &cYou cannot have an EV total of greater than 510!";

        private String pokemonNowShiny = "&e&l(!) &e%pokemon% is now &6&lSHINY";
        private String pokemonNowNonShiny = "&e&l(!) &e%pokemon% is now &f&lNON SHINY&e for %cost%";
        private String abilityChanged = "&e&l(!) &e%pokemon%'s new ability is %ability% for %cost%";
        private String evChanged = "&e&l(!) &e%pokemon%'s %ev% is now %value% for %cost%";
        private String ivChanged = "&e&l(!) &e%pokemon%'s %iv% is now %value% for %cost%";
        private String pokeballChanged = "&e&l(!) &e%pokemon%'s new ball is %pokeball% for %cost%";
        private String levelChanged = "&e&l(!) &e%pokemon%'s new level is %level% for %cost%";
        private String growthChanged = "&e&l(!) &e%pokemon%'s new growth is %growth% for %cost%";
        private String natureChanged = "&e&l(!) &e%pokemon%'s new nature is %nature% for %cost%";

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

        public String getInsufficientFunds() {
            return this.insufficientFunds;
        }

        public String getPokemonNowShiny() {
            return this.pokemonNowShiny;
        }

        public String getPokemonNowNonShiny() {
            return this.pokemonNowNonShiny;
        }

        public String getAbilityChanged() {
            return this.abilityChanged;
        }

        public String getEvChanged() {
            return this.evChanged;
        }

        public String getIvChanged() {
            return this.ivChanged;
        }

        public String getPokeballChanged() {
            return this.pokeballChanged;
        }

        public String getLevelChanged() {
            return this.levelChanged;
        }

        public String getGrowthChanged() {
            return this.growthChanged;
        }

        public String getNatureChanged() {
            return this.natureChanged;
        }

        public String getEvsMax() {
            return this.evsMax;
        }

        public String getInsufficientFundsPay() {
            return this.insufficientFundsPay;
        }

        public String getSentTokens() {
            return this.sentTokens;
        }

        public String getReceivedPlayerTokens() {
            return this.receivedPlayerTokens;
        }

        public String getCannotPaySelf() {
            return this.cannotPaySelf;
        }
    }
}
