package com.envyful.ultimate.poke.builder.forge.player;

import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.api.forge.player.attribute.AbstractForgeAttribute;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PokeBuilderAttribute extends AbstractForgeAttribute<UltimatePokeBuilderForge> {

    private int tokens = 0;

    public PokeBuilderAttribute(UltimatePokeBuilderForge manager, EnvyPlayer<?> parent) {
        super(manager, (ForgeEnvyPlayer) parent);
    }

    public int getTokens() {
        return this.tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    @Override
    public void load() {
        if (this.manager.getDatabase() == null) {
            return;
        }

        try (Connection connection = this.manager.getDatabase().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PokeBuilderQueries.LOAD_USER)) {
            preparedStatement.setString(1, this.parent.getUuid().toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                this.tokens = this.manager.getConfig().getDefaultTokens();
                return;
            }

            this.tokens = resultSet.getInt("balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        if (this.manager.getDatabase() == null) {
            return;
        }

        try (Connection connection = this.manager.getDatabase().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PokeBuilderQueries.UPDATE_CREATE_USER)) {
            preparedStatement.setString(1, this.parent.getUuid().toString());
            preparedStatement.setInt(2, this.tokens);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
