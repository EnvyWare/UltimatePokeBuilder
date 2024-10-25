package com.envyful.ultimate.poke.builder.forge.ui.placeholder;

import com.envyful.api.text.parse.SimplePlaceholder;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;

public class PricesSimplePlaceholder implements SimplePlaceholder {

    public static PricesSimplePlaceholder of() {
        return new PricesSimplePlaceholder();
    }

    protected PricesSimplePlaceholder() {}

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

        for (var entry : UltimatePokeBuilderForge.getConfig().getBallCosts().entrySet()) {
            name = name.replace(
                    "%" + entry.getKey() + "_cost%", String.valueOf(entry.getValue()));
        }

        for (var entry : UltimatePokeBuilderForge.getConfig().getEvIncrementCosts().entrySet()) {
            name = name.replace(
                    "%ev_" + entry.getKey() + "_cost%", String.valueOf(entry.getValue()));
        }

        for (var entry : UltimatePokeBuilderForge.getConfig().getGrowthCosts().entrySet()) {
            name = name.replace(
                    "%" + entry.getKey() + "_cost%", String.valueOf(entry.getValue()));
        }

        for (var entry : UltimatePokeBuilderForge.getConfig().getNatureCosts().entrySet()) {
            name = name.replace(
                    "%" + entry.getKey() + "_cost%", String.valueOf(entry.getValue()));
        }

        for (var entry : UltimatePokeBuilderForge.getConfig().getIvIncrementCosts().entrySet()) {
            name = name.replace(
                    "%iv_" + entry.getKey() + "_cost%", String.valueOf(entry.getValue()));
        }

        return name;
    }
}
