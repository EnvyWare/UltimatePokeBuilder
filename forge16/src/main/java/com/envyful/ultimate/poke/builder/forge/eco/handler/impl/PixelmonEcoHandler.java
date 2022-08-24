package com.envyful.ultimate.poke.builder.forge.eco.handler.impl;

import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.eco.handler.EcoHandler;
import com.pixelmonmod.pixelmon.api.economy.BankAccount;
import com.pixelmonmod.pixelmon.api.economy.BankAccountProxy;

public class PixelmonEcoHandler implements EcoHandler {

    private static final String HANDLER_ID = "Pixelmon";

    @Override
    public String getId() {
        return HANDLER_ID;
    }

    @Override
    public boolean hasBalance(ForgeEnvyPlayer player, int balance) {
        return getBalance(player) >= balance;
    }

    @Override
    public int getBalance(ForgeEnvyPlayer player) {
        BankAccount bank = BankAccountProxy.getBankAccountUnsafe(player.getParent());

        if (bank == null) {
            return 0;
        }

        return bank.getBalance().intValue();
    }

    @Override
    public void setBalance(ForgeEnvyPlayer player, int balance) {
        BankAccount bank = BankAccountProxy.getBankAccountUnsafe(player.getParent());

        if (bank == null) {
            return;
        }

        bank.setBalance(balance);
    }

    @Override
    public void addBalance(ForgeEnvyPlayer player, int balance) {
        BankAccount bank = BankAccountProxy.getBankAccountUnsafe(player.getParent());

        if (bank == null) {
            return;
        }

        bank.add(balance);
    }

    @Override
    public void takeBalance(ForgeEnvyPlayer player, int balance) {
        BankAccount bank = BankAccountProxy.getBankAccountUnsafe(player.getParent());

        if (bank == null) {
            return;
        }

        bank.add(balance);
    }
}
