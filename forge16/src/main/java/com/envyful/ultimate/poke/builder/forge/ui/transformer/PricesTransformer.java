package com.envyful.ultimate.poke.builder.forge.ui.transformer;

import com.envyful.api.text.parse.SimplePlaceholder;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;

import java.util.Map;

public class PricesTransformer implements SimplePlaceholder {

    public static PricesTransformer of() {
        return new PricesTransformer();
    }

    protected PricesTransformer() {}

    @Override
    public String replace(String name) {
        name = name.replace(
                        "%unbreedable_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getUnbreedableCost()))
                .replace(
                        "%untradeable_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getUntradeableCost()))
                .replace(
                        "%shiny_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getShinyCost()))
                .replace(
                        "%ability_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getAbilityCost()))
                .replace(
                        "%hidden_ability_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getHiddenAbilityCost()))
                .replace(
                        "%gender_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getGenderCost()))
                .replace(
                        "%per_level_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getGenderCost()));

        for (Map.Entry<String, Integer> stringIntegerEntry : UltimatePokeBuilderForge.getConfig().getBallCosts().entrySet()) {
            name = name.replace(
                    "%" + stringIntegerEntry.getKey() + "_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getGenderCost()));
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : UltimatePokeBuilderForge.getConfig().getEvIncrementCosts().entrySet()) {
            name = name.replace(
                    "%" + stringIntegerEntry.getKey() + "_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getGenderCost()));
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : UltimatePokeBuilderForge.getConfig().getGrowthCosts().entrySet()) {
            name = name.replace(
                    "%" + stringIntegerEntry.getKey() + "_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getGenderCost()));
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : UltimatePokeBuilderForge.getConfig().getNatureCosts().entrySet()) {
            name = name.replace(
                    "%" + stringIntegerEntry.getKey() + "_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getGenderCost()));
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : UltimatePokeBuilderForge.getConfig().getIvIncrementCosts().entrySet()) {
            name = name.replace(
                    "%" + stringIntegerEntry.getKey() + "_cost%", String.format("%,.2f", UltimatePokeBuilderForge.getConfig().getGenderCost()));
        }

        return name;
    }
}
