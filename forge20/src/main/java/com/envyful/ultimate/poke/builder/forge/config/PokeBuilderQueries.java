package com.envyful.ultimate.poke.builder.forge.config;

public class PokeBuilderQueries {

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `ultimate_poke_builder_players`(" +
            "id         INT         UNSIGNED        NOT NULL        AUTO_INCREMENT, " +
            "uuid       VARCHAR(64) NOT NULL, " +
            "balance    INT         UNSIGNED        NOT NULL, " +
            "UNIQUE(uuid), " +
            "PRIMARY KEY(id));";

    public static String LOAD_USER = "SELECT balance FROM `ultimate_poke_builder_players` WHERE uuid = ?;";

    public static String UPDATE_CREATE_USER = "INSERT INTO `ultimate_poke_builder_players`(uuid, balance) " +
            "VALUES (?, ?) ON DUPLICATE KEY UPDATE balance = VALUES(`balance`);";

}
