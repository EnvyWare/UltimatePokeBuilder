package com.envyful.ultimate.poke.builder.forge.eco.handler.impl;

import com.envyful.api.player.EnvyPlayer;
import com.envyful.api.reforged.pixelmon.storage.UtilPixelmonPlayer;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoHandler;
import com.pixelmonmod.pixelmon.api.economy.IPixelmonBankAccount;
import net.minecraft.entity.player.EntityPlayerMP;

public class PixelmonEcoHandler implements EcoHandler {

    private static final String HANDLER_ID = "Pixelmon";

    @Override
    public String getId() {
        return HANDLER_ID;
    }

    @Override
    public boolean hasBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        return getBalance(player) >= balance;
    }

    @Override
    public int getBalance(EnvyPlayer<EntityPlayerMP> player) {
        IPixelmonBankAccount bank = UtilPixelmonPlayer.getBank(player.getParent());

        if (bank == null) {
            return 0;
        }

        return bank.getMoney();
    }

    @Override
    public void setBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        IPixelmonBankAccount bank = UtilPixelmonPlayer.getBank(player.getParent());

        if (bank == null) {
            return;
        }

        bank.setMoney(balance);
    }

    @Override
    public void addBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        IPixelmonBankAccount bank = UtilPixelmonPlayer.getBank(player.getParent());

        if (bank == null) {
            return;
        }

        bank.setMoney(bank.getMoney() + balance);
    }

    @Override
    public void takeBalance(EnvyPlayer<EntityPlayerMP> player, int balance) {
        IPixelmonBankAccount bank = UtilPixelmonPlayer.getBank(player.getParent());

        if (bank == null) {
            return;
        }

        bank.setMoney(bank.getMoney() - balance);
    }
}
