package com.envyful.ultimate.poke.builder.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.type.SQLDatabaseDetails;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import com.envyful.api.player.SaveMode;
import com.envyful.api.player.SaveModeTypeAdapter;
import com.envyful.api.reforged.pixelmon.config.PokeSpecPricing;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.pixelmonmod.api.pokemon.PokemonSpecification;
import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigPath("config/UltimatePokeBuilder/config.yml")
@ConfigSerializable
public class PokeBuilderConfig extends AbstractYamlConfig {

    private SQLDatabaseDetails sqlDatabaseDetails = new SQLDatabaseDetails("UltimatePokeBuilder", "0.0.0.0", 3306,
                                                                           "username", "password",
                                                                           "database");

    private SaveMode saveMode = SaveMode.JSON;
    private String economyHandler = "tokens";
    private int defaultTokens = 500;
    private int shinyCost = 200;
    private int untradeableCost = 600;
    private int genderCost = 600;
    private int abilityCost = 500;
    private int hiddenAbilityCost = 1000;
    private int costPerLevel = 200;
    private Map<String, Integer> evIncrementCosts = new HashMap<String, Integer>() {
        {
            this.put("hp", 100);
            this.put("attack", 100);
            this.put("defense", 100);
            this.put("special_attack", 100);
            this.put("special_defense", 100);
            this.put("speed", 100);
        }
    };

    private Map<String, Integer> ivIncrementCosts = new HashMap<String, Integer>() {
        {
            this.put("hp", 100);
            this.put("attack", 100);
            this.put("defense", 100);
            this.put("special_attack", 100);
            this.put("special_defense", 100);
            this.put("speed", 100);
        }
    };

    private Map<String, Integer> ballCosts = new HashMap<String, Integer>() {
        {
            this.put("poke_ball", 100);
            this.put("great_ball", 100);
            this.put("ultra_ball", 100);
            this.put("master_ball", 100);
            this.put("level_ball", 100);
            this.put("moon_ball", 100);
            this.put("friend_ball", 100);
            this.put("love_ball", 100);
            this.put("safari_ball", 100);
            this.put("heavy_ball", 100);
            this.put("fast_ball", 100);
            this.put("repeat_ball", 100);
            this.put("timer_ball", 100);
            this.put("nest_ball", 100);
            this.put("net_ball", 100);
            this.put("dive_ball", 100);
            this.put("luxury_ball", 100);
            this.put("heal_ball", 100);
            this.put("dusk_ball", 100);
            this.put("premier_ball", 100);
            this.put("sport_ball", 100);
            this.put("quick_ball", 100);
            this.put("lure_ball", 100);
            this.put("park_ball", 100);
            this.put("cherish_ball", 100);
            this.put("gs_ball", 100);
            this.put("beast_ball", 100);
            this.put("dream_ball", 100);
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

    private List<String> blacklistSpecs = Lists.newArrayList(
            "ditto", "islegendary:true"
    );

    private transient List<PokemonSpecification> blacklistSpecsCache = null;

    private Map<String, PokeSpecPricing> minPriceModifiers = ImmutableMap.of(
            "example", new PokeSpecPricing("shiny:1", new PokeSpecPricing.MathHandler("*", 2.0))
    );

    public PokeBuilderConfig() {
        super();
    }

    public SaveMode getSaveMode() {
        return this.saveMode;
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

    public int getUntradeableCost() {
        return this.untradeableCost;
    }

    public int getGenderCost() {
        return this.genderCost;
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

    public Map<String, Integer> getIvIncrementCosts() {
        return this.ivIncrementCosts;
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

    public List<PokemonSpecification> getBlockedSpecs() {
        if (this.blacklistSpecsCache == null) {
            List<PokemonSpecification> specs = Lists.newArrayList();

            for (String blacklistSpec : this.blacklistSpecs) {
                specs.add(PokemonSpecificationProxy.create(blacklistSpec));
            }

            this.blacklistSpecsCache = specs;
        }

        return this.blacklistSpecsCache;
    }

    public List<PokeSpecPricing> getPricingModifiers() {
        return Lists.newArrayList(this.minPriceModifiers.values());
    }
}
