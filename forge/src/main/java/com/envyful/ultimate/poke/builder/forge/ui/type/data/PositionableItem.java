package com.envyful.ultimate.poke.builder.forge.ui.type.data;

import net.minecraft.item.ItemStack;

public class PositionableItem {

    private ItemStack itemStack;
    private int posX;
    private int posY;

    public PositionableItem(ItemStack itemStack, int posX, int posY) {
        this.itemStack = itemStack;
        this.posX = posX;
        this.posY = posY;
    }

    public PositionableItem(ItemStack itemStack, int pos) {
        this(itemStack, pos % 9, pos / 9);
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }
}
