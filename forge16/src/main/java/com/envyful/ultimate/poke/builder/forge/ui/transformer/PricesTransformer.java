package com.envyful.ultimate.poke.builder.forge.ui.transformer;

import com.envyful.api.gui.Transformer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;

import java.util.Map;

public class PricesTransformer implements Transformer {

    public static PricesTransformer of() {
        return new PricesTransformer();
    }

    protected PricesTransformer() {}

    @Override
    public String transformName(String name) {
        name = name.replace(
                        "%unbreedable_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getUnbreedableCost()))
                .replace(
                        "%untradeable_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getUntradeableCost()))
                .replace(
                        "%shiny_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getShinyCost()))
                .replace(
                        "%ability_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getAbilityCost()))
                .replace(
                        "%hidden_ability_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getHiddenAbilityCost()))
                .replace(
                        "%gender_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getGenderCost()))
                .replace(
                        "%per_level_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getGenderCost()));

        for (Map.Entry<String, Integer> stringIntegerEntry : UltimatePokeBuilderForge.getConfig().getBallCosts().entrySet()) {
            name = name.replace(
                    "%" + stringIntegerEntry.getKey() + "_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getGenderCost()));
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : UltimatePokeBuilderForge.getConfig().getEvIncrementCosts().entrySet()) {
            name = name.replace(
                    "%" + stringIntegerEntry.getKey() + "_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getGenderCost()));
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : UltimatePokeBuilderForge.getConfig().getGrowthCosts().entrySet()) {
            name = name.replace(
                    "%" + stringIntegerEntry.getKey() + "_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getGenderCost()));
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : UltimatePokeBuilderForge.getConfig().getNatureCosts().entrySet()) {
            name = name.replace(
                    "%" + stringIntegerEntry.getKey() + "_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getGenderCost()));
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : UltimatePokeBuilderForge.getConfig().getIvIncrementCosts().entrySet()) {
            name = name.replace(
                    "%" + stringIntegerEntry.getKey() + "_cost%", String.format("%,.2d", UltimatePokeBuilderForge.getConfig().getGenderCost()));
        }

        return name;
    }
}
