package com.envyful.ultimate.poke.builder.forge.ui.placeholder;

import com.envyful.api.text.parse.SimplePlaceholder;

import java.util.function.DoubleSupplier;

public class PriceSimplePlaceholder implements SimplePlaceholder {

    public static PriceSimplePlaceholder of(DoubleSupplier price) {
        return new PriceSimplePlaceholder(price);
    }

    public static PriceSimplePlaceholder of(double price) {
        return new PriceSimplePlaceholder(() -> price);
    }

    private final DoubleSupplier price;

    protected PriceSimplePlaceholder(DoubleSupplier price) {this.price = price;}

    @Override
    public String replace(String name) {
        return name.replace("%cost%", String.format("%,.2f", this.price.getAsDouble()));
    }
}
