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

    private String economyHandler = "tokens";
    private int defaultTokens = 500;
    private int shinyCost = 200;
    private int abilityCost = 500;
    private int hiddenAbilityCost = 1000;
    private int costPerLevel = 200;
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

    private Map<String, Integer> ballCosts = new HashMap<String, Integer>() {
        {
            this.put("pokeball", 100);
            this.put("greatball", 100);
            this.put("ultraball", 100);
            this.put("masterball", 100);
            this.put("levelball", 100);
            this.put("moonball", 100);
            this.put("friendball", 100);
            this.put("loveball", 100);
            this.put("safariball", 100);
            this.put("heavyball", 100);
            this.put("fastball", 100);
            this.put("repeatball", 100);
            this.put("timerball", 100);
            this.put("nestball", 100);
            this.put("netball", 100);
            this.put("diveball", 100);
            this.put("luxuryball", 100);
            this.put("healball", 100);
            this.put("duskball", 100);
            this.put("premierball", 100);
            this.put("sportball", 100);
            this.put("quickball", 100);
            this.put("lureball", 100);
            this.put("parkball", 100);
            this.put("cherishball", 100);
            this.put("gsball", 100);
            this.put("beastball", 100);
            this.put("dreamball", 100);
        }
    };

    private Map<String, Integer> growthCosts = new HashMap<String, Integer>() {
        {
            this.put("microscopic", 100);
            this.put("pygmy", 100);
            this.put("runt", 100);
            this.put("small", 100);
            this.put("ordinary", 100);
            this.put("huge", 100);
            this.put("giant", 100);
            this.put("enormous", 100);
            this.put("ginormous", 100);
        }
    };

    private Map<String, Integer> natureCosts = new HashMap<String, Integer>() {
        {
            this.put("hardy", 100);
            this.put("serious", 100);
            this.put("docile", 100);
            this.put("bashful", 100);
            this.put("quirky", 100);
            this.put("lonely", 100);
            this.put("brave", 100);
            this.put("adamant", 100);
            this.put("naughty", 100);
            this.put("bold", 100);
            this.put("relaxed", 100);
            this.put("impish", 100);
            this.put("lax", 100);
            this.put("timid", 100);
            this.put("hasty", 100);
            this.put("jolly", 100);
            this.put("naive", 100);
            this.put("modest", 100);
            this.put("mild", 100);
            this.put("quiet", 100);
            this.put("rash", 100);
            this.put("calm", 100);
            this.put("gentle", 100);
            this.put("sassy", 100);
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

    public int getCostPerLevel() {
        return this.costPerLevel;
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

    public Map<String, Integer> getBallCosts() {
        return this.ballCosts;
    }

    public Map<String, Integer> getGrowthCosts() {
        return this.growthCosts;
    }

    public Map<String, Integer> getNatureCosts() {
        return this.natureCosts;
    }
}
