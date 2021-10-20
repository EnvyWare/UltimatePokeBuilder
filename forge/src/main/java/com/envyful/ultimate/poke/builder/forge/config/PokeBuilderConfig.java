package com.envyful.ultimate.poke.builder.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.type.SQLDatabaseDetails;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Integer> evIncrementCosts = new HashMap<String, Integer>() {
        {
            this.put("hp", 100);
            this.put("attack", 100);
            this.put("defence", 100);
            this.put("specialattack", 100);
            this.put("specialdefence", 100);
            this.put("speed", 100);
        }
    };

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

    public Map<String, Integer> getEvIncrementCosts() {
        return this.evIncrementCosts;
    }
}
