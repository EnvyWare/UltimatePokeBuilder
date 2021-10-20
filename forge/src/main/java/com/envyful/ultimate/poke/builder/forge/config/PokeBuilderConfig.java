package com.envyful.ultimate.poke.builder.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.type.SQLDatabaseDetails;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigPath("config/UltimatePokeBuilder/config.yml")
@ConfigSerializable
public class PokeBuilderConfig extends AbstractYamlConfig {

    private SQLDatabaseDetails sqlDatabaseDetails = new SQLDatabaseDetails("UltimatePokeBuilder", "0.0.0.0", 3306,
                                                                           "username", "password",
                                                                           "database");

    private String economyHandler = "Token";
    private int defaultTokens = 500;
    private int shinyCost = 200;
    private int abilityCost = 500;
    private int hiddenAbilityCost = 1000;

    public PokeBuilderConfig() {
        super();
    }

    public SQLDatabaseDetails getSqlDatabaseDetails() {
        return this.sqlDatabaseDetails;
    }

    public String getEconomyHandler() {
        return this.economyHandler;
    }

    public int getDefaultTokens() {
        return this.defaultTokens;
    }

    public int getShinyCost() {
        return this.shinyCost;
    }

    public int getAbilityCost() {
        return this.abilityCost;
    }

    public int getHiddenAbilityCost() {
        return this.hiddenAbilityCost;
    }
}
