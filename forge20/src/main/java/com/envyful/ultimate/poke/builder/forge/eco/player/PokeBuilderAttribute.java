package com.envyful.ultimate.poke.builder.forge.eco.player;

import com.envyful.api.forge.player.attribute.ManagedForgeAttribute;
import com.envyful.api.player.save.attribute.DataDirectory;
import com.envyful.ultimate.poke.builder.forge.UltimatePokeBuilderForge;
import com.envyful.ultimate.poke.builder.forge.config.PokeBuilderQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@DataDirectory("config/players/UltimatePokeBuilder/")
public class PokeBuilderAttribute extends ManagedForgeAttribute<UltimatePokeBuilderForge> {

    private double tokens = 0;

    public PokeBuilderAttribute() {
        super(UltimatePokeBuilderForge.getInstance());
    }

    public double getTokens() {
        return this.tokens;
    }

    public void setTokens(double tokens) {
        this.tokens = tokens;
    }

    @Override
    public void load() {
        if (this.manager.getDatabase() == null) {
            return;
        }

        try (Connection connection = this.manager.getDatabase().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PokeBuilderQueries.LOAD_USER)) {
            preparedStatement.setString(1, this.id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                this.tokens = UltimatePokeBuilderForge.getConfig().getDefaultTokens();
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
            preparedStatement.setString(1, this.id.toString());
            preparedStatement.setInt(2, (int)this.tokens);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
