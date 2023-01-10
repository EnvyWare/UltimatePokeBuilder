package com.envyful.ultimate.poke.builder.forge.ui.transformer;

import com.envyful.api.text.parse.SimplePlaceholder;

public class PriceTransformer implements SimplePlaceholder {

    public static PriceTransformer of(double price) {
        return new PriceTransformer(price);
    }

    private final double price;

    protected PriceTransformer(double price) {this.price = price;}

    @Override
    public String replace(String name) {
        return name.replace("%cost%", String.format("%,.2f", this.price));
    }
}
