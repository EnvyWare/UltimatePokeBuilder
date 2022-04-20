package com.envyful.ultimate.poke.builder.forge.ui.transformer;

import com.envyful.api.gui.Transformer;
import com.google.common.collect.Lists;

import java.util.List;

public class PriceTransformer implements Transformer {

    public static PriceTransformer of(double price) {
        return new PriceTransformer(price);
    }

    private final double price;

    protected PriceTransformer(double price) {this.price = price;}

    @Override
    public String transformName(String name) {
        return name.replace("%cost%", String.format("%,.2f", this.price));
    }

    @Override
    public List<String> transformLore(List<String> lore) {
        List<String> newLore = Lists.newArrayList();

        for (String s : lore) {
            newLore.add(this.transformName(s));
        }

        return newLore;
    }
}
