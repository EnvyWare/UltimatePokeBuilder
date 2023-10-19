package com.envyful.ultimate.poke.builder.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.type.SQLDatabaseDetails;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import com.envyful.api.player.SaveMode;
import com.envyful.api.reforged.pixelmon.config.PokeSpecPricing;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.pixelmonmod.api.pokemon.PokemonSpecification;
import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigPath("config/UltimatePokeBuilder/config.yml")
@ConfigSerializable
public class PokeBuilderConfig extends AbstractYamlConfig {


    @Comment("""
            The MySQL database details.
            This will only be used if the save mode is set to MYSQL
            
            NOTE: DO NOT SHARE THESE WITH ANYONE YOU DO NOT TRUST
            """)
    private SQLDatabaseDetails sqlDatabaseDetails = new SQLDatabaseDetails("UltimatePokeBuilder", "0.0.0.0", 3306,
                                                                           "username", "password",
                                                                           "database");

    @Comment("""
            The setting to tell the mod how to save the player data.
            The options are:
            - JSON
            - MYSQL
            """)
    private SaveMode saveMode = SaveMode.JSON;

    @Comment("""
            If the pokebuilder should use a custom tokens economy.
            Alternatively you can use "pokedollars"
            """)
    private String economyHandler = "tokens";

    @Comment("""
            Default number of tokens to give the player if tokens economy is selected
            """)
    private double defaultTokens = 500;

    @Comment("""
            The base cost to change a pokemon to shiny
            """)
    private double shinyCost = 200;

    @Comment("""
            The base cost to change a pokemon to untradeable
            """)
    private double untradeableCost = 600;

    @Comment("""
            The base cost to change a pokemon to unbreedable
            """)
    private double unbreedableCost = 600;

    @Comment("""
            The base cost to change a pokemons gender
            """)
    private double genderCost = 600;

    @Comment("""
            The base cost to change a pokemon's ability
            """)
    private double abilityCost = 500;

    @Comment("""
            The base cost to change a pokemons ability to their hidden ability
            """)
    private double hiddenAbilityCost = 1000;

    @Comment("""
            The base cost to increase the pokemon's level
            """)
    private double costPerLevel = 200;
    private Map<String, Integer> evIncrementCosts = new HashMap<>() {
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

    @Comment("""
            These will modify the price if the pokemon matches the spec
            """)
    private Map<String, PokeSpecPricing> minPriceModifiers = ImmutableMap.of(
            "example", new PokeSpecPricing("shiny", new PokeSpecPricing.MathHandler("*", 2.0))
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

    public double getDefaultTokens() {
        return this.defaultTokens;
    }

    public double getShinyCost() {
        return this.shinyCost;
    }

    public double getUntradeableCost() {
        return this.untradeableCost;
    }

    public double getUnbreedableCost() {
        return this.unbreedableCost;
    }

    public double getGenderCost() {
        return this.genderCost;
    }

    public double getCostPerLevel() {
        return this.costPerLevel;
    }

    public double getAbilityCost() {
        return this.abilityCost;
    }

    public double getHiddenAbilityCost() {
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
                specs.add(PokemonSpecificationProxy.create(blacklistSpec).get());
            }

            this.blacklistSpecsCache = specs;
        }

        return this.blacklistSpecsCache;
    }

    public List<PokeSpecPricing> getPricingModifiers() {
        return Lists.newArrayList(this.minPriceModifiers.values());
    }
}
