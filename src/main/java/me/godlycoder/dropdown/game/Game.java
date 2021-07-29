package me.godlycoder.dropdown.game;

import lombok.Getter;
import me.godlycoder.dropdown.DropDown;
import me.godlycoder.dropdown.game.gamestates.Waiting;
import me.godlycoder.dropdown.gamemap.GameMap;
import me.godlycoder.dropdown.gamemap.LocalGameMap;
import me.godlycoder.dropdown.gameplayer.GamePlayer;
import me.godlycoder.dropdown.gameplayer.GamePlayerState;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Game extends PluginGame {
    private final DropDown plugin;

    @Getter private final Set<GamePlayer> gamePlayers;

    @Getter private GameState gameState;
    @Getter public GameMap gameMap;

    public Game(DropDown plugin) {
        this.plugin = plugin;

        gameMap = new LocalGameMap(plugin.getGameMapsFolder(), "Test", true);
        gamePlayers = new HashSet<>();
    }

    @Override
    public boolean canStart() {
        return gamePlayers.size() >= 4;
    }

    @Override
    public void setGameState(GameState gameState) {
        if (gameState != null) {
            gameState.onDisable();
        }
        this.gameState = gameState;
        gameState.onEnable(plugin);
    }

    @Override
    public void addGamePlayer(Player player) {
        gamePlayers.add(new GamePlayer(plugin, this, player));
        if (gamePlayers.size() >= 1) setGameState(new Waiting(this));
    }

    @Override
    public GamePlayer getGamePlayer(Player player) {
        return gamePlayers.stream()
                .filter( it -> it.getPlayer().equals(player) )
                .findFirst()
                .orElse(null);
    }

    @Override
    public void removeGamePlayer(Player player) {
        gamePlayers.remove(getGamePlayer(player));
    }

    @Override
    public Set<GamePlayer> getAliveGamePlayers() {
        return gamePlayers.stream()
                .filter( it -> it.getGamePlayerState() == GamePlayerState.ALIVE )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<GamePlayer> getSpectatingGamePlayers() {
        return gamePlayers.stream()
                .filter( it -> it.getGamePlayerState() == GamePlayerState.ALIVE )
                .collect(Collectors.toSet());
    }
}
