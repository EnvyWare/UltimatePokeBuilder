package com.envyful.ultimate.poke.builder.forge.eco.handler;

public interface EcoHandler {

    String getId();

    int getBalance();

    void setBalance(int balance);

    void addBalance(int balance);

    void takeBalance(int balance);

}
