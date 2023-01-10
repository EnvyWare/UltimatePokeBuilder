package com.envyful.ultimate.poke.builder.forge.ui.placeholder;

import com.envyful.api.text.parse.SimplePlaceholder;

public class PriceSimplePlaceholder implements SimplePlaceholder {

    public static PriceSimplePlaceholder of(double price) {
        return new PriceSimplePlaceholder(price);
    }

    private final double price;

    protected PriceSimplePlaceholder(double price) {this.price = price;}

    @Override
    public String replace(String name) {
        return name.replace("%cost%", String.format("%,.2f", this.price));
    }
}
